package com.demo.clockin.common.paginator;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果实现类
 * 
 * @author Bobbie.Qi
 * @dateTime 2014-8-1 下午9:07:27
 * @param <Entity>
 */
public class Page<Entity extends Serializable> implements IPage<Entity> {

	private static final long serialVersionUID = -5707283913969397010L;

	/** 结果集 */
	private List<Entity> result;
	/** 页码 */
	private int pageNumber;
	/** 每页记录数 */
	private int pageSize;
	/** 总页数 */
	private int totalPages;
	/** 当前页记录数 */
	private int numberOfElements;
	/** 总记录数 */
	private long totalElements;
	
	public Page() {
	}

	public Page(List<Entity> result, int pageNumber, int pageSize, long totalElements) {
		this.result = result;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPages = (int) (totalElements % pageSize == 0 ? totalElements / pageSize : (totalElements / pageSize + 1));
		this.numberOfElements = result == null ? 0 : result.size();
		this.totalElements = totalElements;
	}
	public Page(long totalElements,int pageSize){
		this.totalElements=totalElements;
		this.pageSize=pageSize;
	}

	public List<Entity> getResult() {
		return this.result;
	}

	public void setResult(List<Entity> result) {
		this.result = result;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public int getNumberOfElements() {
		return this.numberOfElements;
	}

	public long getTotalElements() {
		return this.totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean hasPreviousPage() {
		return 1 != pageNumber;
	}

	public boolean isFirstPage() {
		return 1 == pageNumber;
	}

	public boolean hasNextPage() {
		return totalPages != pageNumber;
	}

	public boolean isLastPage() {
		return totalPages == pageNumber;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public boolean hasResult() {
		if (result == null || result.isEmpty()) {
			return false;
		}
		return true;
	}
}
