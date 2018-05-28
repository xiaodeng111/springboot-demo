package com.demo.clockin.controller.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.lang.DateUtil;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.domain.bo.RoleResourceBo;
import com.demo.clockin.domain.bo.RolesBo;
import com.demo.clockin.domain.param.RolesParam;
import com.demo.clockin.domain.vo.ManagerVo;
import com.demo.clockin.service.IRoleResourceService;
import com.demo.clockin.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 角色控制器
 * @author:dengrq
 * @time:2017年5月1日
 */
@Controller
@RequestMapping("/admin/roles")
public class RolesController extends BaseProController {
	
	private static final String permissionsSplit = ";";
	private static final String enameSplit = "_";
	
	@Autowired
	private IRolesService rolesService;
	
	@Autowired
	private IRoleResourceService roleResourceService;
	
	/**
	 * 查询角色列表
	 * @return String
	 * @author: dengrq
	 * @time:2017年5月1日
	 */
	@RequestMapping(value="/rolesAction", method=RequestMethod.GET)
	public String onList() {
		return "roles/list";
	}
	
	/**
	 * 查询角色列表
	 * void
	 * @author: dengrq
	 * @time:2017年5月1日
	 */
	@RequestMapping(value="/list4ajax")
	public void findRolesList4ajax(HttpServletRequest request, HttpServletResponse response) {
		RolesParam roles = new RolesParam();
		String pageNumber 	= null;
		String pageSize 	= null;
		
		String dtGridPager 	= request.getParameter("dtGridPager");
		if(null != dtGridPager) {
			pageSize 			= JSON.parseObject(dtGridPager).getString("pageSize");
			pageNumber 			= JSON.parseObject(dtGridPager).getString("nowPage");
			String parameters 	= JSON.parseObject(dtGridPager).getString("parameters");
			String name 		= JSON.parseObject(parameters).getString("name");
			
			if(StringUtil.isNotEmpty(name)) {
				roles.setName(name);
			}
		}
		
		roles.setPageNumber(StringUtil.isNotEmpty(pageNumber) ? Integer.valueOf(pageNumber) : 1);
		roles.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.valueOf(pageSize) : 15);
		
		IPage<RolesBo> pageList = rolesService.findRoles(roles);
		
