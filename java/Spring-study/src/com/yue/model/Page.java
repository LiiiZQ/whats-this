package com.yue.model;

public class Page {
    private int currentPage=1;
    private int rowsPerPage=3;
    private int rowsCount;
    private int totalPage;
    private String username_cx;
    
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public int getRowsCount() {
		return rowsCount;
	}
	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getUsername_cx() {
		return username_cx;
	}
	public void setUsername_cx(String username_cx) {
		this.username_cx = username_cx;
	}
    
    
}
