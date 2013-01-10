package com.czg.common;

import javax.servlet.http.HttpServletRequest;

import com.czg.bean.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
public class AdminValidateInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 登录校验拦截
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception 
	{
		HttpServletRequest request = HttpUtility.getCurrentRequest();
		
		AdminUser adminUser = HttpUtility.getAdminUser(request);
		
		if(adminUser==null){
			
			return "loginUI";
		}
		return invocation.invoke();
	}
	
}
