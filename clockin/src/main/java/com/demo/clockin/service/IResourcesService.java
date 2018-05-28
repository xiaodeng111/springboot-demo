package com.demo.clockin.service;

import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.domain.param.ResourcesParam;

import java.util.List;

/**
 * 资源 业务接口
 * 
 * @author dengrq
 *
 * @date 2014-07-29
 */
public interface IResourcesService {
		
	/**
	 * 查询所有 资源
	 * @return
	 */
	public List<ResourcesBo> selectAll();
	
	/**
	 * 根据编号查询资源管理信息
	 * @param id
	 * @return ResourcesBo
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	public ResourcesBo findResourcesById(Integer id);

	/**
	 * 分页查询资源管理列表
	 * @param param
	 * @return IPage<SchoolBo>
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	public IPage<ResourcesBo> findListByPage(ResourcesParam param);
	
	/**
	 * 添加资源管理
	 * @param resourcesBo void
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	public void doAddResources(ResourcesBo resourcesBo);
	
	/**
	 * 编辑资源管理
	 * @param resourcesBo void
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	public void doEditResources(ResourcesBo resourcesBo);
	
	/**
	 * 根据编号删除资源管理
	 * @param id void
	 * @author: dengrq
	 * @time: 2017-05-31
	 */
	public void doDelResources(Integer id);
	
}
