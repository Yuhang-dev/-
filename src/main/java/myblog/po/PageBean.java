package main.java.myblog.po;

import java.util.List;

/**
 * 
 * 负责显示页面列表显示的bean，封装一页所需的列表对象
 * 
 * @author Yuhang
 *
 * @param <T> 可以是FoodBean,CultureBean,ResortsBean
 */

public class PageBean<T> {
	private int currentPage;
	private List<T> tableList=null;
	private int pageSize=2;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getTableList() {
		return tableList;
	}
	public void setTableList(List<T> tableList) {
		this.tableList = tableList;
	}
	
}
