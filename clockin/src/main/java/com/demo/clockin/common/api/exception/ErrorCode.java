package com.demo.clockin.common.api.exception;

public interface ErrorCode extends SystemErrorCode {
	/**
	 * 常用名词
	 * 
	 * ILLEGAL : 错误、非法（可预料）
	 * ERROR : 错误、异常（不可预料）
	 * UNABLE : 不可用（无能力的；不能胜任的）
	 * FAILED : 失败了的，不成功的
	 * LIMIT : 限制
	 */
	/**
	 * 文件大小超过限制
	 */
	public static final ErrorInfo FILE_BIG_SIZE		= ErrorInfo.getInstance(40003, "FILE_BIG_SIZE", "文件超过上传大小");
	/**
	 * 输入不能包含
	 */
	public static final ErrorInfo PARAMS_NO_MATCHE	= ErrorInfo.getInstance(40005, "PARAMS_NO_MATCHE", "输入不能包含(' \" %)等字符");
	/**
	 * 当前文件类型不允许上传
	 */
	public static final ErrorInfo FILE_NO_TYPES		= ErrorInfo.getInstance(40004, "FILE_NO_TYPES", "当前文件类型不允许上传");

	/** 验证码错误 */
	public static final ErrorInfo ILLEGAL_AUTH_VERIFYCODE 		= ErrorInfo.getInstance(30001, "ILLEGAL_AUTH_VERIFYCODE", "验证码错误");

	/** 无权限 */
	public static final ErrorInfo LIMIT_NOT_PERMISSION 		= ErrorInfo.getInstance(30002, "LIMIT_NOT_PERMISSION", "无权限，请联系管理员！！！");

	/** 图片上传失败 */
	public static final ErrorInfo UPLOAD_IMAGE_FAILED 			= ErrorInfo.getInstance(30004, "UPLOAD_IMAGE_FAILED", "图片上传失败");

}
