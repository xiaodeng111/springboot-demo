package com.demo.clockin.controller.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.clockin.common.controller.BaseController;
import com.demo.clockin.common.lang.DateUtil;
import com.demo.clockin.common.lang.HtmlUtil;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.common.lang.StringUtils;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.NewsBo;
import com.demo.clockin.domain.param.NewsParam;
import com.demo.clockin.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 新闻发现控制器
 * @author dengrq
 * @date 2018-02-05
 */
@Controller
@RequestMapping("/admin/news")
public class NewsController extends BaseController {
	
	@Autowired
	private INewsService newsService;

	/**
	 * 进入新闻发现列表
	 * @return String
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	 @RequestMapping(value="/onList/{type}", method=RequestMethod.GET)
	 public String onNewsList(HttpServletRequest request, @PathVariable Integer type) {
	 	if(type.intValue() == 1){
			request.setAttribute("title","banner管理");
		}else if(type.intValue() == 2){
			request.setAttribute("title","最新资讯管理");
		}else if(type.intValue() == 3){
			request.setAttribute("title","热门课程管理");
		}else{
			request.setAttribute("title","推荐管理");
		}
		 request.setAttribute("type",type);
		return "news/list";
	}

	/**
	 * 查询新闻发现列表
	 * @param request
	 * @param response void
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/list4ajax/{type}")
	public void findNewsList4Ajax(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer type) {
		NewsParam param = new NewsParam();
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

            String name = JSON.parseObject(parameters).getString("name");
            if(StringUtil.isNotEmpty(name)) {
				param.setName(name);
			}

			String status = JSON.parseObject(parameters).getString("status");
			if(StringUtil.isNotEmpty(status) && !StringUtils.EMPTY.equals(status)){
				param.setStatus(Integer.valueOf(status));
			}

			param.setType(type);
		}
		
		param.setPageNumber(StringUtil.isNotEmpty(pageNumber) ? Integer.valueOf(pageNumber) : 1);
		param.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.valueOf(pageSize) : 15);
		
		IPage<NewsBo> pageList = newsService.findListByPage(param);
				
		writeResponse4Ajax(pageList, response);
	}

	/**
	 * 进入添加新闻发现页面
	 * @return String
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/onAdd/{type}", method=RequestMethod.GET)
	public String onAddNews(@PathVariable Integer type, HttpServletRequest request) {
		if(type.intValue() == 1){
			request.setAttribute("title","banner");
		}else if(type.intValue() == 2){
			request.setAttribute("title","最新资讯");
		}
		else if(type.intValue() == 3){
			request.setAttribute("title","热门课程");
		}else{
			request.setAttribute("title","推荐");
		}
		request.setAttribute("type",type);
		return "news/add";
	}
	
	/**
	 * 保存新闻发现信息
	 * @param newsBo
	 * @return String
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/add/{type}", method=RequestMethod.POST)
	public String doAddNews(NewsBo newsBo,@PathVariable Integer type) {
		if(type.intValue()==1){
			if(StringUtil.isEmpty(newsBo.getJumpUrl())){
				newsBo.setJumpUrl(StringUtil.EMPTY);
			}
			newsBo.setContent(StringUtil.EMPTY);
		}else if(type.intValue() ==2 ){
			newsBo.setJumpUrl(StringUtil.EMPTY);
			if(StringUtil.isEmpty(newsBo.getImgeUrl())){
				newsBo.setImgeUrl(StringUtil.EMPTY);
			}
			newsBo.setContent(HtmlUtil.replaceImgVideoHtml(newsBo.getContent()));
		}else{
			newsBo.setContent(StringUtil.EMPTY);
		}
		newsBo.setType(type);
		newsBo.setCreateTime(DateUtil.getYearMonthDate());
		newsService.doAddNews(newsBo);
		return "redirect:/admin/news/onList/"+type;
	}

	/**
	 * 进入新闻发现编辑页面
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/onEdit", method=RequestMethod.POST)
	public String onEditNews(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

		if(StringUtil.isNotEmpty(status) && !StringUtils.EMPTY.equals(status)){
			request.setAttribute("status", status);
		}
		if(StringUtil.isNotEmpty(name) && !StringUtils.EMPTY.equals(name)){
			request.setAttribute("name", name);
		}

		int id = StringUtil.toInt(idStr);

		NewsBo newsBo = newsService.findNewsById(id);
		if(StringUtil.isNotEmpty(newsBo.getContent())){
			newsBo.setContent(HtmlUtil.addImgVideoDomain(newsBo.getContent()));
		}
		request.setAttribute("news", newsBo);
		if(newsBo.getType().intValue() == 1){
			request.setAttribute("title","banner");
		}else if(newsBo.getType().intValue() == 2){
			request.setAttribute("title","最新资讯");
		}else if(newsBo.getType().intValue() == 3){
			request.setAttribute("title","热门课程");
		}else{
			request.setAttribute("title","推荐");
		}
		return "news/edit";
	}
	
	/**
	 * 编辑新闻发现信息
	 * @param news
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String doEditNews(NewsBo news, HttpServletRequest request) {
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("commonName");
		String status = request.getParameter("commonStatus");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("type",news.getType());
		if(StringUtil.isNotEmpty(status) && !StringUtils.EMPTY.equals(status)){
			request.setAttribute("status", status);
		}
		if(StringUtil.isNotEmpty(name) && !StringUtils.EMPTY.equals(name)){
			request.setAttribute("name", name);
		}

		if(StringUtil.isNotEmpty(news.getContent())){
			news.setContent(HtmlUtil.replaceImgVideoHtml(news.getContent()));
		}
		newsService.doEditNews(news);
		
		return "news/list";
	}
	
	/**
	 * 删除新闻发现信息
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/doDel/{type}", method=RequestMethod.POST)
	public String doDelNews(HttpServletRequest request, @PathVariable Integer type) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);
		if(StringUtil.isNotEmpty(status) && !StringUtils.EMPTY.equals(status)){
			request.setAttribute("status", status);
		}
		if(StringUtil.isNotEmpty(name) && !StringUtils.EMPTY.equals(name)){
			request.setAttribute("name", name);
		}
		request.setAttribute("type", type);
		int id = StringUtil.toInt(idStr);
		
		newsService.doDelNews(id);
		
		return "news/list";
	}


	/**
	 * 进入单词库详情页面
	 * @param request
	 * @return String
	 * @author: dengrq
	 * @time: 2017-10-14
	 */
	@RequestMapping(value="/onDetail", method=RequestMethod.POST)
	public String onDetailWordStock(HttpServletRequest request) {
		String idStr = request.getParameter("id");
		String nowPage = request.getParameter("nowPage");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		// 传递参数
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("pageSize", pageSize);

		if(StringUtil.isNotEmpty(status) && !StringUtils.EMPTY.equals(status)){
			request.setAttribute("status", status);
		}
		if(StringUtil.isNotEmpty(name) && !StringUtils.EMPTY.equals(name)){
			request.setAttribute("name", name);
		}

		int id = StringUtil.toInt(idStr);

		NewsBo newsBo = newsService.findNewsById(id);
		if(StringUtil.isNotEmpty(newsBo.getContent())){
			newsBo.setContent(HtmlUtil.addImgVideoDomain(newsBo.getContent()));
		}
		request.setAttribute("news", newsBo);
		if(newsBo.getType().intValue() == 1){
			request.setAttribute("detailTitle","banner");
		}else if(newsBo.getType().intValue() == 2){
			request.setAttribute("detailTitle","最新资讯");
		}else if(newsBo.getType().intValue() == 3){
			request.setAttribute("detailTitle","热门课程");
		}else{
			request.setAttribute("detailTitle","推荐");
		}

		return "news/detail";
	}

}