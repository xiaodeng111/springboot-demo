package com.demo.clockin.domain.param;


import com.demo.clockin.common.paginator.PageParam;

/**
 * 角色查询对象
 * @author:Bobbie.Qi
 * @time:2017年5月1日
 */
public class RolesParam extends PageParam {

	private static final long serialVersionUID = -4610413117495509103L;
	/**
	 * 名称
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
