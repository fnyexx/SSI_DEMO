<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>类别列表管理</title>
<%@ include file="/main/page/jscss.jsp" %>
<script src="/main/js/jquery.js" type="text/javascript"></script>
<style type="text/css">
 form {margin:0;border:0;}
 #pager span a{ margin:0 5px;}
</style>
<script language="javascript">

<!-- -->
$(function(){
	$("#scp").change(function(){
		location.href=("/admin/cpin.aspx?cid="+$("#scp").val());
	})
});
</script>

</head>
<body leftmargin="8" topmargin="8" background='/main/images/allbg.gif'>


<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="/main/images/tbg.gif">&nbsp;
	
	类别列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="8%">类别编号</td>
	<td width="10%">类别名称</td>
	<td width="10%">操作</td>
</tr>
<c:forEach var="item" items="${leiBie}">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FFB584';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td>${item.cid }</td>
	<td>${item.cname }</td>
	<td>
	<form id="frm_edit${item.cid}" action="/admin/lbie.do" method="post" style="display:inline">
	<input type="hidden" name="ac" value="update"/>
	<input type="hidden" name="cid" value="${item.cid}"/>
	<input type="hidden" name="ctype" value="${item.ctype}"/>
	<a href="javascript:;" onclick="$('#frm_edit${item.cid}}')[0].submit();">编辑</a></form> |
	 <form id="frm_del${item.cid}" action="/admin/lbie.do" method="post" style="display:inline">
	 <input type="hidden" name="ac" id="ac" value="del"/>
	 <input type="hidden" name="cid" value="${item.cid}"/>
	 <input type="hidden" name="ctype" value="${item.ctype}"/>
	 <a href="javascript:;" onclick="if (confirm('您确定要删除吗?')){ $('#frm_del${item.cid}')[0].submit();}" >删除</a></form>
	 </td>
</tr>
</c:forEach>
<!--  快速转换位置按钮  -->
  <tr align="right" bgcolor="#EEF4EA">
  <td height="20" colspan="10" align="center">
    <a href="/admin/lbie.do?ac=insert&ctype=${ctype}">新增类别</a>
 </td>
 </tr>
<tr align="right" bgcolor="#EEF4EA">
</tr>

</table>
</body>
</html>