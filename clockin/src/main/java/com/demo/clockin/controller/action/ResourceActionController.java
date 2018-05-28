package com.demo.clockin.controller.action;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.lang.DateUtil;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.param.ResourceActionParam;
import com.demo.clockin.service.IResourceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源动作控制器
 * @author dengrq
 * @date 2017-06-01
 */
@Controller
@RequestMapping("/admin/resourceAction")
public class ResourceActionController extends BaseProController {
	@Autowired
	private IResourceActionService resourceActionService;

	/**
	 * 进入资源动作列表
	 * @return String
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	 @RequestMapping(value="/resourceActionAction", method=RequestMethod.GET)
	 public String onResourceActionList() {
		return "resourceAction/list";
	}

	/**
	 * 查询资源动作列表
	 * @param request
	 * @param response void
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	@RequestMapping(value="/list4ajax")
	public void findResourceActionList4Ajax(HttpServletRequest request, HttpServletResponse response) {
		ResourceActionParam param = new ResourceActionParam();
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

            String nameZh = JSON.parseObject(parameters).getString("nameZh");

            if(StringUtil.isNotEmpty(nameZh)) {
				param.setNameZh(nameZh);
			}
		}
		
		param.setPageNumber(StringUtil.isNotEmpty(pageNumber) ? Integer.valueOf(pageNumber) : 1);
		param.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.valueOf(pageSize) : 15);
		
		IPage<ResourceActionBo> pageList = resourceActionService.findListByPage(param);
				
		writeResponse4Ajax(pageList, response);
	}

	/**
	 * 进入添加资源动作页面
	 * @return String
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	@RequestMapping(value="/onAdd", method=RequestMethod.GET)
	public String onAddResourceAction() {
		return "resourceAction/add";
	}
	
	/**
	 * 保存资源动作信息
	 * @param schoolBo
	 * @return String
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAddResourceAction(ResourceActionBo resourceActionBo, HttpServletRequest request) {
		resourceActionBo.setCreatorId(this.getSessionManager(request).getId());
		resourceActionBo.setCreator(this.getSessionManager(request).getName());
		resourceActionBo.setCreateDate(DateUtil.getNowDateTime());
		int maxBit = resourceActionService.findMaxBit();
		resourceActionBo.setBit(maxBit > 0 ? maxBit * 2 : 1);
		resourceActionService.doAddResourceAction(resourceActionBo);
		Constants.getResourceActionMap().put(resourceActionBo.getNameEn(), resourceActionBo);
		
		return "redirect:/admin/resourceAction/resourceActionAction";
	}

	/**
	 * 进入资源动作编辑页面
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	@RequestMapping(value="/onEdit", method=RequestMethod.POST)
	public String onEditResourceAction(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

		int id = StringUtil.toInt(idStr);
		
		ResourceActionBo resourceActionBo = resourceActionService.findResourceActionById(id);
		request.setAttribute("resourceAction", resourceActionBo);
		
		return "resourceAction/edit";
	}
	
	/**
	 * 编辑资源动作信息
	 * @param resourceActionBo
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEditResourceAction(ResourceActionBo resourceActionBo, HttpServletRequest request) {
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		
		ResourceActionBo resourceActionVO = resourceActionService.findResourceActionById(resourceActionBo.getId());
		
		resourceActionBo.setCreatorId(this.getSessionManager(request).getId());
		resourceActionBo.setCreator(this.getSessionManager(request).getName());
		resourceActionBo.setCreateDate(DateUtil.getNowDateTime());
		resourceActionService.doEditResourceAction(resourceActionBo);
		
		Constants.getResourceActionMap().remove(resourceActionVO.getNameEn());
		Constants.getResourceActionMap().put(resourceActionBo.getNameEn(), resourceActionBo);
		
		return "resourceAction/list";
	}
	
	/**
	 * 删除资源动作信息
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	@RequestMapping(value="/doDel", method=RequestMethod.POST)
	public String doDelResourceAction(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

        String nameZh = request.getParameter("nameZh");
        request.setAttribute("nameZh", nameZh);
		
		int id = StringUtil.toInt(idStr);
		
		resourceActionService.doDelResourceAction(id);
		
		return "resourceAction/list";
	}
}
