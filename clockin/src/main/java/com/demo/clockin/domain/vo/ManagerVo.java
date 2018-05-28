package com.demo.clockin.domain.vo;


import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.constant.Property;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.domain.bo.ManagerBo;
import com.demo.clockin.domain.bo.RoleResourceBo;

import java.util.Map;

public class ManagerVo extends ManagerBo {
	/**
	 * @Description:TODO
	 */
	private static final long serialVersionUID = -200098260819671601L;
	
	private String email;
	private Integer gender;
	private String logo;
	private String mobile;
	private String name;
	private String nickname;
	
	/**
	 * 当前用户权限
	 */
	private transient Map<String,RoleResourceBo> permissionsMap;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Map<String, RoleResourceBo> getPermissionsMap() {
		return permissionsMap;
	}

	public void setPermissionsMap(Map<String, RoleResourceBo> permissionsMap) {
		this.permissionsMap = permissionsMap;
	}
	
	public String getLogoUrl() {
		if (!StringUtil.isNotEmpty(logo)) {
			return Property.FILE_UPLOAD_ROOTURL + Constants.separator + Property.DEFAULT_MANAGER_LOGO;
		} else {
			return Property.FILE_UPLOAD_ROOTURL + Constants.separator + logo;
		}
	}

}
