package com.demo.clockin.dao;

import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.param.ResourceActionParam;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.param.ResourceActionParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 资源动作 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-07-31
 */
public interface IResourceActionDao {

	/**
	 * 查询所有资源动作
	 * 
	 * @return
	 */
	public List<ResourceActionBo> selectAll();
	
	/**
	 * 获取最大位值
	 */
	public Integer findMaxBit();
	
	/**
	 * 根据编号查询资源动作信息
	 * @param id
	 * @return ResourceActionBo
	 * @author: cg
	 * @time: 2017-06-01
	 */
	public ResourceActionBo findResourceActionById(Integer id);

	/**
	 * 分页查询资源动作列表
	 * @param resourceActionParam
	 * @param pageRequest
	 * @return IPage<ResourceActionBo>
	 * @author: cg
	 * @time: 2017-06-01
	 */
	public IPage<ResourceActionBo> findListByPage(@Param("param") ResourceActionParam resourceActionParam, PageRequest pageRequest);
	
	/**
	 * 添加资源动作
	 * @param resourceActionBo void
	 * @author: cg
	 * @time: 2017-06-01
	 */
	public void doAddResourceAction(ResourceActionBo resourceActionBo);
		
	/**
	 * 编辑资源动作
	 * @param resourceActionBo void
	 * @author: cg
	 * @time: 2017-06-01
	 */
	public void doEditResourceAction(ResourceActionBo resourceActionBo);
	
	/**
	 * 根据编号删除资源动作
	 * @param id void
	 * @author: cg
	 * @time: 2017-06-01
	 */
	public void doDelResourceAction(Integer id);
}
