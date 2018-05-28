package com.demo.clockin.dao;

import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.domain.param.ResourcesParam;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.domain.param.ResourcesParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 资源 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-07-29
 */
public interface IResourcesDao {

	/**
	 * 查询所有资源
	 * 
	 * @return
	 */
	public List<ResourcesBo> selectAll();
	
	/**
	 * 根据编号查询资源管理信息
	 * @param id
	 * @return ResourcesBo
	 * @author: cg
	 * @time: 2017-05-31
	 */
	public ResourcesBo findResourcesById(Integer id);

	/**
	 * 分页查询资源管理列表
	 * @param resourcesParam
	 * @param pageRequest
	 * @return IPage<ResourcesBo>
	 * @author: cg
	 * @time: 2017-05-31
	 */
	public IPage<ResourcesBo> findListByPage(@Param("param") ResourcesParam resourcesParam, PageRequest pageRequest);
	
	/**
	 * 添加资源管理
	 * @param resourcesBo void
	 * @author: cg
	 * @time: 2017-05-31
	 */
	public void doAddResources(ResourcesBo resourcesBo);
		
	/**
	 * 编辑资源管理
	 * @param resourcesBo void
	 * @author: cg
	 * @time: 2017-05-31
	 */
	public void doEditResources(ResourcesBo resourcesBo);
	
	/**
	 * 根据编号删除资源管理
	 * @param id void
	 * @author: cg
	 * @time: 2017-05-31
	 */
	public void doDelResources(Integer id);
}
