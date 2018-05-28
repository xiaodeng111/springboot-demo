package com.demo.clockin.common.constant;

/**
 * 接口返回常量值
 * @author:dengrq
 * @time:2017年2月12日
 */
public class ApiReturnConstant {
	// 公用参数 成功
	public static String SUCCESS = "success";
	// 公用参数 失败
	public static String FAILURE = "failure";
	
	/**
	 * API返回数据
	 */
	public static String API_RESULT_DATA 			= "data";
	/**
	 * API返回的状态值，1-成功；0-失败
	 */
	public static String API_RESULT_STATUS 			= "status";
	/**
	 * API返回的错误码
	 */
	public static String API_RESULT_ERRCODE 		= "errCode";
	
	/**
	 * 是否显示错误信息(1-抛出接口返回的message)
	 */
	public static String API_IS_SHOW_ERR			= "isShowErr";
	/**
	 * API返回的信息
	 */
	public static String API_RESULT_MESSAGE 		= "message";
	/**
	 * 调用接口返回结果 成功
	 */
	public static int OPERATION_STATUS_SUCCESS 		= 1;
	/**
	 * 调用接口返回结果 失败
	 */
	public static int OPERATION_STATUS_FAILUE 		= 0;
	
	/**
	 * 显示服务端错误信息
	 */
	public static String SHOW_SERVERS_ERR_MESSAGE 	= "1";
}
