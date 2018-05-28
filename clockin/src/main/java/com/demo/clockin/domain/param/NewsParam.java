package com.demo.clockin.domain.param;


import com.demo.clockin.common.paginator.PageParam;

/**
 * 新闻发现查询对象
 * @author cg
 * @date 2018-02-07
 */
 @SuppressWarnings("serial")
public class NewsParam extends PageParam {
	/**
	 * 标题
	 */
	private String name;

	/**
	 * 状态（0-下架；1-上架）
	 */
	private Integer status;

	/**
	 * TYPE
	 */
	private Integer type;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
