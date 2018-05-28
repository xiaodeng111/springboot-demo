package com.demo.clockin.common.constant;

import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.bo.ResourcesBo;
import com.demo.clockin.domain.bo.ResourceActionBo;
import com.demo.clockin.domain.bo.ResourcesBo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 静态参数配置
 * 
 * @author dengrq
 *
 */
public class Constants {
	
	/**
	 *	通用数据字典 status 1启用 2禁用
	 */
	public static final int COMMONS_DICT_STATUS_YES = 1;
	public static final int COMMONS_DICT_STATUS_NO 	= 2;
	
	// 根目录显示名称
	public static final String RESOURCE_ROOT_NAME	= "ROOT";
	
	// 用户状态 未锁定
	public static final int MANAGER_STATE_UNLOCKED 	= 0;
	// 用户状态 已锁定
	public static final int MANAGER_STATE_LOCKED 	= 1;
	// 目录分隔符
	public static final String separator 			= "/";

	/**
	 * 初始化系统默认资源类型 1菜单 2目录 3模块
	 */
	public static final Integer RESOURCES_MENU_TYPE= 1;
	public static final Integer RESOURCES_CATALOG_TYPE= 2;
	public static final Integer RESOURCES_MODULE_TYPE= 3;

	/**
	 * 保存session的对象名称
	 */
	public static final String SESSION_MANAGER = "manager";

	// 所有资源权限
	private static Map<String, ResourcesBo> resourceMap = new HashMap<String,ResourcesBo>();
	// 左右资源操作权限
	private static Map<String, ResourceActionBo> resourceActionMap = new HashMap<String,ResourceActionBo>();
	// 校区code对应校区名称
	private static Map<String, String> schoolMap = new HashMap<String, String>();

	/**
	 * @return the resourceMap
	 */
	public static Map<String, ResourcesBo> getResourceMap() {
		return resourceMap;
	}

	/**
	 * @return the resourceActionMap
	 */
	public static Map<String, ResourceActionBo> getResourceActionMap() {
		return resourceActionMap;
	}

	/**
	 * @return getSchoolMap
	 */
	public static Map<String, String> getSchoolMap() {
		return schoolMap;
	}
	
}
