package com.demo.clockin.controller.action;


import com.demo.clockin.common.controller.BaseController;
import com.demo.clockin.common.lang.MD5Util;
import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.bo.ManagerInfoBo;
import com.demo.clockin.domain.vo.ManagerVo;
import com.demo.clockin.service.IManagerInfoService;
import com.demo.clockin.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
	
	@Autowired
	private IManagerService managerService;
	
	@Autowired
	private IManagerInfoService managerInfoService;
		
	/**
	 * 跳转修改密码页面
	 * @return String
	 * @author: dengrq
	 * @time:2017年4月27日
	 */
	@RequestMapping(value = "/onChange", method = RequestMethod.GET)
	public String onChange() {
		return "account/change";
	}
	
	/**
	 * 修改密码
	 * @return String
	 * @author: dengrq
	 * @time:2017年4月27日
	 */
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public String change(HttpServletRequest request, Model model) {
		// 执行保存密码的操作
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		ManagerBo manager = (ManagerBo)request.getSession().getAttribute("manager");
		ManagerBo bo = new ManagerBo();
		bo.setLoginName(manager.getLoginName());
		bo.setPassword(MD5Util.stringToPassword(password));
		ManagerVo m = managerService.findManager4Login(bo);
		if (password != null && m != null) {
			manager.setPassword(MD5Util.stringToPassword(newPassword));
			managerService.doResetPassword(manager);
			model.addAttribute("errmsg", "密码修改成功，重新登录生效");
		} else {
			model.addAttribute("errmsg", "原密码错误，请重新输入");
		}
		return "account/change";
	}
	
	/**
	 * 跳转个人信息修改页面
	 * @return String
	 * @author: dengrq
	 * @time:2017年4月28日
	 */
	@RequestMapping(value = "/onEdit", method = RequestMethod.GET)
	public String onEdit(HttpServletRequest request) {
		ManagerBo m = (ManagerBo)request.getSession().getAttribute("manager");
		ManagerInfoBo mi = managerInfoService.findInfoByManagerId(m.getId());
		request.setAttribute("managerInfo", mi);
		return "account/managerEdit";
	}
	
	/**
	 * 保存个人信息
	 * @param managerInfo
	 * @return String
	 * @author: dengrq
	 * @time:2017年5月1日
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(ManagerInfoBo managerInfo) {
		managerInfoService.doEdit(managerInfo);
		return "redirect:/account/onEdit";
	}
}
