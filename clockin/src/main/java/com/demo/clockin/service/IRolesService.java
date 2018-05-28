package com.demo.clockin.service;


import com.demo.clockin.common.paginator.IPage;
import com.demo.clockin.domain.bo.RolesBo;
import com.demo.clockin.domain.param.RolesParam;

import java.util.List;

/**
 * 角色 业务接口
 * 
 * @author dengrq
 *
 * @date 2014-08-04
 */
public interface IRolesService {
		
	/**
	 * 根据 ID 查找 角色 
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
	public IPage<RolesBo> findRoles(RolesParam param);
	
	/**
	 * 添加 角色 
	 * @param roles
	 * @return
	 */
	public int doAddSave(RolesBo roles);
	
	/**
	 * 修改 角色 
	 * @param roles
	 * @return
	 */
	public int doEditSave(RolesBo roles);
	
}
