package com.demo.clockin.controller.action;


import com.demo.clockin.common.controller.BaseController;
import com.demo.clockin.domain.vo.ManagerVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseProController extends BaseController {
	/**
	 * 通过session获取manager对象
	 * @param request
	 * @return ManagerVo
	 * @author: dengrq
	 * @time:2017年6月26日
	 */
	public ManagerVo getSessionManager(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (ManagerVo)session.getAttribute("manager");
	}
}
