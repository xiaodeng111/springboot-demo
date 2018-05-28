package com.demo.clockin.controller.listener;


import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.service.IResourceActionService;
import com.demo.clockin.service.IResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 *@desc: 使用@WebListener注解，实现ServletContextListener接口
 *@author: dengrq
 *@time: 2018/5/23
 */
@WebListener
public class WebInitListener implements ServletContextListener {
	
	private Logger logger = LoggerFactory.getLogger(WebInitListener.class);
	
	public static WebApplicationContext springContext = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		logger.info("====================================");
		logger.info("==      Puxin Education Group     ==");
		logger.info("==     	gedu clockin	       ==");
		logger.info("====================================");
		
		springContext = WebApplicationContextUtils.getWebApplicationContext(sce
				.getServletContext());
		IResourcesService resourcesService = (IResourcesService) springContext.getBean("resourcesService");
		// 初始化所有资源（菜单目录）
		List<ResourcesBo> resList = resourcesService.selectAll();
		for (ResourcesBo res : resList) {
			for (ResourcesBo parent : resList) {
				if (res.getParentId().equals(parent.getId())) {
					res.setParentName(parent.getNameZh());
				} else if (res.getParentId() == 0) {
					res.setParentName(Constants.RESOURCE_ROOT_NAME);
				}
			}
			Constants.getResourceMap().put(res.getNameEn(), res);
		}
		// 初始化所有菜单操作权限
		IResourceActionService resourceActionService = (IResourceActionService) springContext.getBean("resourceActionService");
		List<ResourceActionBo> raList = resourceActionService.selectAll();
		for (ResourceActionBo ra : raList)
			Constants.getResourceActionMap().put(ra.getNameEn(), ra);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
