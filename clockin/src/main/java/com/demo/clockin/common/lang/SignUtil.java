package com.demo.clockin.common.lang;

import java.util.*;

public class SignUtil {

	/**
	 * 签名算法
	 * @param params
	 * @return
	 */
	public static String getBaseSign(Map<String, Object> params, String appKey) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys, new Comparator<String>() {
			public int compare(String a, String b) {
				return a.toString().toLowerCase().compareTo(b.toString().toLowerCase());
			}
		});
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			if(null != params.get(key)) {
				sb.append(key.toLowerCase()).append("=");
			    sb.append(params.get(key)).append("&");
			}
		}
		sb.append("appkey=").append(appKey);
		String mySign = MD5Util.getMD5String(sb.toString());
		return mySign;
	}

}
