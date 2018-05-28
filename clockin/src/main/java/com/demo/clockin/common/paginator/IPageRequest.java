package com.demo.clockin.common.paginator;

import java.io.Serializable;

/**
 * 分页请求接口
 * 
 * @author Bobbie.Qi
 * @dateTime 2014-8-1 下午9:15:46
 */
public abstract interface IPageRequest extends Serializable {

	public abstract int getPageNumber();

	public abstract int getPageSize();

	public abstract int getOffset();

	public abstract int getIsCount();
}
