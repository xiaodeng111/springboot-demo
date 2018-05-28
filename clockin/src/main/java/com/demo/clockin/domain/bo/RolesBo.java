package com.demo.clockin.domain.bo;

import com.demo.clockin.common.domain.BaseModel;

import java.util.Date;

/**
 * 角色
 * 
 * @author cg
 *
 * @date 2014-08-04
 */
public class RolesBo extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8600761594146699654L;

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 状态
	 */
	private Integer roleStatus;
	
	/**
	 * 创建人ID
	 */
	private Integer creatorId;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 描述
	 */
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Roles [name=" + name + ", roleStatus=" + roleStatus + ", creatorId=" + creatorId + ", creator="
				+ creator + ", createDate=" + createDate + ", description=" + description + ", id=" + getId()
				+ "]";
	}
}
