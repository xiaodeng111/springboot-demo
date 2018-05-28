package com.demo.clockin.service;


import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.param.ResourceActionParam;

import java.util.List;

/**
 * 资源动作 业务接口
 * 
 * @author dengrq
 *
 * @date 2014-07-31
 */
public interface IResourceActionService {
	
	/**
	 * 查询所有 资源动作
	 * @return
	 */
	public List<ResourceActionBo> selectAll();
	
	/**
	 * 获取当前系统资源动作最大位值
	 * 
	 * @return 最大位值
	 */
	public int findMaxBit();
	
	/**
	 * 根据编号查询资源动作信息
	 * @param id
	 * @return ResourceActionBo
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	public ResourceActionBo findResourceActionById(Integer id);

	/**
	 * 分页查询资源动作列表
	 * @param param
	 * @return IPage<SchoolBo>
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	public IPage<ResourceActionBo> findListByPage(ResourceActionParam param);
	
	/**
	 * 添加资源动作
	 * @param resourceActionBo void
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	public void doAddResourceAction(ResourceActionBo resourceActionBo);
	
	/**
	 * 编辑资源动作
	 * @param resourceActionBo void
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	public void doEditResourceAction(ResourceActionBo resourceActionBo);
	
	/**
	 * 根据编号删除资源动作
	 * @param id void
	 * @author: dengrq
	 * @time: 2017-06-01
	 */
	public void doDelResourceAction(Integer id);
	
}
