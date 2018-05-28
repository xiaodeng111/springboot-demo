package com.demo.clockin.controller.action;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.clockin.common.lang.DateUtil;
import com.demo.clockin.common.lang.MD5Util;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.bo.ManagerInfoBo;
import com.demo.clockin.domain.bo.RolesBo;
import com.demo.clockin.domain.param.ManagerAddParam;
import com.demo.clockin.domain.param.ManagerParam;
import com.demo.clockin.domain.vo.ManagerVo;
import com.demo.clockin.service.IManagerInfoService;
import com.demo.clockin.service.IManagerService;
import com.demo.clockin.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * 管理员控制器类
 * @author:dengrq
 * @time:2017年4月26日
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerController extends BaseProController {
	@Autowired
	private IManagerService managerService;
	
	@Autowired
	private IManagerInfoService managerInfoService;
		
	@Autowired
	private IRolesService rolesService;
	
	@RequestMapping(value = "/onMain", method = RequestMethod.GET)
	public String onMain() {
		return "main";
	}
		
	/**
	 * 跳转管理员列表
	 * @return ResponseBody
	 * @author: dengrq
	 * @time:2017年4月26日
	 */
	@RequestMapping(value = "/managerAction", method = RequestMethod.GET)
	public String findManagerList(ManagerBo manager, HttpServletRequest request, Model model) {
		ManagerVo currentManager = getSessionManager(request);
						
		List<RolesBo> rolesList = rolesService.findRolesList(currentManager.getRoleId());
		model.addAttribute("rolesList", rolesList);
		
		model.addAttribute("manager", manager);
		
		return "manager/list";
	}
	
	/**
	 * 查询管理员列表
	 * @return ApiResult
	 * @author: dengrq
	 * @time:2017年4月28日
	 */
	@RequestMapping(value = "/list4ajax")
	public void findManagerList4Ajax(HttpServletRequest request, HttpServletResponse response) {
		ManagerParam manager = new ManagerParam();
		String pageNumber 	= null;
		String pageSize 	= null;
		
		String dtGridPager 	= request.getParameter("dtGridPager");
		if(null != dtGridPager) {
	   		String parameters 	= JSONObject.parseObject(dtGridPager).getString("parameters");
	   		String pageSizeStr 	= JSON.parseObject(dtGridPager).getString("pageSize");
			String pageNoStr 	= JSON.parseObject(dtGridPager).getString("nowPage");
	   		
			String loginName 	= JSONObject.parseObject(parameters).getString("loginName");
			String schoolCode 	= JSONObject.parseObject(parameters).getString("schoolCode");
			String roleId 		= JSONObject.parseObject(parameters).getString("roleId");
			String isOnjob 		= JSONObject.parseObject(parameters).getString("isOnjob");
			String isLock 		= JSONObject.parseObject(parameters).getString("isLock");
			// 用于保持当前页
			pageNumber 	= JSONObject.parseObject(parameters).getString("nowPage");
			pageNumber = StringUtil.isEmpty(pageNumber) ? pageNoStr : pageNumber;
			pageSize 	= JSONObject.parseObject(parameters).getString("pageSize");
			pageSize = StringUtil.isEmpty(pageSize) ? pageSizeStr : pageSize;
			
			ManagerVo currentManager = getSessionManager(request);
			manager.setSchoolCode(schoolCode);
			if(currentManager.getRoleId()==2||currentManager.getRoleId()==3){
				manager.setSchoolCode(currentManager.getSchoolCode());
			}
			
			manager.setLoginName(loginName);
			if(StringUtil.isNotEmpty(roleId)){
				manager.setRoleId(Integer.parseInt(roleId));
			}
			if(StringUtil.isNotEmpty(isOnjob)){
				manager.setIsOnjob(Integer.parseInt(isOnjob));
			}
			if(StringUtil.isNotEmpty(isLock)){
				manager.setIsLock(Integer.parseInt(isLock));
			}
		}
		
		manager.setPageNumber(StringUtil.isNotEmpty(pageNumber) ? Integer.valueOf(pageNumber) : 1);
		manager.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.valueOf(pageSize) : 15);
		
		IPage<ManagerVo> managerList = managerService.findManager(manager);
		this.writeResponse4Ajax(managerList, response);
	}
	
	/**
	 * 进入添加账号管理页面
	 * @return String
	 * @author: cg
	 * @time: 2017-06-02
	 */
	@RequestMapping(value="/onAdd", method=RequestMethod.GET)
	public String onAddManager(HttpServletRequest request) {
		List<RolesBo> roleList = rolesService.findRolesList(this.getSessionManager(request).getRoleId());
		request.setAttribute("roleList", roleList);
		
		return "manager/add";
	}
	
	/**
	 * 保存账号管理信息
	 * @param schoolBo
	 * @return String
	 * @author: cg
	 * @time: 2017-06-02
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAddManager(ManagerAddParam manager, HttpServletRequest request) {
		Date createTime = DateUtil.getNowDateTime();
		
		ManagerBo managerBo = manager.getManager();
		
		ManagerVo currentManager = getSessionManager(request);
		// 先验证登录名是否重复
		ManagerParam param = new ManagerParam();
		param.setLoginName(manager.getManager().getLoginName());
		IPage<ManagerBo> managers = managerService.findListByPage(param);
		if (managers != null && null != managers.getResult() && managers.getResult().size() > 0) {
//			message = "添加失败：登录名重复";
			
		} else{
			if (manager.getManager().getPassword() != null && manager.getManager().getPassword().trim().length() > 0) {
				managerBo.setPassword(MD5Util.stringToPassword(manager.getManager().getPassword()));
			}
			managerBo.setCreateTime(createTime);
			managerBo.setNickname(manager.getManagerInfo().getName());
			managerBo.setCreator(currentManager.getName());
			managerService.doAddManager(managerBo);
			
			ManagerInfoBo managerInfo = manager.getManagerInfo();
			managerInfo.setManagerId(managerBo.getId());
			managerInfo.setIsOnjob(manager.getManagerInfo().getIsOnjob());
			managerInfo.setCreateTime(createTime);
			managerInfo.setCreator(currentManager.getName());
			managerInfoService.doSave(managerInfo);
		}
		
		return "redirect:/admin/manager/managerAction";
	}
	
	/**
	 * 用于校验用户名是否唯一
	 * @return
	 */
	@RequestMapping(value="/checkLoginName")
	public void checkLoginName(HttpServletRequest request, HttpServletResponse response){
		String loginName = request.getParameter("loginName");
		
		ManagerParam param = new ManagerParam();
		param.setLoginName(loginName);
		IPage<ManagerBo> managers = managerService.findListByPage(param);
		int size = 0;
		if (managers != null && null != managers.getResult() && managers.getResult().size() > 0) {
			size = managers.getResult().size();
		}
		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			pw = response.getWriter();
			pw.print(size);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}
	
	/**
	 * 进入账号管理编辑页面
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-06-02
	 */
	@RequestMapping(value="/onEdit", method=RequestMethod.POST)
	public String onEditManager(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String loginName = request.getParameter("loginName");
		String roleId = request.getParameter("roleId");
		String isOnjob = request.getParameter("isOnjob");
		String isLock = request.getParameter("isLock");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		if(StringUtil.isNotEmpty(loginName)) {
			request.setAttribute("loginName", loginName);
		}
		if(StringUtil.isNotEmpty(roleId)) {
			request.setAttribute("roleId", roleId);
		}
		if(StringUtil.isNotEmpty(isOnjob)) {
			request.setAttribute("isOnjob", isOnjob);
		}
		if(StringUtil.isNotEmpty(isLock)) {
			request.setAttribute("isLock", isLock);
		}

		int id = StringUtil.toInt(idStr);
		
		ManagerVo managerVo = managerService.findManagerById(id);
		request.setAttribute("manager", managerVo);
		
		List<RolesBo> roleList = rolesService.findRolesList(getSessionManager(request).getRoleId());
		request.setAttribute("roleList", roleList);
		
		return "manager/edit";
	}
	
	/**
	 * 编辑账号管理信息
	 * @param
	 * @param request
	 * @return String
	 * @author: cg
	 * @time: 2017-06-02
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEditManager(ManagerAddParam manager, HttpServletRequest request) {
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String loginName = request.getParameter("loginName");
		String roleId = request.getParameter("roleId");
		String isOnjob = request.getParameter("isOnjob");
		String isLock = request.getParameter("isLock");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		if(StringUtil.isNotEmpty(loginName)) {
			request.setAttribute("loginName", loginName);
		}
		if(StringUtil.isNotEmpty(roleId)) {
			request.setAttribute("roleId", roleId);
		}
		if(StringUtil.isNotEmpty(isOnjob)) {
			request.setAttribute("isOnjob", isOnjob);
		}
		if(StringUtil.isNotEmpty(isLock)) {
			request.setAttribute("isLock", isLock);
		}
		
		ManagerVo currentManager = getSessionManager(request);
		ManagerBo managerBo = manager.getManager();
		ManagerInfoBo managerInfoBo = manager.getManagerInfo();
		
		// 更新用户主表中的创建人和修改时间
		managerBo.setModifier(currentManager.getName());
		managerBo.setModifyTime(new Date());
		managerService.doEditManager(managerBo);
		// 更新用户详细信息表中的数据
		ManagerInfoBo managerInfoVo = managerInfoService.findInfoByManagerId(manager.getManager().getId());
		managerInfoVo.setIsOnjob(managerBo.getIsOnjob());
		managerInfoVo.setName(managerInfoBo.getName());
		managerInfoVo.setGender(managerInfoBo.getGender());
		managerInfoService.doEdit(managerInfoVo);

		List<RolesBo> rolesList = rolesService.findRolesList(currentManager.getRoleId());
		request.setAttribute("rolesList", rolesList);
				
		return "manager/list";
	}
	
	/**
	 * 保存重置密码
	 * @return
	 */
	@RequestMapping(value="/resetpw")
	public void editResetSavePassword(HttpServletRequest request, HttpServletResponse response) {
		ManagerBo manager = new ManagerBo();
		String newPassword = request.getParameter("newPassword");
		String id = request.getParameter("id");
		manager.setId(Integer.parseInt(id));
		manager.setPassword(MD5Util.stringToPassword(newPassword));
		managerService.doResetPassword(manager);
		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			pw = response.getWriter();
			pw.print(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}
}
