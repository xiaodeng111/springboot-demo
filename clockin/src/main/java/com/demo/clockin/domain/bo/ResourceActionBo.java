package com.demo.clockin.domain.bo;


import com.demo.clockin.common.domain.BaseModel;

import java.util.Date;

/**
 * 资源动作
 * 
 * @author cg
 *
 * @date 2014-07-31
 */
public class ResourceActionBo extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7184398108991457933L;

	/**
	 * 名称
	 */
	private String nameZh;
	
	/**
	 * 英文名称
	 */
	private String nameEn;
	
	/**
	 * BIT
	 */
	private Integer bit;
	
	/**
	 * 状态
	 */
	private Integer actionStatus;
	
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
	public String getNameZh() {
		return nameZh;
	}
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public Integer getBit() {
		return bit;
	}
	public void setBit(Integer bit) {
		this.bit = bit;
	}
	public Integer getActionStatus() {
		return actionStatus;
	}
	public void setActionStatus(Integer actionStatus) {
		this.actionStatus = actionStatus;
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
		return "ResourceAction [nameZh=" + nameZh + ", nameEn=" + nameEn + ", bit=" + bit + ", actionStatus="
				+ actionStatus + ", creatorId=" + creatorId + ", creator=" + creator + ", createDate=" + createDate
				+ ", description=" + description + ", id=" + getId() + "]";
	}
}
