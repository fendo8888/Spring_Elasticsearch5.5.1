package com.fendo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fendo.entity.CsdnBlog;
import com.mysql.fabric.xmlrpc.base.Array;

public class Page{

    private int pageNo = 1;//页码，默认是第一页
    private int pageSize = 15;//每页显示的记录数，默认是15
    private int totalRecord;//总记录数
    private int totalPage;//总页数
    private String message;
    
    private List<CsdnBlog> simpleResult=new ArrayList<CsdnBlog>();//返回记录SimpleResult

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	

	public List<CsdnBlog> getSimpleResult() {
		return simpleResult;
	}

	public void setSimpleResult(List<CsdnBlog> simpleResult) {
		simpleResult = simpleResult;
	}

	public Page() {
		super();
	}

	public Page(int pageNo, int pageSize, int totalRecord, int totalPage, List<CsdnBlog> simpleResult) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		simpleResult = simpleResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
