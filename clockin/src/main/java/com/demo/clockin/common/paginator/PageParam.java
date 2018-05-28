package com.demo.clockin.common.paginator;

import java.io.Serializable;

public class PageParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2211610545543589865L;
	private Integer pageNumber;
	private Integer pageSize;
	private String schoolCode;
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
}
