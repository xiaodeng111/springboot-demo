package com.demo.clockin.controller.action;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.lang.DateUtil;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.domain.param.ResourcesParam;
import com.demo.clockin.service.IResourceActionService;
import com.demo.clockin.service.IResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin/resources")
public class ResourcesController extends BaseProController {
	@Autowired
	private IResourcesService resourcesService;
	
	@Autowired
	private IResourceActionService resourceActionService;

	/**
	 * 进入资源管理列表
	 * @return String
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	 @RequestMapping(value="/resourcesAction", method=RequestMethod.GET)
	 public String onResourcesList() {
		return "resources/list";
	}

	/**
	 * 查询资源管理列表
	 * @param request
	 * @param response void
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	@RequestMapping(value="/list4ajax")
	public void findResourcesList4Ajax(HttpServletRequest request, HttpServletResponse response) {
		try {
			ResourcesParam param = new ResourcesParam();
			String pageNumber 	= null;
			String pageSize 	= null;
			
			String dtGridPager = request.getParameter("dtGridPager");
			if(StringUtil.isNotEmpty(dtGridPager)) {
				String pageSizeStr 	= JSON.parseObject(dtGridPager).getString("pageSize");
				String pageNoStr 	= JSON.parseObject(dtGridPager).getString("nowPage");
				String parameters 	= JSON.parseObject(dtGridPager).getString("parameters");

	            // 用于保持当前页
				pageNumber 		= JSONObject.parseObject(parameters).getString("nowPage");
				pageNumber 		= StringUtil.isEmpty(pageNumber) ? pageNoStr : pageNumber;
				pageSize 		= JSONObject.parseObject(parameters).getString("pageSize");
				pageSize 		= StringUtil.isEmpty(pageSize) ? pageSizeStr : pageSize;
				
				request.setAttribute("nowPage", pageNumber);
				request.setAttribute("pageSize", pageSize);

	            String nameZh = JSON.parseObject(parameters).getString("searchNameZh");

	            if(StringUtil.isNotEmpty(nameZh)) {
					param.setNameZh(nameZh);
					request.setAttribute("searchNameZh", nameZh);
				}
			}
			
			param.setPageNumber(StringUtil.isNotEmpty(pageNumber) ? Integer.valueOf(pageNumber) : 1);
			param.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.valueOf(pageSize) : 15);
			
			IPage<ResourcesBo> pageList = resourcesService.findListByPage(param);
			
			List<ResourceActionBo> actionList = resourceActionService.selectAll();
			String charcter = ";";
			for (ResourcesBo r : pageList.getResult()) {
				for (ResourcesBo rm : Constants.getResourceMap().values()) {
					if (rm.getId().equals(r.getParentId()))
						r.setParentName(rm.getNameZh());
				}
				r.setActionsStr(null);
				for (ResourceActionBo ra : actionList) {
					if ((ra.getBit() & r.getActions()) > 0) {
						r.setActionsStr(new StringBuilder()
								.append(null != r.getActionsStr() ? r
										.getActionsStr() : "")
								.append(ra.getNameZh()).append(charcter).toString());

					}
				}
				if (r.getParentId().equals(0))
					r.setParentName(Constants.RESOURCE_ROOT_NAME);
				r.setActionsStr(org.apache.commons.lang.StringUtils.removeEnd(
						r.getActionsStr(), charcter));
			}
			
			writeResponse4Ajax(pageList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进入添加资源管理页面
	 * @return String
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	@RequestMapping(value="/onAdd", method=RequestMethod.GET)
	public String onAddResources(HttpServletRequest request) {
		preResources(request);
		return "resources/add";
	}
	
	/**
	 * 保存资源管理信息
	 * @param schoolBo
	 * @return String
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAddResources(ResourcesBo resourcesBo, HttpServletRequest request) {
		if (null == resourcesBo.getParentId())
			resourcesBo.setParentId(0);
		if (null == resourcesBo.getResourceOrder())
			resourcesBo.setResourceOrder(0);
		resourcesBo.setCreatorId(this.getSessionManager(request).getId());
		resourcesBo.setCreator(this.getSessionManager(request).getName());
		resourcesBo.setCreateDate(DateUtil.getNowDateTime());
		resourcesService.doAddResources(resourcesBo);
		Constants.getResourceMap().put(resourcesBo.getNameEn(), resourcesBo);
		
		return "redirect:/admin/resources/resourcesAction";
	}

	/**
	 * 进入资源管理编辑页面
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	@RequestMapping(value="/onEdit", method=RequestMethod.POST)
	public String onEditResources(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String nameZh = request.getParameter("searchNameZh");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("searchNameZh", nameZh);

		int id = StringUtil.toInt(idStr);
				
		ResourcesBo resVO = null;
		ResourcesBo parentRsour = null;
		for (ResourcesBo res : Constants.getResourceMap().values()) {
			if (res.getId().equals(id)) {
				resVO = res;
			}
		}
		for (ResourcesBo res : Constants.getResourceMap().values()) {
			if (res.getId().equals(resVO.getParentId())) {
				parentRsour = res;
			}
		}
		String charcter = ";";
		Collection<ResourceActionBo> actionList = Constants
				.getResourceActionMap().values();
		if (null != resVO) {
			resVO.setActionsStr(null);
			StringBuilder actionStrBuilder = new StringBuilder("");
			StringBuilder actionBitStr = new StringBuilder("");
			for (ResourceActionBo ra : actionList) {
				if ((ra.getBit() & resVO.getActions()) > 0) {
					actionStrBuilder.append(actionStrBuilder.toString().equals(
							"") ? ra.getId() : charcter + ra.getId());
					actionBitStr.append(actionBitStr.toString().equals("") ? ra
							.getNameZh() : charcter + ra.getNameZh());
				}
			}
			resVO.setActionsStr(actionStrBuilder.toString());
			request.setAttribute("actionBit", actionBitStr.toString());
			request.setAttribute("resources", resVO);
			String resourcesjson = JSON.toJSONString(resVO);
			String parentRsourjson = JSON.toJSONString(parentRsour);
			request.setAttribute("resourcesjson", resourcesjson);
			request.setAttribute("parentRsourjson", parentRsourjson);
		}
		preResources(request);
		
		return "resources/edit";
	}
	
	/**
	 * 编辑资源管理信息
	 * @param resourcesBo
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEditResources(ResourcesBo resourcesBo, HttpServletRequest request) {
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String nameZh = request.getParameter("searchNameZh");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("searchNameZh", nameZh);
		
		ResourcesBo tmp = null;
		for (ResourcesBo r : Constants.getResourceMap().values()) {
			if (r.getId().equals(resourcesBo.getId()))
				tmp = r;
		}
		if (null == resourcesBo.getParentId())
			resourcesBo.setParentId(0);
		if (null == resourcesBo.getResourceOrder())
			resourcesBo.setResourceOrder(0);
		resourcesBo.setCreatorId(this.getSessionManager(request).getId());
		resourcesBo.setCreator(this.getSessionManager(request).getName());
		resourcesBo.setCreateDate(DateUtil.getNowDateTime());
		resourcesService.doEditResources(resourcesBo);
		if (null != tmp && null != Constants.getResourceMap().get(tmp.getNameEn()))
			Constants.getResourceMap().remove(tmp.getNameEn());
		Constants.getResourceMap().put(resourcesBo.getNameEn(), resourcesBo);
		
		return "resources/list";
	}
	
	/**
	 * 删除资源管理信息
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	@RequestMapping(value="/doDel", method=RequestMethod.POST)
	public String doDelResources(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

        String parentId = request.getParameter("parentId");
        request.setAttribute("parentId", parentId);
		
		int id = StringUtil.toInt(idStr);
		
		resourcesService.doDelResources(id);
		
		return "resources/list";
	}
	
	private void preResources(HttpServletRequest request) {
		Collection<ResourcesBo> rList = Constants.getResourceMap().values();
		List<ResourcesBo> resourcesList = new ArrayList<ResourcesBo>();
		for (ResourcesBo r : rList) {
			if (r.getResourceStatus().equals(Constants.COMMONS_DICT_STATUS_YES))
				resourcesList.add(r);
		}
		String resjson = JSONObject.toJSONString(resourcesList);
		request.setAttribute("resjson", resjson);
		Collection<ResourceActionBo> raList = Constants.getResourceActionMap().values();
		List<ResourceActionBo> actionList = new ArrayList<ResourceActionBo>();
		for (ResourceActionBo ra : raList) {
			if (ra.getActionStatus().equals(Constants.COMMONS_DICT_STATUS_YES))
				actionList.add(ra);
		}
		request.setAttribute("actionjson", actionList);
	}
}
