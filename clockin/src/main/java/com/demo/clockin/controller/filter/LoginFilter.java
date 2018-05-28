package com.demo.clockin.controller.filter;

import com.alibaba.fastjson.JSON;
import com.demo.clockin.common.api.ApiResult;
import com.demo.clockin.common.api.exception.ErrorCode;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.constant.Property;
import com.demo.clockin.domain.vo.ManagerVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用@WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 *@author: dengrq
 *@time: 2018/5/23
 */
@WebFilter(urlPatterns = { "/*" }, filterName = "loginFilter",
	initParams={@WebInitParam(name="exclusions",value=".plugins,.mp4,.js,.gif,.jpg,.png,.css,.ico,/login,/logout,/") //忽略资源
 })
@Order(value = 1)  //多个过滤器的加载顺序
public class LoginFilter implements Filter {
	
	protected final static Log LOG = LogFactory.getLog(LoginFilter.class);
	public static final String PARAM_NAME_EXCLUSIONS	 	= "exclusions";
	public static final String PATH_LOGIN 					= "/jsp/common/noRights.jsp";
	private Set<String>        excludesPattern;
	protected String           contextPath;
	public static final String STATIC_RESOURCE              = "/jsp/common";

	@Override
	public void init(FilterConfig config) throws ServletException {
		{
            String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
            if (exclusions != null && exclusions.trim().length() != 0) {
                excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
            }
        }
		
		this.contextPath = config.getServletContext().getContextPath();
		LOG.info(LoginFilter.class.getName() + " init.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
		
        String requestURI = httpRequest.getRequestURI();
		
        if (isExclusion(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

		HttpSession session = httpRequest.getSession();
		Object uid = session.getAttribute(Constants.SESSION_MANAGER);
		if (uid == null) {
			httpResponse.sendRedirect(contextPath + PATH_LOGIN);
		} else {
			// 验证权限
			if(hasRights(httpRequest, (ManagerVo)uid, requestURI)) {
				chain.doFilter(request, response);
			} else {
				// 没有仅限
				if (httpRequest.getHeader("X-Requested-With") == null ? false : true){
					writeNoAuthResp(response);
				}else{
					httpResponse.sendRedirect(contextPath + PATH_LOGIN);
				}
			}
		}
	}
	
	/**
	 * 排除不验证的URI
	 * @param requestURI
	 * @return
	 */
	private boolean isExclusion(String requestURI) {
        if (excludesPattern == null) {
            return false;
        }

        if (contextPath != null && requestURI.startsWith(contextPath)) {
            requestURI = requestURI.substring(contextPath.length());
            if (!requestURI.startsWith("/")) {
                requestURI = "/" + requestURI;
            }
        }

        for (String pattern : excludesPattern) {
            if (requestURI.endsWith(pattern)) {
                return true;
            }
        }

        return false;
    }
	
	/**
	 * 检查是否有权限操作
	 * @param request
	 * @param manager
	 * @param requestURI
	 * @return
	 */
	private boolean hasRights(HttpServletRequest request, ManagerVo manager, String requestURI) {
        
		if(manager.getRoleId() != null && manager.getRoleId().intValue() == 1){
        	return true;
        }

        if(requestURI == null || requestURI == ""){
			return false;
		}

        if(requestURI.contains(STATIC_RESOURCE) || requestURI.contains("list4ajax")|| requestURI.contains("delUserInfo")
				||requestURI.contains("upload")|| requestURI.contains("jsp/controller.jsp")|| requestURI.contains("getWeChatUrl")){
			return true;
		}
        
        if(isExclusion(requestURI)) {
        	return true;
        }

        requestURI = requestURI.substring(contextPath.length(),requestURI.length());

        if (manager != null && manager.getPermissionsMap() != null) {
            if (manager.getPermissionsMap().containsKey(requestURI)) {
                return true;
            }
        } else {
            request.getSession().invalidate();
            return false;
        }
            
        return false;
    }

	@Override
	public void destroy() {
		LOG.info(LoginFilter.class.getName() + " destroy.");
	}

	private void writeNoAuthResp(ServletResponse response) throws IOException {
		ApiResult apiResult = new ApiResult();
		apiResult.setStatus(ErrorCode.LIMIT_NOT_PERMISSION.getNum());
		apiResult.setMessage(ErrorCode.LIMIT_NOT_PERMISSION.getDesc());
		response.setContentType("application/json");
		OutputStream stream = response.getOutputStream();
		stream.write(JSON.toJSONString(apiResult).getBytes("utf-8"));
		stream.flush();
		stream.close();
	}
	
}
