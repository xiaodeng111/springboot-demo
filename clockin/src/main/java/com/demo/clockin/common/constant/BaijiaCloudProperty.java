package com.demo.clockin.common.constant;

public class BaijiaCloudProperty {
	public static String UN_READ = "0"; //未读
	public static String READ = "1"; //已读
	public static String DEFAULT_TITLE = "视频处理失败通知";
	
	public static String STD = "std"; //标清
	public static String HIGH = "high"; //高清
	public static String SUPER = "super"; //超清
	
	public static String COMMA = ","; //英文逗号
	
	public static String CALLBACK_URL;

	public static String getCALLBACK_URL() {
		return CALLBACK_URL;
	}

	public static void setCALLBACK_URL(String cALLBACK_URL) {
		CALLBACK_URL = cALLBACK_URL;
	}
}
