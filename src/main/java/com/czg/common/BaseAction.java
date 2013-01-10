package com.czg.common;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletContextAware, ServletResponseAware,ServletRequestAware, SessionAware {
    
	private static final long serialVersionUID = 6360364286882740874L;
	protected ServletContext servletContext;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession httpSession;
	protected Map<String, Object> session;
	protected boolean isPost=HttpUtility.isPost();//当前页面是否被POST请求
    protected boolean isGet=HttpUtility.isGet(); //当前页面是否被GET请求

	protected WebApplicationContext getApplicationContext()
	{			
		 return WebApplicationContextUtils.getWebApplicationContext(servletContext);	 
	}	 
		
	/**
	 * 判断当前页面是否接收到了Post请求
	 * @return 是否接收到了Post请求
	 */
	protected  boolean isPost() 
	{
		return request.getMethod().equalsIgnoreCase("post");	
	}
	
	
	/**
	 * 判断当前页面是否接收到了Get请求
	 * @return 是否接收到了Get请求
	 */
	protected boolean isGet()
	{
		return request.getMethod().equalsIgnoreCase("get");		
	}
	
	
	public void setServletContext(ServletContext servletContext) 
	{
		this.servletContext=servletContext;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}

	public void setServletRequest(HttpServletRequest request) 
	{
		this.request=request;
	}
    
	public HttpServletRequest getServletRequest()
	{
		return this.request;
	}
		
	public void setSession(Map<String, Object> session) 
	{
		this.session=session;
	}
}
