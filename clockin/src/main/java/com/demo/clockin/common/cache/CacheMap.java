package com.demo.clockin.common.cache;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CacheMap
 * @author:Bobbie.Qi
 * @time:2017年2月12日
 */
public class CacheMap {

	private static ConcurrentMap<String, CacheObject> cacheMap = new ConcurrentHashMap<String, CacheObject>();
	private static final Logger LOG = Logger.getLogger(CacheMap.class.toString());
	
	static {
		/*
		 * 自动清理 线程: 防止缓存超时未能及时清理造成内存浪费或溢出
		 */
		Thread t = new Thread(new Runnable() {
			@SuppressWarnings({ "rawtypes" })
			@Override
			public void run() {
				while (true) {
					if (cacheMap != null && !cacheMap.isEmpty()) {
						for (Iterator it = cacheMap.entrySet().iterator(); it.hasNext();) {
							Entry entry = (Entry) it.next();
							CacheObject cache = (CacheObject) entry.getValue();
							if (cache.getTimeOut() != null && cache.getTimeOut() < System.currentTimeMillis()) {
								LOG.log(Level.INFO, "CacheMap KEY: " + entry.getKey() + " be deleted.");
								it.remove();
							}
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.setDaemon(true);
		t.start(); 
	}

	public static void put(String key, Object value) {
		CacheObject cache = new CacheObject(key, value, null);
		cacheMap.put(key, cache);
	}

	public static void put(String key, Object value, long timeOut) {
		CacheObject cache = new CacheObject(key, value, timeOut);
		cacheMap.put(key, cache);
	}

	public static Object get(String key) {
		CacheObject cache = cacheMap.get(key);
		if (cache == null) {
			return null;
		}
		if (cache.getTimeOut() != null && cache.getTimeOut() < System.currentTimeMillis()) {
			remove(key);
			return null;
		}
		return cache.getValue();
	}

	public static void remove(String key) {
		cacheMap.remove(key);
	}
}
