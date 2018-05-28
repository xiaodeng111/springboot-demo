package com.demo.clockin.service.impl;

import com.demo.clockin.common.api.exception.ErrorCode;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.dao.INewsDao;
import com.demo.clockin.domain.bo.NewsBo;
import com.demo.clockin.domain.param.NewsParam;
import com.demo.clockin.service.INewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 新闻发现 业务接口 INewsService 实现类
 * @author dengrq
 * @date 2018-02-07
 */
@Service("newsService")
public class NewsServiceImpl implements INewsService {

	@Resource
	private INewsDao newsDao;

	@Override
	public NewsBo findNewsById(Integer id) {
		return newsDao.findNewsById(id);
	}

	@Override
	public IPage<NewsBo> findListByPage(NewsParam param) {
		PageRequest pageRequest = new PageRequest(param.getPageNumber(), param.getPageSize());
		return newsDao.findListByPage(param, pageRequest);
	}

	@Override
	public void doAddNews(NewsBo newsBo) {
		newsDao.doAddNews(newsBo);
	}

	@Override
	public void doEditNews(NewsBo newsBo) {
		newsDao.doEditNews(newsBo);
	}

	@Override
	public void doDelNews(Integer id) {
		newsDao.doDelNews(id);
	}

}
