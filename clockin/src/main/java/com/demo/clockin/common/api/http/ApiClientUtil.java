package com.demo.clockin.common.api.http;

import com.alibaba.fastjson.JSONObject;
import com.demo.clockin.common.api.ApiResult;
import com.demo.clockin.common.lang.StringUtil;
import com.demo.clockin.common.api.ApiResult;
import com.demo.clockin.common.lang.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Map;

public abstract class ApiClientUtil {
	
	private static final Log logger = LogFactory.getLog(ApiClientUtil.class);
	
	public static ApiResult doPost(String method, Map<String, Object> params) throws Exception {
		String jsonStr = null;
		try {
			jsonStr = HttpclientPoolUtil.post(method, params);
			return JSONObject.parseObject(jsonStr, ApiResult.class);
		} catch (Exception e) {
			logger.error("############" + jsonStr, e);
			throw e;
		}
	}
	
	public static ApiResult doGet(String method, Map<String, Object> params) throws Exception {
		String jsonStr = null;
		try {
			jsonStr = HttpclientPoolUtil.get(method, params);
			return JSONObject.parseObject(jsonStr, ApiResult.class);
		} catch (Exception e) {
			logger.error("############" + jsonStr, e);
			throw e;
		}
	}
	
	public static String getApiUri(String host, String port, String method) {
		StringBuilder sb = null;
		if(host.contains("http")) {
			sb = new StringBuilder();
		} else {
			sb = new StringBuilder("http://");
		}
		sb.append(host);
		if(StringUtil.isNotEmpty(port) && !"80".equals(port)) {
			sb.append(":").append(port);
		}
		sb.append(method);
		return sb.toString();
	}
}
