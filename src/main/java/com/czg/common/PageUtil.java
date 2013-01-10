package com.czg.common;

import java.text.MessageFormat;

/**
 * @author fnyexx
 * 
 *	分页工具类
 */
public class PageUtil {
	
	/**
     * 获取简单分页链接
	 *@param records 总记录数
     *@param currentPage 当前页
     *@param countPage 总页数
	 *@param url 分页链接
	 *@param pageTag  页码标记
	 *@param return 简单分页链接
     */	 
	public static String getSimplePagerNumbers(int records, int currentPage, int countPage, String url,String pageTag )
	{
	  
	    if (Utils.StringIsNullOrEmpty(pageTag)||"".equals(pageTag))
        	pageTag = "pageNum";
        
        if (url.lastIndexOf("?") > 0)
        {
        	url = url + "&";
        }
        else
		{
		  url = url + "?";
		}
                
        if (countPage < 1)
        {
        	countPage = 1;
	    } 		
        
		StringBuffer sb=new StringBuffer();
		sb.append("<span>");
        
		if(countPage==1)
		{
		 sb.append(MessageFormat.format( "总共<b>{0}</b>条记录",String.valueOf(records) )); 
		 sb.append(MessageFormat.format( "当前第<b>{0}/共{1}</b>页", 1,1 ) );
		 sb.append("<a href=\"javascript:;\">首页</a><a href=\"javascript:;\">上一页</a><a href=\"javascript:;\">下一页</a><a href=\"javascript:;\">尾页</a>"); 
		}
		else
		{
		   
		   sb.append(MessageFormat.format( "总共<b>{0}</b>条记录",String.valueOf(records) )); 
		   sb.append(MessageFormat.format( "当前第<b>{0}/共{1}</b>页", String.valueOf(currentPage),String.valueOf(countPage) ) ); 
	      
		   if(currentPage==1)
		   { 
			sb.append("<a href=\"javascript:;\">首页</a>");   
		    sb.append("<a href=\"javascript:;\">上一页</a>");
			sb.append(MessageFormat.format("<a href=\"{0}{1}={2}\">下一页</a>",url,pageTag,String.valueOf( currentPage+1) ));
			sb.append(MessageFormat.format("<a href=\"{0}{1}={2}\">尾页</a>",url,pageTag,countPage));
		   }
		   else if(currentPage==countPage)
		   {
			sb.append(MessageFormat.format("<a href=\"{0}{1}=1\">首页</a>",url,pageTag));		   
            sb.append(MessageFormat.format("<a href=\"{0}{1}={2}\">上一页</a>",url,pageTag,String.valueOf(currentPage-1)));
            sb.append("<a href=\"javascript:;\">下一页</a>");
            sb.append("<a href=\"javascript:;\">尾页</a>");
		   }
		   else 
		   {
			 sb.append(MessageFormat.format("<a href=\"{0}{1}=1\">首页</a>",url,pageTag));		   
	         sb.append(MessageFormat.format("<a href=\"{0}{1}={2}\">上一页</a>",url,pageTag,String.valueOf(currentPage-1)));
	         sb.append(MessageFormat.format("<a href=\"{0}{1}={2}\">下一页</a>",url,pageTag,String.valueOf( currentPage+1) ));
			 sb.append(MessageFormat.format("<a href=\"{0}{1}={2}\">尾页</a>",url,pageTag,countPage));
		   }
		}
		
	   sb.append("</span>");
	    
	   return sb.toString();
	 
	}
	
	/**
	 * 绑定页面信息
	 * @param PAGE
	 */
	public static Page bindPageMessage(Page page)
	{
		

          //获取总页数
         page.setPageCount(page.getRecordCount() % page.getTpp() == 0 ? page.getRecordCount() / page.getTpp() : page.getRecordCount() / page.getTpp() + 1);
         page.setPageCount(page.getPageCount() == 0 ? 1 : page.getPageCount());

          //修正请求页数中可能的错误
         page.setPageNum( page.getPageNum() < 1 ? 1 : page.getPageNum());
         page.setPageNum( page.getPageNum() > page.getPageCount() ? page.getPageCount() : page.getPageNum());
         
         //生成JAVA分页HTML文
         if(page.isJava())
         page.setPageStr(getSimplePagerNumbers(	page.getRecordCount(),page.getPageNum(),page.getPageCount(),page.getUrl(),"pageNum" ));
         
         return page;
         
	}
}
