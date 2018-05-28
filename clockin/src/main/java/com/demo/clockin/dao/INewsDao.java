package com.demo.clockin.dao;

import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.NewsBo;
import com.demo.clockin.domain.param.NewsParam;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.NewsBo;
import com.demo.clockin.domain.param.NewsParam;
import org.apache.ibatis.annotations.Param;
/**
 * 新闻发现 Dao 接口
 * @author cg
 * @since 2018-02-07
 */
public interface INewsDao {
		
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
	 * @param newsParam
	 * @param pageRequest
	 * @return IPage<NewsBo>
	 * @author: cg
	 * @time: 2018-02-07
	 */
	public IPage<NewsBo> findListByPage(@Param("param") NewsParam newsParam, PageRequest pageRequest);
	
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
