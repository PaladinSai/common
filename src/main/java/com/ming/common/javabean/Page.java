package com.ming.common.javabean;

/**
 * 类名：PageBean<br>
 * 功能：页码管理对象<br>
 * 版本：1.0<br>
 * 日期：2017-01-10<br>
 * 说明：暂无
 * @author Ming
 *
 */
public class Page {
	/** 当前页 */
	private Integer pageNum;
	/** 总页数 */
	private Integer pageCount;
	/** 每页数据数 */
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer rowCount) {
		this.pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public boolean pageNumRight() {
		return this.pageNum <= this.pageCount && this.pageNum > 0;
	}
}