		writeResponse4Ajax(pageList, response);
	}
	
	/**
	 * 进入角色添加页面
	 * @return String
	 * @author: dengrq
	 * @time:2017年5月1日
	 */
	@RequestMapping(value="/onAdd", method=RequestMethod.GET)
	public String onAddRole(HttpServletRequest request, Model model) {
		model.addAttribute("json", JSONObject.toJSONString(proResources(request)));
		return "roles/add";
	}
	
	/**
	 * 保存角色
	 * @return String
	 * @author: dengrq
	 * @time:2017年5月1日
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAddRole(RolesBo roles, HttpServletRequest request) {
		roles.setCreatorId(this.getSessionManager(request).getId());
		roles.setCreator(this.getSessionManager(request).getLoginName());
		roles.setCreateDate(DateUtil.getNowDateTime());
		rolesService.doAddSave(roles);
		proRoleResources(roles, request);
		return "redirect:/admin/roles/rolesAction";
	}
	
	/**
	 * 进入角色编辑页面
	 * @return String
	 * @author: dengrq
	 * @time:2017年5月2日
	 */
	@RequestMapping(value="onEdit", method={RequestMethod.GET, RequestMethod.POST})
	public String onEditRole(HttpServletRequest request) {
		String idStr 	= request.getParameter("id");
		String nowPage 	= request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String name 	= request.getParameter("name");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("name", name);
		
		int id = StringUtil.toInt(idStr);
		
		RolesBo roles = new RolesBo();
		roles.setId(id);
		RolesBo rolesVO = rolesService.findRolesById(roles);
		RoleResourceBo roler = new RoleResourceBo();
		roler.setRoleId(rolesVO.getId());
		List<RoleResourceBo> rrList = roleResourceService.findRoleResourceByRoleId(roler);
		request.setAttribute("roles", rolesVO);

		Collection<ResourcesBo> resourceList = Constants.getResourceMap().values();
		Collection<ResourceActionBo> raList = Constants.getResourceActionMap().values();
		List<ResourcesBo> resultList = proResources(request);
		Set<String> permissionSet = new HashSet<String>();
		StringBuilder permissions = new StringBuilder("");
		for (ResourcesBo r : resourceList) {
			if (r.getResourceStatus().equals(Constants.COMMONS_DICT_STATUS_YES))
				for (ResourceActionBo ra : raList) {
					if (ra.getActionStatus().equals(Constants.COMMONS_DICT_STATUS_YES))
						for (RoleResourceBo rr : rrList) {
							if (rr.getReourceId().equals(r.getId()) && rr.getActionBit() == 0) {
								permissionSet.add(r.getNameEn());
							} else if (rr.getReourceId().equals(r.getId()) && (ra.getBit() & rr.getActionBit()) > 0
									&& (ra.getBit() & r.getActions()) > 0) {
								if (!permissionSet.contains(r.getNameEn()))
									permissionSet.add(r.getNameEn());
								permissionSet.add(r.getNameEn() + enameSplit + ra.getNameEn());
							}
						}
				}
		}
		for (String p : permissionSet) {
			if (permissions.length() > 0) {
				permissions.append(permissionsSplit);
			}
			permissions.append(p);
		}
		request.setAttribute("json", JSONObject.toJSONString(resultList));
		request.setAttribute("permissions", permissions.toString());
		
		return "roles/edit";
	}
	
	/**
	 * 保存修改 角色
	 * 
	 * @return
	 */
	@RequestMapping(value="edit", method={RequestMethod.POST})
	public String editSave(RolesBo roles, HttpServletRequest request) {
		String nowPage 	= request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String name 	= request.getParameter("name");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("name", name);
		
		roles.setCreatorId(this.getSessionManager(request).getId());
		roles.setCreator(this.getSessionManager(request).getName());
		roles.setCreateDate(DateUtil.getNowDateTime());
		rolesService.doEditSave(roles);
		roleResourceService.doDeleteByRoleIds("where role_id = " + roles.getId());
		proRoleResources(roles, request);
		
		return "redirect:/admin/roles/rolesAction";
	}
	
	/**
	 * 添加、修改准备
	 * 
	 * @return
	 */
	private List<ResourcesBo> proResources(HttpServletRequest request) {

		Collection<ResourcesBo> resourceList = Constants.getResourceMap().values();
		Collection<ResourceActionBo> raList = Constants.getResourceActionMap().values();
		ManagerVo sm = this.getSessionManager(request);
		
		Map<String, RoleResourceBo> permissionMap = sm.getPermissionsMap();
		List<ResourcesBo> resultList = new ArrayList<ResourcesBo>();
		// 系统超级管理不限制权限;其他管理只能分配自己拥有的权限
		for (ResourcesBo r : resourceList) {
			if (sm.getId() == 1
					|| (permissionMap.containsKey(r.getDefaultUri()) || permissionMap.containsKey(r.getNameEn())))
				if (r.getResourceStatus().equals(Constants.COMMONS_DICT_STATUS_YES)) {
					resultList.add(r);
					for (ResourceActionBo ra : raList) {
						if (ra.getActionStatus().equals(Constants.COMMONS_DICT_STATUS_YES))
							if ((ra.getBit() & r.getActions()) > 0) {
								if (sm.getId() == 1
										|| ((permissionMap.get(r.getDefaultUri()).getActionBit() & ra.getBit()) > 0)) {
									ResourcesBo raction = new ResourcesBo();
									raction.setId(-1);
									raction.setNameEn(r.getNameEn() + enameSplit + ra.getNameEn());
									raction.setNameZh(ra.getNameZh());
									raction.setDefaultUri(ra.getNameEn());
									raction.setParentId(r.getId());
									raction.setParentName(r.getNameZh());
									raction.setResourceType(0);
									raction.setActions(ra.getBit());
									resultList.add(raction);
								}
							}
					}
				}

		}
		return resultList;
	}
	
	/**
	 * 添加、修改 保存处理
	 */
	private void proRoleResources(RolesBo roles, HttpServletRequest request) {
		String permissionsPar = request.getParameter("permissions");
		if (null == permissionsPar) {
			return;
		}
		Map<String, ResourcesBo> enameRes = Constants.getResourceMap();
		Map<String, ResourceActionBo> enameAction = Constants.getResourceActionMap();
		String permissions[] = permissionsPar.split(permissionsSplit);
		Map<Integer, RoleResourceBo> rrMap = new HashMap<Integer, RoleResourceBo>();
		for (String p : permissions) {
			if (p.contains(enameSplit)) {
				String pr = p.split(enameSplit)[0];
				String pa = p.split(enameSplit)[1];
				Integer rId = enameRes.get(pr).getId();
				if (null != rrMap.get(rId)) {
					RoleResourceBo r = rrMap.get(rId);
					Integer bit = enameAction.get(pa).getBit();
					if (null != r.getActionBit())
						r.setActionBit(r.getActionBit() + bit);
					else
						r.setActionBit(r.getActionBit());
					rrMap.put(enameRes.get(pr).getId(), r);
				} else {
					RoleResourceBo rr = new RoleResourceBo();
					rr.setReourceId(enameRes.get(pr).getId());
					rr.setRoleId(roles.getId());
					rr.setActionBit(enameAction.get(pa).getBit());
					rrMap.put(enameRes.get(pr).getId(), rr);
				}
			} else {
				if (enameRes.containsKey(p)) {
					Integer rId = enameRes.get(p).getId();
					if (null == rrMap.get(rId)) {
						RoleResourceBo rr = new RoleResourceBo();
						rr.setReourceId(enameRes.get(p).getId());
						rr.setRoleId(roles.getId());
						rr.setActionBit(0);
						rrMap.put(enameRes.get(p).getId(), rr);
					}
				}
			}
		}
		for (RoleResourceBo rr : rrMap.values()) {
			if (null != rr.getActionBit())
				roleResourceService.doAddSave(rr);
			else
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}
