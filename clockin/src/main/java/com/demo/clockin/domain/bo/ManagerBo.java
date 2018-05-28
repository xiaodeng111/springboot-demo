package com.demo.clockin.domain.bo;


import com.demo.clockin.common.domain.BaseModel;

import java.util.Date;

/**
 * 
 * 
 * @author cg
 *
 * @date 2017-02-27
 */
@SuppressWarnings("serial")
public class ManagerBo extends BaseModel {
	
	/**
	 * 学校编号
	 */
	private String schoolCode;
	
	/**
	 * 登录账号
	 */
	private String loginName;
	
	/**
	 * 密码
	 */
	private String password;
	
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
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 最后登录IP
	 */
	private String lastLoginIp;
	
	/**
	 * 邮箱
	 */
	private String email;
		
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 修改昵称的时间
	 */
	private Date modifyNicknameTime;
	
	/**
	 * 是否可以修改昵称，1-可以，0-不可以
	 */
	private String editNicknameCode;
		
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getModifyNicknameTime() {
		return modifyNicknameTime;
	}

	public void setModifyNicknameTime(Date modifyNicknameTime) {
		this.modifyNicknameTime = modifyNicknameTime;
	}

	public String getEditNicknameCode() {
		return editNicknameCode;
	}

	public void setEditNicknameCode(String editNicknameCode) {
		this.editNicknameCode = editNicknameCode;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
