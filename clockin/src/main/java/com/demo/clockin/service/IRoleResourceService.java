package com.demo.clockin.service;


import com.demo.clockin.domain.bo.RoleResourceBo;

import java.util.List;

/**
 * 角色资源 业务接口
 * 
 * @author dengrq
 *
 * @date 2014-08-04
 */
public interface IRoleResourceService {
		
	/**
	 * 根据 ID 查找 角色资源 
	 * @param roleResource
	 * @return
	 */
	public List<RoleResourceBo> findRoleResourceByRoleId(RoleResourceBo roleResource);
	
	/**
	 * 添加 角色资源 
	 * @param roleResource
	 * @return
	 */
	public int doAddSave(RoleResourceBo roleResource);
	
	/**
	 * 根据 ID 删除 角色资源 
	 * @param str
	 * @return
	 */
	public int doDeleteByRoleIds(String str);
	
}
