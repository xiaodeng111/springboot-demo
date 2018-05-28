package com.demo.clockin.common.cache;

public class CacheObject {
	private String key; // 缓存ID 
	private Object value;// 缓存数据 
	private Long timeOut;// 过期时间（ms） 
	
	public CacheObject() {
	}

	public CacheObject(String key, Object value, Long timeOut) {
		super();
		this.key = key;
		this.value = value;
		setTimeOut(timeOut);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Long timeOut) {
		if (timeOut != null) {
			this.timeOut = System.currentTimeMillis() + timeOut;
		}
	}
}
