package com.demo.clockin.common.multidb;

public class DatabaseContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDbType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDbType() {
        return contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
