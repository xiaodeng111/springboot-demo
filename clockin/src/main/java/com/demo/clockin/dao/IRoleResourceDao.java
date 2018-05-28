package com.demo.clockin.dao;


import com.demo.clockin.domain.bo.RoleResourceBo;
import com.demo.clockin.domain.bo.RoleResourceBo;

import java.util.List;

/**
 * 角色资源 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-04
 */
public interface IRoleResourceDao {

	/**
	 * 根据ID获取指定的角色资源信息
	 * 
	 * @param roleResource
	 * @return
	 */
	public List<RoleResourceBo> findRoleResourceByRoleId(RoleResourceBo roleResource);

	/**
	 * 添加角色资源
	 * 
	 * @param roleResource
	 * @return
	 */
	public int doAddSaveRoleResource(RoleResourceBo roleResourceBo);
	
	/**
	 * 根据角色ID删除指定的角色资源
	 * 
	 * @param str
	 * @return
	 */
	public int doDeleteByRoleIds(String str);

}
