package com.demo.clockin.service;


import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.NewsBo;
import com.demo.clockin.domain.param.NewsParam;

/**
 * 新闻发现 业务接口
 * @author cg
 * @date 2018-02-07
 */
public interface INewsService {

	/**
	 * 根据编号查询新闻发现信息
	 * @param id
	 * @return NewsBo
	 * @author: cg
	 * @time: 2018-02-07
	 */
	public NewsBo findNewsById(Integer id);

	/**
	 * 分页查询新闻发现列表
	 * @param param
	 * @return IPage<SchoolBo>
	 * @author: cg
	 * @time: 2018-02-07
	 */
	public IPage<NewsBo> findListByPage(NewsParam param);
	
	/**
	 * 添加新闻发现
	 * @param newsBo void
	 * @author: cg
	 * @time: 2018-02-07
	 */
	public void doAddNews(NewsBo newsBo);
	
	/**
	 * 编辑新闻发现
	 * @param newsBo void
	 * @author: cg
	 * @time: 2018-02-07
	 */
	public void doEditNews(NewsBo newsBo);
	
	/**
	 * 根据编号删除新闻发现
	 * @param id void
	 * @author: cg
	 * @time: 2018-02-07
	 */
	public void doDelNews(Integer id);

}
