package com.czg.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;

import sun.misc.BASE64Encoder;
@SuppressWarnings("restriction")
public class Utils 
{
	
	//通过它获取.properties中内容
	public static Properties pps=null ;
		
	
    //缓冲
    private static final int BUFFER_SIZE = 16 * 1024 ;
    
    
    private static final String[] keyUserName={"xiucaike","admin","administrator","root","user","test","system","guest","系统消息","客服","官方","系统","客户","秘书","中奖","工作人员","管理","秀才客","财务"};
    
    
    
	/**
	 * 检测字符串是否为空字符或null
	 * @param value 待检测的字符串 
	 * @return 若字符串真为空或null则 返回 true 否为返回 false
	 */
	 public static boolean StringIsNullOrEmpty(String value)
     {
		 if(value==null)
		 {
			 return true;
		 }
		 
		 if(value.trim().length()==0)
		 {
			 return true;
		 }

         return false;
     }
	 
	 /**
	  * 返回字符串的真实长度
	  * @param str
	  * @return
	  */
	 public static int getStringLength(String str)
	 {
		 
	   try{
			return str.getBytes("gb2312").length;	
		} 
	    catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		 
		 return 0;
	 }
	 
	 /**
	  * 保留的 username
	  * @param str
	  * @return
	  */
	 public static boolean checkReservedUserName(String str)
	 {
		 for(int i=0;i<keyUserName.length;i++)
		 {
			if(str.indexOf( keyUserName[i] )!=-1)
			{
				return false;
			}
		 }
		 		 
		 return true;
	 }
	 
	 
	 /**
	  * 从字符串的指定位置截取指定长度的子字符串
	  * @param str 原字符串
	  * @param startIndex 子字符串的起始位置
	  * @param length 子字符串的长度
	  * @return 子字符串
	  */
     public static String CutString(String str, int startIndex, int length)
     {
         if (startIndex >= 0)
         {
             if (length < 0)
             {
                 length = length * -1;
                 if (startIndex - length < 0)
                 {
                     length = startIndex;
                     startIndex = 0;
                 }
                 else
                     startIndex = startIndex - length;
             }

             if (startIndex > str.length())
                 return "";
         }
         else
         {
             if (length < 0)
                 return "";
             else
             {
                 if (length + startIndex > 0)
                 {
                     length = length + startIndex;
                     startIndex = 0;
                 }
                 else
                     return "";
             }
         }

         if (str.length()- startIndex < length)
             length = str.length() - startIndex;

         return str.substring(startIndex, length);
     }
     
            
     /**
      * 从字符串的指定位置开始截取到字符串结尾的了符串
      * @param str 原字符串
      * @param startIndex 子字符串的起始位置
      * @return 子字符串
      */
     public static String CutString(String str, int startIndex)
     {
         return CutString(str, startIndex, str.length());
        
     }
         
     /**
      * 检测是否有sql危险字符
      * @param str 要判断字符串
      * @return 判断结果
      */
     public static boolean IsSafeSqlString(String str)  {
    	 
    	  return !Pattern.compile("[-|;|,|\\/|\\(|\\)|\\[|\\]|\\}|\\{|%|@|\\*|!|\']").matcher(str).matches();  
     }
        
     /**
      * 检测字符串是否有凶险的字符
      * @param str
      * @return
      */
     public static boolean IsSafeUserInfoString(String str)
     {
    	 return !Pattern.compile("^\\s*$|^c:\\\\con\\\\con$|[%,\\*" + "\"" + "\\s\\t\\<\\>\\&]|游客|^Guest").matcher(str).matches(); 
         
     }
          
     /**
      * 检测邮箱是否符合要求
      * @param eamil
      * @return
      */
     public static boolean IsValidEmail(String eamil)
     {
    	 return !Pattern.compile("^[\\w\\.]+([-]\\w+)*@[A-Za-z0-9-_]+[\\.][A-Za-z0-9-_]").matcher(eamil).matches(); 
     }

     /**
      * 返回 URL 字符串的编码结果
      * @param str 字符串
      * @return 解码结果
      */
     public static String urlDecode(String str) 
     {
    	
    	String result=StringUtils.EMPTY; 
    	
    	try {		 		 
    		result=URLDecoder.decode(str, "utf-8");		
		 } catch (UnsupportedEncodingException e) {		
			e.printStackTrace();
		}
		 
		return result;
     }
	 
