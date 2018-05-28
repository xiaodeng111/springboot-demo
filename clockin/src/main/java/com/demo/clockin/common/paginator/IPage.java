package com.demo.clockin.common.paginator;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果接口
 * 
 * @author Bobbie.Qi
 * @dateTime 2014-8-1 下午9:15:59
 * @param <Entity>
 */
public abstract interface IPage<Entity extends Serializable> extends Serializable {

	abstract List<Entity> getResult();

	abstract int getPageNumber();
	
	abstract int getPageSize();
	
	abstract int getTotalPages();

	abstract int getNumberOfElements();

	abstract long getTotalElements();

	abstract boolean hasPreviousPage();

	abstract boolean isFirstPage();

	abstract boolean hasNextPage();

	abstract boolean isLastPage();

	abstract boolean hasResult();
}
