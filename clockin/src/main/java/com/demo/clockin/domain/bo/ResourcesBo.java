package com.demo.clockin.domain.bo;


import com.demo.clockin.common.domain.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * 资源
 * 
 * @author cg
 *
 * @date 2014-07-29
 */
public class ResourcesBo extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 上级资源
	 */
	private Integer parentId;
	
	private String parentName;
	
	/**
	 * 英文名称
	 */
	private String nameEn;
	
	/**
	 * 中文名称
	 */
	private String nameZh;
	
	/**
	 * 类型
	 */
	private Integer resourceType;
	/**
	 * 类型名称
	 */
	private String resourceTypeName;
	
	/**
	 * 动作
	 */
	private Integer actions;
	
	private String actionsStr;
	
	/**
	 * 访问路径
	 */
	private String defaultUri;
	
	/**
	 * 状态
	 */
	private Integer resourceStatus;
	
	/**
	 * 排序
	 */
	private Integer resourceOrder;
	
	/**
	 * 描述
	 */
	private String description;
	
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

	private List<ResourcesBo> children;
	
	/**
	 * @return the children
	 */
	public List<ResourcesBo> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ResourcesBo> children) {
		this.children = children;
	}

	public String getActionsStr() {
		return actionsStr;
	}

	public void setActionsStr(String actionsStr) {
		this.actionsStr = actionsStr;
	}

	public String getResourceTypeName() {
		return resourceTypeName;
	}
	
	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameZh() {
		return nameZh;
	}
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	public Integer getResourceType() {
		return resourceType;
	}
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
	public Integer getActions() {
		return actions;
	}
	public void setActions(Integer actions) {
		this.actions = actions;
	}
	public String getDefaultUri() {
		return defaultUri;
	}
	public void setDefaultUri(String defaultUri) {
		this.defaultUri = defaultUri;
	}
	public Integer getResourceStatus() {
		return resourceStatus;
	}
	public void setResourceStatus(Integer resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	public Integer getResourceOrder() {
		return resourceOrder;
	}
	public void setResourceOrder(Integer resourceOrder) {
		this.resourceOrder = resourceOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "Resources [parentId=" + parentId + ", parentName=" + parentName + ", nameEn=" + nameEn + ", nameZh="
				+ nameZh + ", resourceType=" + resourceType + ", resourceTypeName=" + resourceTypeName + ", actions="
				+ actions + ", actionsStr=" + actionsStr + ", defaultUri=" + defaultUri + ", resourceStatus="
				+ resourceStatus + ", resourceOrder=" + resourceOrder + ", description=" + description + ", creatorId="
				+ creatorId + ", creator=" + creator + ", createDate=" + createDate + ", children=" + children
				+ ", id=" + getId() + "]";
	}
}
