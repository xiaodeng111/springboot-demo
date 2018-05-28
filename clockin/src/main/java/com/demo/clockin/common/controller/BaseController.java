package com.demo.clockin.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.clockin.domain.vo.ManagerVo;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.vo.ManagerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 基础控制器类
 * @author dengrq
 *
 */
@Controller
@RequestMapping("/base/controller")
public class BaseController {
	public Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 获取客户端IP
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time:2017年5月1日
	 */
	public String getRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
		
	/**
	 * out put for dtgrid
	 * @param pageList
	 * @param response void
	 * @author: dengrq
	 * @time:2017年5月3日
	 */
	public void writeResponse4Ajax(IPage<?> pageList, HttpServletResponse response) {
		try {
	        response.setContentType("text/html; charset=utf-8");
	        OutputStream stream = response.getOutputStream();
	        
	        DtGridResponse pager = new DtGridResponse();
			pager.setExhibitDatas(pageList.getResult());
			pager.setNowPage(pageList.getPageNumber());
			pager.setPageCount(pageList.getTotalPages());
			pager.setRecordCount(Integer.valueOf(String.valueOf(pageList.getTotalElements())));
			pager.setPageSize(pageList.getPageSize());
			pager.setIsSuccess(true);
	        
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	        stream.write(JSON.toJSONString(pager,SerializerFeature.WriteDateUseDateFormat).getBytes("utf-8"));
	        stream.flush();
	        stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出到浏览器
	 * @param result
	 * @param response
	 */
	public void printResponse(Object result, HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			pw = response.getWriter();
			pw.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}

	public ManagerVo getManager(HttpSession session) {
		Object obj = session.getAttribute(Constants.SESSION_MANAGER);
		if(obj != null) {
			return (ManagerVo)obj;
		}

		return null;
	}
}
