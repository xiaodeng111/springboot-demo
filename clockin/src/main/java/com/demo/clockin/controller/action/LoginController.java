package com.demo.clockin.controller.action;


import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.controller.BaseController;
import com.demo.clockin.common.lang.DateUtil;
import com.demo.clockin.common.lang.MD5Util;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.domain.bo.*;
import com.demo.clockin.domain.vo.ManagerVo;
import com.demo.clockin.service.IManagerService;
import com.demo.clockin.service.IRoleResourceService;
import com.demo.clockin.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 登录控制器
 * @author:dengrq
 * @time:2017年4月27日
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
	
	@Autowired
	private IManagerService managerService;
	
	@Autowired
	private IRoleResourceService roleResourceService;
	
	@Autowired
	private IRolesService rolesService;

	/**
	 * @Description:TODO
	 * @param session
	 * @return String
	 * @author: dengrq
	 * @time:2017年4月27日
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String doLogin(HttpSession session) {
		ManagerBo bo = (ManagerBo)session.getAttribute(Constants.SESSION_MANAGER);
		if(null != bo) {
			return "main";
		} else {
			return "redirect:/";
		}
	}
	
	/**
	 * 用户登录
	 * @param loginName
	 * @param password
	 * @param
	 * @return ModelAndView
	 * @author: dengrq
	 * @time:2017年4月27日
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String findLoginManager(String loginName, String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userErr");

		if(StringUtil.isEmpty(loginName)) {
			session.setAttribute("userErr","用户名/邮箱错误");
			return "redirect:/";
		}
		if(StringUtil.isEmpty(password)) {
			session.setAttribute("userErr","密码错误");
			return"redirect:/";
		}
		ManagerBo manager = new ManagerBo();
		manager.setLoginName(loginName);
		manager.setPassword(MD5Util.stringToPassword(password));
		ManagerVo m = managerService.findManager4Login(manager);
		
		if(null == m) {
			session.setAttribute("userErr","用户名/邮箱或者密码不匹配");
			return "redirect:/";
		}

		if(m.getIsLock() == 1){
			session.setAttribute("userErr","用户被禁用");
			return "redirect:/";
		}
		// session存当前登陆人
		session.setAttribute(Constants.SESSION_MANAGER, m);
		
		// 记录最后登录的时间和IP
		m.setLastLoginTime(DateUtil.getNowDateTime());
		m.setLastLoginIp(getRealIP(request));
		managerService.doSetLoginTime(m);
		RolesBo rolesBo = new RolesBo();
		rolesBo.setId(m.getRoleId());
		RolesBo rolesById = rolesService.findRolesById(rolesBo);
		if(rolesById.getRoleStatus()!=null && rolesById.getRoleStatus() == 1){
			// 设置当前登陆人的权限
			RoleResourceBo roleResource = new RoleResourceBo();
			roleResource.setRoleId(m.getRoleId());
			List<RoleResourceBo> roleResourceList = roleResourceService.findRoleResourceByRoleId(roleResource);
			if (roleResourceList != null && roleResourceList.size() > 0) {
				Map<String, RoleResourceBo> permissionsMap = new HashMap<String, RoleResourceBo>();
				Map<Integer, ResourcesBo> tmpResMap = new HashMap<Integer, ResourcesBo>();

				for (ResourcesBo resources : Constants.getResourceMap().values()) {
					for (RoleResourceBo rr : roleResourceList) {
						if (resources.getId().equals(rr.getReourceId()) && resources.getResourceStatus() ==1) {
							tmpResMap.put(resources.getId(), resources);
							if (!resources.getDefaultUri().equals("#")) {
								permissionsMap.put(resources.getDefaultUri(), rr);
							} else {
								permissionsMap.put(resources.getNameEn(), rr);
							}
							break;
						}
					}
				}
				if (!org.springframework.util.StringUtils.isEmpty(permissionsMap)) {
					m.setPermissionsMap(permissionsMap);
					List<CatalogBo> catalogList = new ArrayList<CatalogBo>();
					List<MenuBo> menuList = new ArrayList<MenuBo>();
					List<ModuleBo> moduleList = new ArrayList<ModuleBo>();

					for (RoleResourceBo rr : permissionsMap.values()) {
						ResourcesBo resources = tmpResMap.get(rr.getReourceId());
						// 空菜单、空目录不显示
						boolean hasCatalogsChild = true;
						boolean hasMenuChild = true;

						// 菜单
						if (resources.getResourceType().equals(Constants.RESOURCES_MENU_TYPE)) {
							if (hasMenuChild) {
								MenuBo menuTemp = new MenuBo();
								menuTemp.setId(resources.getId());
								menuTemp.setMenuname(resources.getNameZh());
								menuTemp.setOrdernum(resources.getResourceOrder());
								menuList.add(menuTemp);
							}
						}
						// 菜单下目录
						if (resources.getResourceType().equals(Constants.RESOURCES_CATALOG_TYPE)) {
							if (hasCatalogsChild) {
								CatalogBo catalogTemp = new CatalogBo();
								catalogTemp.setId(resources.getId());
								catalogTemp.setCatalogname(resources.getNameZh());
								catalogTemp.setMenuId(resources.getParentId());
								catalogTemp.setOrdernum(resources.getResourceOrder());
								catalogList.add(catalogTemp);
							}
						}
						// 目录下模块
						if (resources.getResourceType().equals(Constants.RESOURCES_MODULE_TYPE)) {
							ModuleBo module = new ModuleBo();
							module.setId(resources.getId());
							module.setModulename(resources.getNameZh());
							module.setModuleurl(resources.getDefaultUri().substring(0, resources.getDefaultUri().lastIndexOf('/')));
							module.setModuleact(resources.getDefaultUri().substring(resources.getDefaultUri().lastIndexOf('/'),
									resources.getDefaultUri().length()));
							module.setCatalogId(resources.getParentId());
							module.setOrder(resources.getResourceOrder());
							moduleList.add(module);
						}
					}
					// 排序菜单显示
					Collections.sort(catalogList);
					Collections.sort(menuList);
					Collections.sort(moduleList);

					session.setAttribute("catalogList", catalogList);
					session.setAttribute("menuList", menuList);
					session.setAttribute("moduleList", moduleList);
				}

			}
		}
		RolesBo rolesVO = new RolesBo();
		rolesVO.setId(m.getRoleId());
		RolesBo roles = rolesService.findRolesById(rolesVO);
		// session存当前登陆人角色
		session.setAttribute("role", roles);
		
		return "main";
	}
	
	/**
	 * 用户登出
	 * @param session
	 * @return String
	 * @author: dengrq
	 * @time:2017年4月27日
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String doLogout(HttpSession session) {
		session.removeAttribute(Constants.SESSION_MANAGER);
		session.removeAttribute("catalogList");
		session.removeAttribute("menuList");
		session.removeAttribute("moduleList");
		session.removeAttribute("role");
		
		return "redirect:/";
	}
	
}
