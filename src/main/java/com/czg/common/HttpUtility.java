package com.czg.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.czg.bean.AdminUser;

public class HttpUtility 
{
	/**
	 * 获取登录员工的信息
	 * @param request
	 * @return*/
	
	public static AdminUser getAdminUser() 
	{
		
		if(getCurrentRequest()!=null)
		{
			return (AdminUser)getCurrentRequest().getSession().getAttribute("adminUser");
		}
		return null;
	}
	
	/**
	 * 获取登录员工的信息
	 * @param request
	 * @return*/
	
	public static AdminUser getAdminUser(HttpServletRequest request) 
	{
		
		if(request!=null)
		{
			return (AdminUser)request.getSession().getAttribute("adminUser");
		}
		return null;
	} 
	
	
	/**
	 * 获取网站指定的cookie值,并使用utf-8解码
	 * @param name 项
	 * @return
	 */
	 public static String getCookieValue(String cookiename)
	 {
		if( !Utils.StringIsNullOrEmpty(cookiename) )
		{
			Cookie[] clientCookies=getCurrentRequest().getCookies();
			 
			 if ( null!=clientCookies && clientCookies.length>0) 
			 {
				 
			    for (int i = 0; i < clientCookies.length; i++) 
			    {
			    	
					 if ( cookiename.equals( clientCookies[i].getName() ) ) 
					 {
						 try {
							 
							return java.net.URLDecoder.decode(clientCookies[i].getValue(),"utf-8");
						 } catch (UnsupportedEncodingException e) {
							
							e.printStackTrace();
						}
					}
				}	
			}	
		}	    
		return "";
	 }
	 
	 /**
	  * 向客户端写cookie
	  * @param cookieName cookie名称
	  * @param cookieVal cookie值
	  
	 public static void writeCookie(String cookieName,String cookieVal)
	 {
        if(Utils.StringIsNullOrEmpty(cookieName)|| Utils.StringIsNullOrEmpty(cookieVal) )
        	return;
        
		Cookie cookie=new Cookie(cookieName,cookieVal);
		cookie.setPath("/");
	   
		getCurrentResponse().addCookie(cookie);
	 }*/
	
	 /**
	  * 写网站登录用户的cookie
	  * @param shortUserInfo 用户信息
	  * @param expires cookie有效期
	  * @param passwordkey 用户密码Key
	  
	 public static void writeUserCookie(ShortUserInfo shortUserInfo,int expires,String passWordkey)
	 {
		 
		 Cookie useridCookie=new Cookie("uid",shortUserInfo.getId().toString());
		 useridCookie.setMaxAge(expires);
		 useridCookie.setPath("/");
		 getCurrentResponse().addCookie(useridCookie);
		 
		 Cookie userNameCookie=new Cookie("username","");
				
		 try {
			 
			 userNameCookie = new Cookie("username",java.net.URLEncoder.encode(shortUserInfo.getUsername() ,"utf-8" ));
					
		 } catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		 }
		
		userNameCookie.setMaxAge(expires);
		userNameCookie.setPath("/");
		getCurrentResponse().addCookie(userNameCookie);
		 
		Cookie passWordCookie=new Cookie("pk","");
				
		try {
			
			String encryptPassWord=encodeCookiePassword(shortUserInfo.getPassword(), passWordkey);
			passWordCookie=new Cookie("pk",java.net.URLEncoder.encode( encryptPassWord ,"utf-8" ));
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		passWordCookie.setMaxAge(expires);
		passWordCookie.setPath("/");
        getCurrentResponse().addCookie(passWordCookie);
				
	 }
	 	 */
	 /**
	  * 
	  * @param expires 到期
	  * @param passWordkey
	  */
	 /*public static void writeUserSafePassWordCookie(int expires,String safepassword,String passWordkey)
	 {
		 	Cookie passWordCookie=new Cookie("spwd","");
			
			try {
				
				String encryptPassWord=encodeCookiePassword(safepassword, passWordkey);
				passWordCookie=new Cookie("spwd",java.net.URLEncoder.encode( encryptPassWord ,"utf-8" ));
				
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			
			passWordCookie.setMaxAge(expires);
			passWordCookie.setPath("/");
	        getCurrentResponse().addCookie(passWordCookie);      	         
	 }*/
	 
	  
	/**
	 * 判断是否使用get方法提交表单
	 * @param request
	 * @return
	 */
	public static boolean isGet() 
	{
			 	
	   if ( "get".equalsIgnoreCase(getCurrentRequest().getMethod()) ) 
	   {
		   return true;
	   }
       return false;	
	}
			
