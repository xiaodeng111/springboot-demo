package com.demo.clockin.controller.api;

import com.alibaba.fastjson.JSON;
import com.demo.clockin.common.api.ApiResult;
import com.demo.clockin.common.api.ApiResultWapper;
import com.demo.clockin.common.api.exception.ErrorCode;
import com.demo.clockin.common.controller.BaseController;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.NewsBo;
import com.demo.clockin.domain.param.NewsParam;
import com.demo.clockin.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 新闻发现 API控制器
 * @author dengrq
 * @date 2018-02-05
 */
@Controller
@RequestMapping("/api/news")
public class NewsApiController extends BaseController {
	
	@Autowired
	private INewsService newsService;

	/**
	 * 查询新闻发现列表
	 * @param param
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public ApiResult findList(NewsParam param) {
		IPage<NewsBo> pageList = newsService.findListByPage(param);
		return ApiResultWapper.getInstance(pageList);
	}

	/**
	 * 查询新闻发现详情
	 * @param id
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public ApiResult findInfo(Integer id) {
		if(null == id || 0 >= id) {
			ErrorCode.LESS_PARAMS.issue("id");
		}
		NewsBo newsBo = newsService.findNewsById(id);
		if (newsBo == null) {
			ErrorCode.PARAMS_ERROR.issue("id");
		}
		return ApiResultWapper.getInstance(newsBo);
	}
	
	/**
	 * 保存新闻发现信息
	 * @param newsBo
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doAddNews(NewsBo newsBo) {
		newsService.doAddNews(newsBo);
		return ApiResultWapper.getVoidInstance();
	}
	
	/**
	 * 编辑新闻发现信息
	 * @param newsBo
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doEditNews(NewsBo newsBo) {
		newsService.doEditNews(newsBo);
		return ApiResultWapper.getVoidInstance();
	}
	
	/**
	 * 删除新闻发现信息
	 * @param id
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2018-02-05
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doDelNews(Integer id) {
		if(null == id || 0 >= id) {
			ErrorCode.LESS_PARAMS.issue("id");
		}
		newsService.doDelNews(id);
		return ApiResultWapper.getVoidInstance();
	}

	/**
	 * banner上下架
	 * @param
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2017-10-16
	 */
	@RequestMapping(value="/updateStatus", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doEditWordStockStatus(Integer id, Integer status) {
		NewsBo bo = new NewsBo();
		bo.setId(id);
		bo.setStatus(status);
		newsService.doEditNews(bo);
		return ApiResultWapper.getVoidInstance();
	}

	/**
	 * banner批量上下架
	 * @param
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2017-10-16
	 */
	@RequestMapping(value="/updateBatchStatus", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult doBatchEditStatus(String ids, Integer status) {
		if (StringUtil.isEmpty(ids)){
			ErrorCode.LESS_PARAMS.issue();
		}
		List<Integer> idList = JSON.parseArray(ids, Integer.class);
		if (idList.size() ==0){
			ErrorCode.LESS_PARAMS.issue();
		}

		NewsBo bo = new NewsBo();
		bo.setStatus(status);
		for (Integer id:idList) {
			bo.setId(id);
			newsService.doEditNews(bo);
		}
		return ApiResultWapper.getVoidInstance();
	}

	/**
	 * banner批量删除
	 * @param
	 * @return ApiResult
	 * @author: dengrq
	 * @time: 2017-10-16
	 */
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	@ResponseBody
	public ApiResult delBatch(String ids) {
		if (StringUtil.isEmpty(ids)){
			ErrorCode.LESS_PARAMS.issue();
		}
		List<Integer> idList = JSON.parseArray(ids, Integer.class);
		if (idList.size() ==0){
			ErrorCode.LESS_PARAMS.issue();
		}
		for (Integer id:idList) {
			newsService.doDelNews(id);
		}
		return ApiResultWapper.getVoidInstance();
	}

}