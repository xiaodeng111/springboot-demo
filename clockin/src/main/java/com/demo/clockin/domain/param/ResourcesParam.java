package com.demo.clockin.domain.param;

import com.demo.clockin.common.paginator.PageParam;

public class ResourcesParam extends PageParam {
	/**
	 * @Description:TODO
	 */
	private static final long serialVersionUID = 8586718899868743411L;

	/**
	 * 上级资源
	 */
	private Integer parentId;
	
	/**
	 * 名称
	 */
	private String nameZh;

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
