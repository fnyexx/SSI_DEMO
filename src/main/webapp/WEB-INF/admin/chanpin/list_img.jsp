<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.jsy.bean.Img"%>
<%@page import="com.jsy.tools.Utils"%>
<%@page import="com.jsy.tools.HttpUtility"%>

<%@ include file="/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>产品图片列表管理</title>
<%@ include file="/main/page/jscss.jsp" %>
<script src="/main/js/jquery.js" type="text/javascript"></script>
<style type="text/css">
 form {margin:0;border:0;}
 #pager span a{ margin:0 5px;}

a:link {
font-family: Verdana, Arial, Helvetica, sans-serif;
font-size: 12px;
color: #333333;
text-decoration: none;
}
a:hover {
font-family: Verdana, Arial, Helvetica, sans-serif;
font-size: 12px;
color: #008EC8;
text-decoration: none;
}
table {
border-collapse: separate;
border-spacing: 2px;
}


</style>
<script language="javascript">
$(function(){
	$("#scp").change(function(){
		location.href=("/admin/cpimg.aspx?cid="+$("#scp").val());
	})
});
</script>

</head>
<body leftmargin="8" topmargin="8" background='/main/images/allbg.gif'>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="/main/images/tbg.gif">&nbsp;产品图片列表&nbsp;
	<form action="/admin/cpimg.aspx" method="post">
	<select id="scp">
	<option <c:if test="${cid==-1}">selected</c:if> value=-1>所有产品图片</option>
	<c:forEach var="item" items="${leiBie}">
		<option <c:if test="${cid==item.cid}">selected</c:if> value=${item.cid}>${item.cname}</option>
	</c:forEach>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;共计<span style="color:red">${imgCount}</span>条——————产品ID:<input type="text" name="cpid" value="${cpid}" /><input type="submit" value="查询"></form>
	</td>
</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tbody>
          <tr>
            <td colspan="3" valign="top">
                <table width="99%" border="0" align="right" cellpadding="0" cellspacing="0">
                  <tbody><tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td valign="top" height="300" style="padding-right:8px; ">
					
					
<div style=" line-height:21px;width:100%;text-align:justify;text-justify:inter-ideograph">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tbody><tr><td height="8"></td></tr>

<%
	List<Img> img=(List<Img>) HttpUtil.getCurrentRequest().getAttribute("img");
	int t=0;
	int num=6;
	for(int i=1;i<img.size()+1;i++){
		if(((i-1)%6)==0){
		++t;
%>
		<tr>		
		<%
		}
		%>
							 <td valign="top">
							 <table width="16%" border="0" align="center" cellpadding="0" cellspacing="0">
                              <tbody><tr>
                                <td valign="top"><table width="100" height="133" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#bbbbbb">
                                    <tbody><tr>
                                      <td bgcolor="#ffffff"><a href="/cpimg/<%=img.get(i-1).getCpid()%><%=img.get(i-1).getPath()%>" target="_blank" title="<%=img.get(i-1).getImgname() %>"><img src="/cpimg/sca/<%=img.get(i-1).getCpid()%><%=img.get(i-1).getPath()%>" width="100" height="133" border="0" alt="<%=img.get(i-1).getImgname() %>"></a></td>
                                    </tr>
                                </tbody></table></td>
                              </tr>
                              <tr>
                                <td height="33" valign="middle"><div align="center">
                                    <table width="100" height="25" border="0" cellpadding="2" cellspacing="1">
                                      <tbody><tr>
                                        <td><div align="center"><%=img.get(i-1).getImgname() %><br>
											 <form id="frm_del<%=img.get(i-1).getId()%>" action="/admin/cpimg.do" method="post" style="display:inline">
											 <input type="hidden" name="ac" id="ac" value="del"/>
											 <input type="hidden" name="id" value="<%=img.get(i-1).getId()%>"/>
											 <a href="javascript:;" onclick="if (confirm('您确定要删除图片-[<%=img.get(i-1).getImgname() %>]-吗?')){ $('#frm_del<%=img.get(i-1).getId()%>')[0].submit();}" >删除</a><a>取消置顶</a></form>
										</div></td>
                                      </tr>
                                    </tbody></table>
                                </div></td>
                              </tr>
                            </tbody></table>

</td>
		<% 
		if((i==img.size()||(i)%(num*t)==0)){
		%></tr><%
		}
	}
		

	%>
<tr>



</tr>



</tbody></table>

${pageNumbers}
</div>					
		
					</td>
                  </tr>
              </tbody></table></td>
          </tr>
        </tbody></table>


</body>
</html>