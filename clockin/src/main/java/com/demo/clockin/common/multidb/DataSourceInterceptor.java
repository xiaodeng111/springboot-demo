package com.demo.clockin.common.multidb;

import org.aspectj.lang.JoinPoint;

public class DataSourceInterceptor {
	/** 数据源切换常量 */
    public static final String DATASOURCE_UC_DB="ucDataSource";
    public static final String DATASOURCE_UK_DB="ukDataSource";
    public static final String DATASOURCE_ERP_DB="erpDataSource";


    /**
     * 设置数据源为UC数据库所对应的数据源。
     * @param jp
     */
    public void setUcDataSource(JoinPoint jp) {
        DatabaseContextHolder.setDbType(DATASOURCE_UC_DB);
    }
    
    /**
     * 设置数据源为UK数据库所对应的数据源。
     * @param jp
     */
    public void setUkDataSource(JoinPoint jp) {
        DatabaseContextHolder.setDbType(DATASOURCE_UK_DB);
    }
    
    /**
     * 设置数据源为ERP数据库所对应的数据源。
     * @param jp
     */
    public void setErpDataSource(JoinPoint jp) {
    	DatabaseContextHolder.setDbType(DATASOURCE_ERP_DB);
    }
}
