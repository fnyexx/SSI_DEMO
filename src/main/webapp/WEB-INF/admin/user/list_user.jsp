<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/main/page/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表管理</title>
<%@ include file="/main/page/jscss.jsp" %>
<script src="/main/js/jquery.js" type="text/javascript"></script>
<style type="text/css">
 form {margin:0;border:0;}
 #pager span a{ margin:0 5px;}
</style>
</head>
<body leftmargin="8" topmargin="8" background='/main/images/allbg.gif'>
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="/main/images/tbg.gif">&nbsp;
	用户列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="8%">用户编号</td>
	<td width="10%">用户名</td>
	<td width="10%">真实姓名</td>
	<td width="10%">状态</td>
	<td width="10%">操作</td>
</tr>
<c:forEach var="item" items="${userList}">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FFB584';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td>${item.id }</td>
	<td>${item.userName }</td>
	<td>${item.realName }</td>
	<td>${item.status }</td>
	<td>
	<form id="frm_edit${item.id}" action="/admin/user.do" method="post" style="display:inline">
	<input type="hidden" name="ac" value="update"/>
	<input type="hidden" name="id" value="${item.id}"/>
	<a href="javascript:;">编辑</a></form> |
	 <form id="frm_del${item.id}" action="/admin/lbie.do" method="post" style="display:inline">
	 <input type="hidden" name="ac" id="ac" value="del"/>
	 <input type="hidden" name="id" value="${item.id}"/>
	 <a href="javascript:;">删除</a></form>
	 </td>
</tr>
</c:forEach>
<!--  快速转换位置按钮  -->
  <tr align="right" bgcolor="#EEF4EA">
  <td height="20" colspan="10" align="center">
    <a href="javascript:;">新增用户</a>
 </td>
 </tr>
<tr align="right" bgcolor="#EEF4EA">
</tr>
</table>
</body>
</html>