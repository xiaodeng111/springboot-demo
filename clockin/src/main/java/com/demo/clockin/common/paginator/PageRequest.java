package com.demo.clockin.common.paginator;

/**
 * 分页请求实现类
 * 
 * @author Bobbie.Qi
 * @dateTime 2014年7月21日 下午2:22:21
 */
public class PageRequest implements IPageRequest {

	private static final long serialVersionUID = -2739913407064653846L;
	
	private static final int DEFAULT_PAGENUMBER = 1;
	private static final int DEFAULT_PAGESIZE = 15;

	/** 是否统计 默认：0 0每次都统计 1：第一次请求时统计 2：不统计 */
	private int isCount;
	/** 偏移量 */
	private int offset;
	/** 页码 */
	private int pageNumber;
	/** 每页记录数 */
	private int pageSize;

	public PageRequest(int isCount, int offset, Integer pageNumber, Integer pageSize) {
		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGESIZE;
		}

		if (pageNumber == null || pageNumber < 1) {
			pageNumber = DEFAULT_PAGENUMBER;
		}

		this.isCount = isCount;
		this.offset = offset;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public PageRequest(Integer pageNumber, Integer pageSize) {
		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGESIZE;
		}

		if (pageNumber == null || pageNumber < 1) {
			pageNumber = DEFAULT_PAGENUMBER;
		}

		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.offset = (pageNumber - 1) * pageSize;
	}
	
	public PageRequest(PageParam pageParam) {
		Integer pageNumber = pageParam == null ? null : pageParam.getPageNumber();
		Integer pageSize = pageParam == null ? null : pageParam.getPageSize();
		
		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGESIZE;
		}

		if (pageNumber == null || pageNumber < 1) {
			pageNumber = DEFAULT_PAGENUMBER;
		}

		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.offset = (pageNumber - 1) * pageSize;
	}

	@Override
	public int getIsCount() {
		return isCount;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public String toString() {
		return "PageRequest [isCount=" + isCount + ", offset=" + offset
				+ ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + "]";
	}
}
