package com.demo.clockin.domain.param;

import com.demo.clockin.common.paginator.PageParam;

/**
 * 管理员查询条件类
 * @author:Bobbie.Qi
 * @time:2017年4月26日
 */
public class ManagerParam extends PageParam {

	private static final long serialVersionUID = -4267675884072170215L;

	/**
	 * 学校编码
	 */
	private String schoolCode;
	/**
	 * 登录账号
	 */
	private String loginName;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 是否在职（1-是；0-否）
	 */
	private Integer isOnjob;
	/**
	 * 是否锁定（1-是；0-否）
	 */
	private Integer isLock;

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getIsOnjob() {
		return isOnjob;
	}

	public void setIsOnjob(Integer isOnjob) {
		this.isOnjob = isOnjob;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
}
