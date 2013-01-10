package com.czg.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author fnyexx
 * 
 *	翻页公共容器
 * @param <T>
 * 首先要set 页面传来的pageNum、查询得到的recordCount、datalist 、跳转的URL。
 * 然后生成  pageNum 、pageCount 、pageStr
 * 
 * 
 */
public class Page {

 /** 当前页码*/
 private int pageNum = 1;
 /** 页总数*/
 private int pageCount = 0;
 /** 每页显示记录数 -默认10条*/
 private int tpp = 20;
 /** 总记录数*/
 private int recordCount;
 /** 是否生成标签URL(用于JAVA分页) ｜ (JS分页)  默认:生成JAVA分页*/
 private boolean isJava = true;
 /** 标签URL*/
 private String url = StringUtils.EMPTY;
 /** 页面显示链接HTML字符串*/
 private String pageStr = StringUtils.EMPTY;
 /** 查询util*/
 private DaoCriteria daoCriteria = new DaoCriteria();
 /** 数据LIST容器*/
 private List<?> dataList ;
 
public boolean isJava() {
	return isJava;
}
public void setJava(boolean isJava) {
	this.isJava = isJava;
}
public int getRecordCount() {
	return recordCount;
}
public void setRecordCount(int recordCount) {
	this.recordCount = recordCount;
}
public DaoCriteria getDaoCriteria() {
	return daoCriteria;
}
public void setDaoCriteria(DaoCriteria daoCriteria) {
	this.daoCriteria = daoCriteria;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getPageStr() {
	return pageStr;
}
public void setPageStr(String pageStr) {
	this.pageStr = pageStr;
}
public int getPageNum() {
	return pageNum;
}
public void setPageNum(int pageNum) {
	this.pageNum = pageNum;
}
public int getPageCount() {
	return pageCount;
}
public void setPageCount(int pageCount) {
	this.pageCount = pageCount;
}
public int getTpp() {
	return tpp;
}
public void setTpp(int tpp) {
	this.tpp = tpp;
}
public List<?> getDataList() {
	return dataList;
}
public void setDataList(List<?> dataList) {
	this.dataList = dataList;
}

}
