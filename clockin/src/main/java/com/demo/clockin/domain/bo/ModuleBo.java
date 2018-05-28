package com.demo.clockin.domain.bo;


import com.demo.clockin.common.domain.BaseModel;

import java.util.Date;

/**
 * 模块
 * 
 * @author cg
 * 
 * @date 2013-05-23
 */
public class ModuleBo extends BaseModel implements Comparable<ModuleBo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1982479684687888839L;

	/**
	 * 模块名称
	 */
	private String modulename;

	/**
	 * 模块URL
	 */
	private String moduleurl;

	/**
	 * 模块Action
	 */
	private String moduleact;

	/**
	 * 所属目录ID
	 */
	private Integer catalogId;

	/**
	 * 模块状态
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date createtime;

	private String idStr;
	
	private Integer order ;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getModuleurl() {
		return moduleurl;
	}

	public void setModuleurl(String moduleurl) {
		this.moduleurl = moduleurl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public String getModuleact() {
		return moduleact;
	}

	public void setModuleact(String moduleact) {
		this.moduleact = moduleact;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public int compareTo(ModuleBo o) {
		return this.getOrder().compareTo(o.getOrder());
	}

}
