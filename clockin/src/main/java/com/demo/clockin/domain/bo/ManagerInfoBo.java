package com.demo.clockin.domain.bo;


import com.demo.clockin.common.constant.Constants;
import com.demo.clockin.common.constant.Property;
import com.demo.clockin.common.domain.BaseModel;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 
 * 
 * @author cg
 *
 * @date 2017-02-28
 */
@SuppressWarnings("serial")
public class ManagerInfoBo extends BaseModel {
	
	/**
	 * 教师账号编号
	 */
	private Integer managerId;
	
	/**
	 * 学校编号
	 */
	private String schoolCode;
	
	/**
	 * 校区名称
	 */
	private transient String schoolName;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 证件类型
	 */
	private Integer cardType;
	
	/**
	 * 证件号码
	 */
	private String cardCode;
	
	/**
	 * 微信号
	 */
	private String wechat;
	
	/**
	 * QQ
	 */
	private String qq;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 座机号码
	 */
	private String phone;
	
	/**
	 * 是否在职（1-是；0-否）
	 */
	private Integer isOnjob;
	
	/**
	 * 工作性质（1-全职；0-兼职）
	 */
	private Integer jobCategory;
	
	/**
	 * 毕业院校
	 */
	private String academies;
	
	/**
	 * 所获奖项
	 */
	private String awards;
	
	/**
	 * 从业开始时间
	 */
	private String startWork;
	
	/**
	 * 概要介绍
	 */
	private String summary;
	
	/**
	 * 详细介绍
	 */
	private String details;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 头像
	 */
	private String logo;
	
	/**
	 * 性别
	 */
	private Integer gender;
	
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
		this.schoolName = Constants.getSchoolMap().get(schoolCode);
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
		if(StringUtils.isBlank(birthday))this.birthday = null;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getIsOnjob() {
		return isOnjob;
	}
	public void setIsOnjob(Integer isOnjob) {
		this.isOnjob = isOnjob;
	}
	public Integer getJobCategory() {
		return jobCategory;
	}
	public void setJobCategory(Integer jobCategory) {
		this.jobCategory = jobCategory;
	}
	public String getAcademies() {
		return academies;
	}
	public void setAcademies(String academies) {
		this.academies = academies;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getStartWork() {
		return startWork;
	}
	public void setStartWork(String startWork) {
		this.startWork = startWork;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getLogoUrl() {
		if (!StringUtils.isNotEmpty(logo)) {
			return Property.FILE_UPLOAD_ROOTURL + Constants.separator + Property.DEFAULT_MANAGER_LOGO;
		} else {
			return Property.FILE_UPLOAD_ROOTURL + Constants.separator + logo;
		}
		
	}
	@Override
	public String toString() {
		return "ManagerInfo [managerId=" + managerId + ", schoolCode=" + schoolCode + ", name=" + name + ", birthday="
				+ birthday + ", cardType=" + cardType + ", cardCode=" + cardCode + ", wechat=" + wechat + ", qq=" + qq
				+ ", email=" + email + ", mobile=" + mobile + ", phone=" + phone + ", isOnjob=" + isOnjob
				+ ", jobCategory=" + jobCategory + ", academies=" + academies + ", awards=" + awards + ", startWork="
				+ startWork + ", summary=" + summary + ", details=" + details + ", creator=" + creator + ", createTime="
				+ createTime + ", modifier=" + modifier + ", modifyTime=" + modifyTime + ", logo=" + logo + ", gender="
				+ gender + ", id=" + getId() + "]";
	}
	
}
