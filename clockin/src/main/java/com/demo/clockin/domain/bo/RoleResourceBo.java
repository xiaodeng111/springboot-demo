package com.demo.clockin.domain.bo;

import com.demo.clockin.common.domain.BaseModel;

/**
 * 角色资源
 * 
 * @author cg
 *
 * @date 2014-08-04
 */
public class RoleResourceBo extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7024130571278262810L;

	/**
	 * ROLE_ID
	 */
	private Integer roleId;
	
	/**
	 * REOURCE_ID
	 */
	private Integer reourceId;
	
	/**
	 * ACTION_BIT
	 */
	private Integer actionBit;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getReourceId() {
		return reourceId;
	}
	public void setReourceId(Integer reourceId) {
		this.reourceId = reourceId;
	}
	public Integer getActionBit() {
		return actionBit;
	}
	public void setActionBit(Integer actionBit) {
		this.actionBit = actionBit;
	}
}
