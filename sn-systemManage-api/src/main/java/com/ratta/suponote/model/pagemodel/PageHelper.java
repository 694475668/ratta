package com.ratta.suponote.model.pagemodel;

/**
 * @author page
 * easyui分页帮助类
 * 2018-10-31
 */
public class PageHelper implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 当前第几页
	 */
	private int page;
	/**
	 * 每行显示的记录数
	 */
	private int rows;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 倒序或者正序
	 */
	private String order;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "PageHelper [page=" + page + ", rows=" + rows + ", sort=" + sort
				+ ", order=" + order + "]";
	}

}