	/**
	 * 判断是否使用post方法提交表单
	 * @param request
	 * @return
	 */
	public static boolean isPost() 
	{
		
		if ( "post".equalsIgnoreCase( getCurrentRequest().getMethod() ) ) 
		{
			 return true;			
		}
		
		return false;
	}
			
	
	 
	/**
	 * 
	 * @param sessionname
	 * @param codekey
	 * @return
	 */
	public static String getCodeVal(String sessionname,String codekey)
	{
	  return getCodeVal(sessionname, codekey,null);	
	}
		
	/**
	 * 
	 * @param sessionname
	 * @param codekey
	 * @param request
	 * @return
	 */
	public static String getCodeVal(String sessionname,String codekey,HttpServletRequest request)
	{
	   if(!Utils.StringIsNullOrEmpty(sessionname))
	   {
          if(null==request)
          {
        	request=getCurrentRequest();  
          }
          
          Map<String, String> entity= ( Map<String, String>)request.getSession().getAttribute(sessionname);
          return entity.get(codekey);
       
	   }	
		return "";
	}
		
	/**
	 * 获取登录员工的所有权限
	 * @return
	 */
	public static String getEmployeePrivileges()
	{
		
	   HttpServletRequest request=getCurrentRequest();
	   if(request!=null)
	   {
		   return String.valueOf( request.getSession().getAttribute("employeePrivileges") );
	   }
	   return "";
	}
				
	/**
	 * 返回通过ajax执行操作后的提示信息
	 * @param flag 状态码 
	 * @param jsonStr json字符串
	 * @param mustCloseResponse 是否关闭response对象输出流
	 */
	public static void responseJsonResult(HttpServletResponse response,int flag,String jsonStr,boolean mustCloseResponse)  
	{
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		PrintWriter out=null;
		try {
			
			out = response.getWriter();
			
			String tpl="{0}\"state\":{1},\"data\":{2}{3}";
			
			out.write(MessageFormat.format(tpl,'{',flag,jsonStr,'}'));
			//System.out.println(MessageFormat.format(tpl,'{',flag,jsonStr,'}'));
	        out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			if ( mustCloseResponse && null!=out ) {
				out.close();
			}
		}
	}
	
	/**
	 * 添加或删除后的提示页面
	 * @param message
	 * @param linkStr
	 * @param operType
	 * @throws IOException
	 */
	public static void pageRender(HttpServletResponse response,String message, String linkStr, String operType) throws IOException
	{
		if(linkStr.equals("")){
			String url = MessageFormat.format(HttpUtility.getNameSpaceToSession(), "list.do");
			linkStr = MessageFormat.format("<a class=\"btn\" href=\"{0}\">{1}</a>",url,"返回列表");
		}
         StringBuffer htmlBuffer=new StringBuffer();
         htmlBuffer.append("<center>");
         htmlBuffer.append("<table align=\"center\" width=\"500\" class=\"MessageBox\">");
         htmlBuffer.append("<tbody>");
         htmlBuffer.append("<tr>");
         htmlBuffer.append("<td valign=\"top\" class=\"msg {0}\">");
         htmlBuffer.append("<div class=\"title\">{1}</div>");
         htmlBuffer.append("<div class=\"content\">{2}</div>");
         htmlBuffer.append("<div class=\"buttons\" style=\"margin-top:8px;\" ><a class=\"btn\" href=\"javascript:history.back();\">返回继续操作</a>&nbsp;&nbsp;{3}</div>");
         htmlBuffer.append("</td>");
         htmlBuffer.append("</tr>");
         htmlBuffer.append("</tbody>");
         htmlBuffer.append("</table>");
         htmlBuffer.append("</center>");
             
         StringBuffer contentBuffer=new StringBuffer();
         contentBuffer.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
         contentBuffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r");
         contentBuffer.append("<title>操作结果</title>\r");
         contentBuffer.append("<link rel=\"stylesheet\" href=\"/admin/css/common.css\" type=\"text/css\" />");
         contentBuffer.append("</head>");
         contentBuffer.append("<body style=\"margin-top:50px;\">\r");
               
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html;charset=UTF-8");
         
         PrintWriter out = response.getWriter();;
	 
		 out.write(contentBuffer.toString());
         out.write(MessageFormat.format(htmlBuffer.toString(), 
        		 operType == "success" ? "success" : (operType == "alert" ? "alert" : "error"),
        		 operType == "success" ? "操作成功" : (operType == "alert" ? "操作提示" : "操作失败"),
        		 message,		 
        		 linkStr));
         out.write("</body>\r</html>\r");		
	}
		
