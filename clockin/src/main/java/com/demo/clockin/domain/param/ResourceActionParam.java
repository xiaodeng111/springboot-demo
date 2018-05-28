package com.demo.clockin.domain.param;


import com.demo.clockin.common.paginator.PageParam;

public class ResourceActionParam extends PageParam {
	/**
	 * @Description:TODO
	 */
	private static final long serialVersionUID = 4041331581794624417L;
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
}
