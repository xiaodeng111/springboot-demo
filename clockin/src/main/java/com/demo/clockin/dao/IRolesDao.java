package com.demo.clockin.dao;

import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.RolesBo;
import com.demo.clockin.domain.param.RolesParam;
import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.common.paginator.PageRequest;
import com.demo.clockin.domain.bo.RolesBo;
import com.demo.clockin.domain.param.RolesParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-04
 */
public interface IRolesDao {

	/**
	 * 根据ID获取指定的角色信息
	 * 
	 * @param roles
	 * @return
	 */
	public RolesBo findRolesById(RolesBo roles);
	
	/**
	 * 根据用户角色查询角色列表
	 * @param roleId
	 * @return List<RolesBo>
	 * @author: Bobbie.Qi
	 * @time:2017年4月28日
	 */
	public List<RolesBo> findRolesList(Integer roleId);
	
	/**
	 * 根据条件查询角色列表
	 * @param param
	 * @return IPage<RolesBo>
	 * @author: Bobbie.Qi
	 * @time:2017年5月1日
	 */
	public IPage<RolesBo> findRolesByPage(@Param("param") RolesParam param, PageRequest pageRequest);
	
	/**
	 * 添加角色
	 * 
	 * @param roles
	 * @return
	 */
	public int doAddSaveRoles(RolesBo rolesBo);
	
	/**
	 * 修改角色
	 * 
	 * @param roles
	 * @return
	 */
	public int doEditSaveRoles(RolesBo rolesBo);
}