     /**
      * 判断是否为base64字符串
      * @param str
      * @return
      */
     public static boolean IsBase64String(String str) {
		
    	return Pattern.compile("[A-Za-z0-9\\+\\/\\=]").matcher(str).matches();
	}
     
     
     
     
 	/**
 	 * 字符串使用Base64进行编码
 	 * @param value 待编码的字符串
 	 * @return 使用Base64进行编码的字符串
 	 * @throws IOException
 	 */ 
	public static String EncdoeStrUsingBase64(String value) throws IOException{
	   
	    if(!StringIsNullOrEmpty(value))
	    {
		  return new BASE64Encoder().encode(value.getBytes());
		}
	    
	    return "";
	}
     	
	/**
	 * 字节数组使用Base64进行编码
	 * @param value 待编码的字节数组
	 * @return 使用Base64进行编码的字节数组
	 * @throws IOException
	 */
	public static String EncdoeStrUsingBase64(byte[] value) throws IOException{
	   
		String encodeTxt = "";
	    if( value!=null && value.length>0 ){
	    	encodeTxt= new BASE64Encoder().encode(value);
		}
	    
	    return encodeTxt;
	} 
	 
   
	/**
	 * 字符串使用Base64解码
	 * @param value 待解码的字符串
	 * @return 使用Base64解码的字符串
	 * @throws IOException
	 */
	public static byte[] DecodeStrUsingBase64(String value){
	  
	  byte[] buf = null;
      if( !StringIsNullOrEmpty(value) )
      {
		 
    	  try 
    	  {
    	      buf = new sun.misc.BASE64Decoder().decodeBuffer(value);
    	  }
    	  catch (IOException ex) 
    	  {
    		   ex.printStackTrace();
    	  }
	  }
	    
	  return buf;	
	} 
	 
	 
	/**
	 * 解析url
	 * @param url 浏览器url地址 example: http://localhost/user/login.do?goto=/task/post.do
	 * @param paramName 参数的名称 
	 * @return
	 */
    public static String getQueryString(String url,String paramName)
    {
    	
    	Map<String, String> queryParams=new HashMap<String, String>();
    	if( !StringIsNullOrEmpty(url) && url.indexOf('?')>-1 )
    	{
    		   		
    		url=url.substring(url.indexOf('?')+1,url.length());
    		String[] keyValuePairs=url.split("&");
    		
    		for (String str : keyValuePairs) 
    		{
				String[] item=str.split("=");
				queryParams.put(item[0], item[1]);	
			}
    		 		
    	}
    
    	return ( queryParams.containsKey(paramName)?queryParams.get(paramName):"" );
    }
    
    /**
     * 格式化Uid为9位标准格式
     * @param uid 用户编号
     * @return 
     */
    public static String formatUid(long uid)
    {
    	String userid=String.valueOf(uid);
    	
    	if( !StringIsNullOrEmpty(userid)  )
    	{
    		 if( userid.length()<7 )
    		 {
    			 return String.format("%1$07d",uid);
    		 }
    		 else{
    			 return userid.substring(0,7);
    		 }	
    	}
    	return "";
    }
    
    
    /**
     * 构建用户的头像和身份证存储路径
     * @param uid
     * @return "137/25/66"
     */
    public static String buildPath(String uid)
    {
    	if( !StringIsNullOrEmpty(uid) && uid.length()>6  )
    	{
    		return uid.substring(0,3)+"/"+uid.substring(3,5)+"/"+uid.substring(5);
    	}
        return "";	
    }
    
    
    /**
     * 获获得当前绝对路径
     * @param folderName 指定的路径
     * @return 绝对路径
     */
    public static String getMapPath(String path)
    {
    	if( !StringIsNullOrEmpty( path ) )
    	{
    		if ( !path.startsWith("/")) 
    		   path="/"+path;		

    		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getRealPath(path);
    	}
    	
    	return "";
    }
   
   
  /**
   * 获取文件最后修改的时间 
   * @param path 文件的路径
   * @return 文件最后修改的时间
   */
   public static long getLastModified(String path)
   {
	  File file=new File(path);
	  return file.lastModified();
   }
    
   
    /**
     * 判断目录是否存在,不存在就创建 
     * @param path
     * @return
     */
    public static void createDirectory(String path)
    {
        File file = new File(path);
        if ( !file.isDirectory() ) 
        {
			file.mkdirs();
		}	
    }
    
