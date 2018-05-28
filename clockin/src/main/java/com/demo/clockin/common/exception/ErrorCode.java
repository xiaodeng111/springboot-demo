package com.demo.clockin.common.exception;

/**
 * 错误码
 * @author:Bobbie.Qi
 * @time:2017年2月10日
 */
public enum ErrorCode {
	/**
     * 常用名词
     * 
     * ILLEGAL : 错误、非法（可预料）
     * ERROR : 错误、异常（不可预料）
     * UNABLE : 不可用（无能力的；不能胜任的）
     * FAILED : 失败了的，不成功的
     * LIMIT : 限制
     */
	
	/* 系统级，代码中请勿调用 */
	UN_KNOWN_EXCEPTION("100001", "未知错误"),
	SYSTEM_ERROR("100002", "系统错误"),
	DATABASE_EXCEPTION("100003", "数据库异常"),
	DATABASE_EXECUTE_EXCEPTION("100004", "数据库执行异常"),
	DATA_CONVERT_EXCEPTION("100005", "数据转换异常"),
	CONNECTION_REFUSED("100006", "拒绝访问"),
	SYSTEM_RESOURCES_UNABLE("100007", "服务端资源不可用"),
	ILLEGAL_REQUEST("100008", "非法请求"),
	API_EXECUTE_FAILED("100009", "API执行错误"),
	ILLEGAL_SIGN("100010", "非法签名"),
	OVERDUE_REQUEST("100011", "过期请求"),
	/* 非业务类 */
	CONNECTION_LIMIT("200001", "无权调用API"),
	API_NOT_EXISTS("200002", "API不存在"),
	API_DISCARDED("200003", "API已经废弃"),
	LESS_PARAMS("200005", "缺少参数(%s)，请参考API文档"),
	PARAMS_ERROR("200006", "参数(%s)错误，请参考API文档"),
	PARAMS_FORMAT_ERROR("200007", "参数(%s)格式错误"),
	/* 业务类 */
	/* 301 UCI*/
	ILLEGAL_AUTH_VERIFYCODE("301001", "验证码错误"),
	OVERDUE_AUTH_VERIFYCODE("301002", "验证码已过期"),
	ILLEGAL_TOO_FREQUENTLY_CODE("301003", "请勿频繁请求验证码"),
	ILLEGAL_MOBILE_NOT_EXISTS("301004", "手机号码不存在"),
	ILLEGAL_USER_NOT_REGISTER("301005", "用户未注册"),
	ILLEGAL_USERNAME_OR_PASSWORD("301006", "用户名或密码错误"),
	ILLEGAL_USER_EXISTS("301007", "账号已存在，请直接登陆"),
	ILLEGAL_USER_ISBIND("301008", "(%s)已绑定(%s)手机号，请联系学校老师先进行解绑"),
	/* 302 CARI*/
	ILLEGAL_ADD_CART("302001", "重复添加购物车"),
	/* 303 ORDERI*/
	ILLEGAL_CREATE_ORDER("303001", "创建待支付订单失败"),
	OVERDUE_ORDER("303002", "订单已过期"),
	FAILED_CHECK_CLASS("303003", "(%s)课程已经报满，不能继续报名"),
	ILLEGAL_USED_COUPON("303004", "优惠券已使用"),
	ILLEGAL_ORDER_CLASS_INVALID("303005", "重新报名失败，班级已无效"),
	ILLEGAL_ORDER_CLASS_NOOPEN("303006", "班级未开启窗口期"),
	/* 304 PAYI*/
	ILLEGAL_CREATE_THIRD_ORDER("304001", "创建第三方支付订单错误"),
	/* 305 SMS */
	ILLEGAL_REQUEST_TOO_OFTEN("305001", "请求太频繁，请稍后再试");
	
	private String errCode;
	
	private String message;
	
	private ErrorCode(String errCode, String message) {
		this.errCode = errCode;
		this.message = message;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