	/**
	 * 获到当前的Request对象
	 * @return 当前的的Request对象
	 */
	public static HttpServletRequest getCurrentRequest()
	{
		
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
		
	/**
	 * 获取当前的Response对象
	 * @return 当前的Response对象
	 
	public static HttpServletResponse getCurrentResponse()
	{		
		return ((ServletRequestAttributes)).get;
	}*/
	
	
	/**
	 * 获取访问网站用户的ip地址
	 * @return
	 */
	public static String getClientIp()
	{
		HttpServletRequest httpRequest=getCurrentRequest();
		if(httpRequest!=null)
		{
			if(Utils.getMessage("sys_address").equals("test"))
			return httpRequest.getRemoteAddr();//服务器有NGINX转发，不能这样获取！
			return httpRequest.getHeader("X-Real-IP");
		}
		return "0.0.0.0";
	}
		
	/**
	 * 获取当前的Session对象
	 * @return 当前的Session对象
	 */
	public static HttpSession getCurrentSession() 
	{
		return getCurrentRequest().getSession();	
	}
	
		
	/**
	 * 获取Url中的指定的参数值
	 * @param parameter 参数名称
	 */
	public static String getQueryString(String paramName)
	{
		
		HttpServletRequest httpRequest=getCurrentRequest(); 
		
		if(httpRequest!=null)
		{
			String result=httpRequest.getParameter(paramName);
			
			if(!Utils.StringIsNullOrEmpty(result))
				return result;
			else 
				return "";
		}
				
		return "";
	}
	
	/**
	 * 
	 * @param paramName
	 * @param defValue
	 * @return
	 */
	public static int  getInt(String paramName,int defValue)
	{
		String queryStr=getQueryString(paramName);
		return NumberUtils.stringToInt(queryStr,defValue);
	}
	
	/**
	 * 
	 * @param paramName
	 * @param defValue
	 * @return
	 */
	public static long getLong(String paramName,long defValue)
	{
		String queryStr=getQueryString(paramName);
		return Utils.StringIsNullOrEmpty(queryStr)?defValue:NumberUtils.createLong(queryStr);
	}
		
	/**
	 * 返回上一个页面的地址
	 * @return 返回上一个页面的地址
	 */
	public static String getUrlReferrer()
	{
	   String resultStr=getCurrentRequest().getHeader("Referer");
	   return Utils.StringIsNullOrEmpty(resultStr)?"":resultStr;
	}
	
	
	public static String getHost()
	{
	   return getCurrentRequest().getHeader("Host");	
	}
		
	
	public static boolean isCrossSitePost()
	{
		if(!isPost())
			return true;
		
		return isCrossSitePost(getUrlReferrer(), getHost());
	}
		
	public static boolean isCrossSitePost(String urlReferrer, String host)
	{

		if (urlReferrer.length() < 7)
		{
			return true;
		}
            
	    java.net.URL url=null;
		
	    try {
			url = new java.net.URL(urlReferrer);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        return url.getHost().equals(host);
	}
	
	/**
	 * http://localhost
	 * @return
	 */
	public static String getBasePath()
	{
		HttpServletRequest request=getCurrentRequest();
		if(request!=null)
		{
			if(request.getServerPort()==80)
			{
			   return request.getScheme()+"://"+request.getServerName();
			   
			  	
			}
			else 
			{
			  return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();		
			}
		}
		
		return "";
	}	
	public static void setNameSpaceToSession(){
		
		String uri = getCurrentRequest().getRequestURI();
			   uri = uri.substring(0,uri.lastIndexOf("/")+1)+"{0}";
			getCurrentSession().setAttribute("URI_NAME_SPACE", uri);
	}
	
	public static String getNameSpaceToSession(){
			return getCurrentSession().getAttribute("URI_NAME_SPACE").toString();
	}
}