    /**
     * 获得页码显示链接 
     * @param curPage 当前页数
     * @param countPage 总页数
     * @param url 超级链接地址
     * @param extendPage 周边页码显示个数上限
     * @param pageTag 页码标记
     * @return
     */
    public static String getPageNumbers( int curPage, int countPage, String url, int extendPage, String pageTag )
    {
        if (StringIsNullOrEmpty(pageTag)||"".equals(pageTag))
        	pageTag = "page";
        
        int startPage = 1;
        int endPage = 1;
        
        if (url.lastIndexOf("?") > 0)
        {
        	url = url + "&";
        }
        else
            url = url + "?";
            
        if (countPage < 1)
        {
        	countPage = 1;
        }
            
        if (extendPage < 3)
        {
        	extendPage = 2;
        }
            
        
        if (countPage > extendPage)
        {
            if (curPage - (extendPage / 2) > 0)
            {
                if (curPage + (extendPage / 2) < countPage)
                {
                    startPage = curPage - (extendPage / 2);
                    endPage = startPage + extendPage - 1;
                }
                else
                {
                    endPage = countPage;
                    startPage = endPage - extendPage + 1;
                   
                }
            }
            else
            {
                endPage = extendPage;
               
            }
        }
        else
        {
            startPage = 1;
            endPage = countPage;
           
        }
        
        StringBuffer sb=new StringBuffer("");
        
        for (int i = startPage; i <= endPage; i++)
        {
            if (i == curPage)
            {
                sb.append("<span class=\"current\" title=\""+i);
                sb.append("\">");
                sb.append(i);
                sb.append("</span>");
            }
            else
            {
            	 sb.append("<a href=\"");
                 sb.append(url);
                 sb.append(pageTag);
                 sb.append("=");
                 sb.append(i);
                 sb.append("\"");
                 sb.append(" title=\""+i);
                 sb.append("\">");
                 sb.append(i);
                 sb.append("</a>");
            }
        }
        
        return sb.toString();        
    }
    
    
	
	
    /**
     * 复制文件
     * @param src
     * @param dst
     */
	public static void copy(File src, File dst)  
	{
         try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
                in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
                out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
                 byte [] buffer = new byte [BUFFER_SIZE];
                 while (in.read(buffer) > 0 )  {
                    out.write(buffer);
                } 
             } finally  {
                 if ( null != in)  {
                    in.close();
                } 
                  if ( null != out)  {
                    out.close();
                } 
            } 
         } catch (Exception e)  {
            e.printStackTrace();
        } 
    } 
    
	
	/**
	 * 获取文件的扩展名
	 * @param fileName
	 * @return
	 */
    public static String getExtention(String fileName)  
    {
         int pos = fileName.lastIndexOf(".");
         return fileName.substring(pos+1);
    } 
    
    
    /**
     * 
     * @param length
     * @return
     */
    public static String getFileSize(long length)
    {
    	if(length<1024)
    	{
    		return length+"字节";
    	}
    	else if (1024<=length||length<148576) 
    	{
            return String.valueOf(length/1024)+"KB";			
		}
    	else 
    	{
		  return String.valueOf(length/1024/1024)+"MB";	
		}
    	
    }
    
    
    /**
     * 获取某一时间段 
     * @param isYesterday 是否是昨天
     * @param isStart 是否是开始时间段
     * @return 某一时间段 
     */
    public static Date getTimePeriod(boolean isYesterday,boolean isStart)
	{
    	    	
    	Calendar calendar = Calendar.getInstance();
    	
    	if (isYesterday)
		{
			calendar.add(Calendar.DATE, -1);
		}
    	
    	if (isStart)
		{
    		calendar.set(Calendar.HOUR_OF_DAY,0);
        	calendar.set(Calendar.MINUTE, 0);
        	calendar.set(Calendar.SECOND, 0);
        	calendar.set(Calendar.MILLISECOND, 0);
	
		}
    	else 
    	{
    		calendar.set(Calendar.HOUR_OF_DAY, 23);
    		calendar.set(Calendar.MINUTE, 59);
    		calendar.set(Calendar.SECOND, 59);
    		calendar.set(Calendar.MILLISECOND, 999);	
		}  
    	
    	return calendar.getTime();
	}
     

    /***
     * 
     * @param hours
     * @return
     */
    public static Date getDiffTimeByHours(int hours)
    {
    	Calendar calendar = Calendar.getInstance();
    	
    	calendar.set(Calendar.HOUR_OF_DAY,0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	
    	int mis=( (hours-1)*60*60*1000 ) + ( 59*60+59)*1000;
    	calendar.add(Calendar.MILLISECOND,mis );
    	
    	return calendar.getTime();
    }

    public static Date getDiffTimeByMinute(int minute)
    {
        Calendar calendar = Calendar.getInstance();
    	
    	calendar.set(Calendar.HOUR_OF_DAY,0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	
    	int mis=minute*60*1000;
    	calendar.add(Calendar.MILLISECOND,mis );
    	
    	return calendar.getTime();
    	
    }
    
    /**
     * 字符串如果操过指定长度则将超出的部分用指定字符串代替
     * @param SrcStr 要检查的字符串
     * @param len 指定长度
     * @param TailStr 用于替换的字符串
     * @return 截取后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String getUnicodeSubString(String SrcStr, int len, String TailStr) throws UnsupportedEncodingException
	{
    	
  	     String resultStr="";
         int byteLen = SrcStr.getBytes("utf-8").length; //单字节字符长度
         int charLen = SrcStr.length(); //把字符平等对待时的字符串长度
         int byteCount = 0; //记录读取进度
         int pos = 0;  //记录截取位置
         
         if (byteLen >len )
         {
             for (int i = 0; i < charLen; i++)
             {
                 if ((int)SrcStr.toCharArray()[i] > 255) //按中文字符计算加2
                     byteCount += 3;
                 else  //按英文字符计算加1
                     byteCount += 1;
                 
                 if (byteCount > len)  //超出时只记下上一个有效位置
                 {
                     pos = i;
                     //System.out.println("pos# "+i);
                     break;
                 }
                 else if (byteCount == len) //记下当前位置
                 {
                     pos = i + 1;
                     //System.out.println("pos# "+i);
                     break;
                 }
             }

             if (pos >= 0)
                 resultStr = SrcStr.substring(0, pos) + TailStr;
         }
         else
      	   resultStr = SrcStr;
         
         return resultStr;
	}
    
    /**
     * 用户的邮件主体
     * @param email email
     * @return 用户的邮件主体
     */
    public static String getUserEmailBody(String email)
    {
    	if(StringIsNullOrEmpty(email))
    	 return "";
    	 
    	if(email.indexOf("@")==-1)
    	 return "";
    	
    	return email.substring(email.indexOf("@")+1);
    }
    
    public static String getStringDate(Date date){
    	String ds=date.toLocaleString();
    	return ds.substring(0,ds.length()-2);
    }
    
    public static String getDateFormat(Date date){
    	return date!=null?new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date):"";
    }
    
    public static String getSortCont(String cont){
    	String str=cont.replace("&nbsp", "");
    	if(cont.length()>250)
    	str=str.substring(0,250)+"...";
    	return str;
    }
    
	/**
	 * 得到当前时间的前后几天
	 * @param sourceDate 原时间
	 * @param diff 增量
	 * @return 当前时间的前后几天  
	 */
	public static Date getDateAround(long sourceDate, int diff) 
	{ 

		 Calendar c = Calendar.getInstance(); // 初始化一个Calendar
		 c.setTimeInMillis(sourceDate);
		 c.add(Calendar.DATE, diff);
		 return  c.getTime();
	  } 
    
    /**
     * 返回 HTML 字符串的解码结果
     * @param str 字符串
     * @return 解码结果
     */
    public static String HtmlDecode(String str)
    {
    	return HtmlUtils.htmlUnescape(str);
    }
    
    /**
     * 返回 HTML 字符串的编码结果
     * @param str 字符串
     * @return 编码结果
     */
    public static String HtmlEncode(String str)
    {
        return HtmlUtils.htmlEscape(str);
    }
    
    /**
     * 返回带*号手机号码
     * @param str 字符串
     * @return 编码结果 返回带*号手机号码
     */
    public static String getTelEncrypt(String mobliePhone)
    {
    	String str=mobliePhone.substring(3,7);
        return mobliePhone.replace(str,"*****");
    }
    
    /**
     * 返回带*号邮箱地址
     * @param email 邮箱地址
     * @return 返回带*号邮箱地址
     */
    public static String getEmailEncrypt(String email)
    {
    	
   	    String emailHead=email.substring(0,email.indexOf("@"));
   	    if(emailHead.length()>5)
   	    {
   	       emailHead=emailHead.substring(0,emailHead.length()-5);
   	       emailHead+="*****";
   	    }
   	    else 
   	    {
   	       emailHead="*****";
   	    }
   	 
        return ( emailHead+email.substring( email.indexOf("@") ) );
    }
    
    /// <summary>
    /// 替换回车换行符为html换行符
    /// </summary>
    public static String StrFormat(String str)
    {
        String str2;

        if (str == null)
        {
            str2 = "";
        }
        else
        {
            str = str.replace("\r\n", "<br />");
            str = str.replace("\n", "<br />");
            str2 = str;
        }
        return str2;
    }
    
    /**
     * 判断给定的字符串数组(strNumber)中的数据是不是都为数值型
     * @param strNumber 要确认的字符串数组
     * @return 是则返加true 不是则返回 false
     */
    public static boolean isNumericArray(String[] strNumber)
    {
    	if(strNumber==null) return false;
    	
    	if(strNumber.length<1) return false;
    	
    	for (String str : strNumber)
		{
			if(!StringUtils.isNumeric(str))
				return false;		
		}
    	
    	return true;
    }
    
    /**
     * url encode
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */  
    public static String urlEncode(String url) 
    {
    	String result=StringUtils.EMPTY;
    	
    	try {
			result=java.net.URLEncoder.encode(url,"utf-8");
		} 
    	catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	return result;
    }

    /**
     * 获取远程用户IP
     * @return 返回IP
     */
    public static String getRemoteAddr()
    {
    	 HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    	return request.getRemoteAddr();
    }
    
    /**
     * 获取MessageResources.properties中key指定value
     * @return 返回value
     */
    public static String getMessage(String key)
    {
    	return pps.getProperty(key);
    }
    
    /**
     * 获取添加活越度值
     * num 0登陆 1发布任务 2完成任务 3一天不上线
     * @return 返回value
     */
    public static int getLiveLevel(int num)
    {
    	if(num==0){			/*登陆+5分*/
    		return 5;
    	}else if(num==1){	/*发布任务+10分*/
    		return 10;
    	}else if(num==2){	/*完成任务+15分*/
    		return 15;
    	}else if(num==3){	/*一天不上线-5分*/
    		return -5;
    	}else {}
    	return 0;
    }
    
    /** 
    *判断给定日期是否为当天， 
    *距离当前时间七天之内的日期，和七天之外的日期 
    * @param dt 
    * @param type 0--当天 1--7天之内的 2--7天之外的 
    * @return 
    */ 
    public static boolean getDayDiffFromToday(Date dt,int type){ 
    	if(dt==null)return false;
		Date today=new Date(); 
		today.setHours(23); 
		today.setMinutes(59); 
		today.setSeconds(59); 

		long diff = today.getTime() - dt.getTime(); 
		if(diff<0)diff=0; 
		long days = diff/(1000*60*60*24); 

		if(type==0 && days==0)return true; 
		if(type==1 && days>0 && days<=7)return true; 
		if(type==2 && days>7)return true; 

		return false; 
		} 
    
	/**
	 * 以友好的方式显示时间
	 * @param req
	 * @param time
	 * @return
	 */
	public static String getFriendly_time(Date time) {
		String str="前";
		int i=1;
		if(time == null) return "##错误的时间";
		int ct = (int)((System.currentTimeMillis() - time.getTime())/1000);
		if(ct<0){i=-1;str="后"; ct = (int)((time.getTime()-System.currentTimeMillis())/1000);}
		if(ct == 0) {  
            return "刚刚";  
        }  
        if(ct > 0 && ct < 60) {  
            return ct + "秒"+str;  
        }
        if(ct >= 60 && ct < 3600) {  
            return Math.max(ct / 60,1) + "分钟"+str;  
        }  
		if(ct >= 3600 && ct < 86400)
			return ct / 3600+"小时"+str;
		if(ct >= 86400 && ct < 2592000){ //86400 * 30
			int day = ct / 86400 ;			
			if(day==1) {if(i==1)return "昨天";else return "明天";}
			if(day==2) {if(i==1)return "前天";else return "后天";}
			return day+"天"+str;
			}
		if(ct >= 2592000 && ct < 31104000) //86400 * 30
			return ct / 2592000+"个月"+str;
		return  ct / 31104000<10?ct/31104000+"年"+str:"--";		
	}
	
	/**
	 * 获取N个小数位的Float
	 * @param 值，位数
	 * return 用户等级
	 */
	public static float getFloatPREC(float value,int n){
		  int   roundingMode  =  4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.  
		  BigDecimal   bd  =   new  BigDecimal((double)value);  
		  return bd.setScale(n,roundingMode).floatValue();  
	}
	
    /**
		 * 字节码转换成16进制字符串
		 * @param byte[]b 输入要转换的字节码
		 * @return String 返回转换后的16进制字符串
		 */
	    public static String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
				if (n < b.length - 1)
					hs = hs + ":";
			}
			return hs.toUpperCase();
		}
	    
	    /**
		 * 16进制字符串转换为byte数组 "1212"={12,12}
		 * @param strIn
		 *            需要转换的字符串
		 * @return 转换后的byte数组
		 * @throws IllegalArgumentException
		 */
		public static byte[] stringToByte(String hex)
				throws IllegalArgumentException
		{

			if (hex.length() % 2 != 0)
			{
				throw new IllegalArgumentException();
			}

			char[] arr = hex.toCharArray();
			byte[] b = new byte[hex.length() / 2];

			for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++)
			{
				String swap = "" + arr[i++] + arr[i];
				int byteint = Integer.parseInt(swap, 16) & 0xFF;
				b[j] = new Integer(byteint).byteValue();
			}
			return b;
		}	
		
		/**
		 *  二进制 byte[] 转字符串   {12,12}="1212"
		 * @param :       byte[]
		 * @return:       String
		 * @throws        AppException
		 */
		public static String byteToString(byte[] b) { 
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1) {
					hs = hs + "0" + stmp;
				} else {
					hs = hs + stmp;
				}
				if (n < (b.length - 1)) {
					hs = hs + "";
				}
			}
			return hs;
		}
		/**
		 * int类型转换为byte[]类型
		 * @param input 待转换的int值
		 * @return byte[]
		 */
	    public static byte[] intTobyte(int input) {
			byte[] output = new byte[4];
			output[0] = (byte) (input & 0xff);
			output[1] = (byte) ((input >> 8) & 0xff);
			output[2] = (byte) ((input >> 16) & 0xff);
			output[3] = (byte) (input >>> 24);
			return output;
		}

		/**
		 * byte[]类型转换为int类型
		 * @param input 待转换的byte值
		 * @return int
		 */
	    public static int byteToint(byte[] input) {
			int output = 0;
			if (input.length != 4) {
				
			}
			output = (input[0] & 0xff) | ((input[1] << 8) & 0xff00)
					| ((input[2] << 24) >>> 8) | (input[3] << 24);
			return output;
		}
		
		
		/**
		 * 字节数组使用Base64进行编码
		 * @param value 待编码的字节数组
		 * @return 使用Base64进行编码的字节数组
		 */
		public static String encdoeStrUsingBase64(byte[] value){
		   
			String encodeTxt = "";
		    if( value!=null && value.length>0 ){
		    	encodeTxt= new BASE64Encoder().encode(value);
			}
		    
		    return encodeTxt;
		}
}  
