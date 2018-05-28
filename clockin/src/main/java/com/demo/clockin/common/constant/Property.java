package com.demo.clockin.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 1、 修正代码中的@Component不可丢掉了
 * 2、 set方法要是非静态的
 *
 *@desc: 全局变量设置
 *@author: dengrq
 *@time: 2018/5/25
 */
@Component
public class Property {
	/**
	 * 系统 web 路径 (eg: http://www.mysystem.com )
	 */
	public static String BASE;

	/** request字符集 */
	public static String CHARSET="UTF-8";

	/** 公司名称 */
	public static String COMP_NAME;

	/**
	 * 系统名称
	 */
	public static String SYSTEM_NAME;
	
	/**
	 * 文件上传物理根目录
	 */
	public static String FILE_UPLOAD_ROOTPATH;
	
	/**
	 * 文件访问地址
	 */
	public static String FILE_UPLOAD_ROOTURL;
	
	/**
	 * 图片存放的子目录
	 */
	public static String FILE_IMAG_UPLOADPATH;
	
	/**
	 * 允许图片上传的最大占用空间，单位：兆（M）
	 */
	public static String FILE_IMAG_MAXSIZE;
	
	/**
	 * 允许图片上传的文件格式 多种格式之间逗号分隔
	 */
	public static String FILE_IMAG_TYPES;
		
	/**
	 * 默认头像地址
	 */
	public static String DEFAULT_MANAGER_LOGO;



	@Value("${system.base}")
	public void setBASE(String bASE) {
		BASE = bASE;
	}


	@Value("${system.charset}")
	public void setCHARSET(String cHARSET) {
		CHARSET = cHARSET;
	}


	@Value("${system.compName}")
	public void setCOMP_NAME(String cOMP_NAME) {
		COMP_NAME = cOMP_NAME;
	}

	@Value("${system.name}")
	public void setSYSTEM_NAME(String sYSTEM_NAME) {
		SYSTEM_NAME = sYSTEM_NAME;
	}



	@Value("${system.fileUploadRootPath}")
	public void setFILE_UPLOAD_ROOTPATH(String fILE_UPLOAD_ROOTPATH) {
		FILE_UPLOAD_ROOTPATH = fILE_UPLOAD_ROOTPATH;
	}


	@Value("${system.fileUploadRootUrl}")
	public void setFILE_UPLOAD_ROOTURL(String fILE_UPLOAD_ROOTURL) {
		FILE_UPLOAD_ROOTURL = fILE_UPLOAD_ROOTURL;
	}


	@Value("${system.fileImageUploadPath}")
	public void setFILE_IMAG_UPLOADPATH(String fILE_IMAG_UPLOADPATH) {
		FILE_IMAG_UPLOADPATH = fILE_IMAG_UPLOADPATH;
	}

	@Value("${system.fileImageMaxSize}")
	public void setFILE_IMAG_MAXSIZE(String fILE_IMAG_MAXSIZE) {
		FILE_IMAG_MAXSIZE = fILE_IMAG_MAXSIZE;
	}


	@Value("${system.fileImageTypes}")
	public void setFILE_IMAG_TYPES(String fILE_IMAG_TYPES) {
		FILE_IMAG_TYPES = fILE_IMAG_TYPES;
	}


	@Value("${system.defaultManagetLogo}")
	public void setDEFAULT_MANAGER_LOGO(String dEFAULT_MANAGER_LOGO) {
		DEFAULT_MANAGER_LOGO = dEFAULT_MANAGER_LOGO;
	}


}
