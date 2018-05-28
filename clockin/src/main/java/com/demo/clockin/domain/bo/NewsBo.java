package com.demo.clockin.domain.bo;


import com.demo.clockin.common.domain.BaseModel;

/**
 * 新闻发现
 * @author cg
 * @date 2018-02-07
 */
@SuppressWarnings("serial")
public class NewsBo extends BaseModel {
	
	/**
	 * 标题
	 */
	private String name;
	
	/**
	 * 图片链接
	 */
	private String imgeUrl;
	
	/**
	 * 跳转链接
	 */
	private String jumpUrl;
	
	/**
	 * 简介（副标题）
	 */
	private String descIntroduction;
	
	/**
	 * UE富文本内容
	 */
	private String content;
	
	/**
	 * 顺序号
	 */
	private Integer oderNum;
	
	/**
	 * 状态（0-下架；1-上架）
	 */
	private Integer status;
	
	/**
	 * TYPE
	 */
	private Integer type;
	
	/**
	 * 创建时间
	 */
	private String createTime;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getImgeUrl() {
		return imgeUrl;
	}
	
	public void setImgeUrl(String imgeUrl) {
		this.imgeUrl = imgeUrl;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}
	
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getDescIntroduction() {
		return descIntroduction;
	}
	
	public void setDescIntroduction(String descIntroduction) {
		this.descIntroduction = descIntroduction;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOderNum() {
		return oderNum;
	}
	
	public void setOderNum(Integer oderNum) {
		this.oderNum = oderNum;
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

	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
