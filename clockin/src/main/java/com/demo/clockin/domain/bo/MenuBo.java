package com.demo.clockin.domain.bo;


import com.demo.clockin.common.domain.BaseModel;

import java.util.Date;

/**
 * 菜单
 * 
 * @author cg
 * 
 * @date 2013-07-01
 */
public class MenuBo extends BaseModel implements Comparable<MenuBo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9214282481029935987L;

	/**
	 * 用户名
	 */
	private String menuname;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 排序
	 */
	private Integer ordernum;

	/**
	 * 创建时间
	 */
	private Date createtime;

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public int compareTo(MenuBo o) {
		return this.getOrdernum().compareTo(o.getOrdernum());
	}
}
