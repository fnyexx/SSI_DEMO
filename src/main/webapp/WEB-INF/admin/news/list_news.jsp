<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻列表管理</title>
<%@ include file="/main/page/jscss.jsp" %>
<script src="/main/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="/main/js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/main/js/jBox/jquery.jBox-zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="/main/js/jBox/Blue/jbox.css" />
<style type="text/css">
 form {margin:0;border:0;}
 #pager span a{ margin:0 5px;}
</style>
<script language="javascript">
<!-- -->
$(function(){
	$("#scp").change(function(){
		location.href=("/admin/news.do?cid="+$("#scp").val());
	})
});
</script>
</head>
<body leftmargin="8" topmargin="8" background='/main/images/allbg.gif'>

<%--  邮件列表   --%>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="/main/images/tbg.gif">&nbsp;新闻列表&nbsp;
	<select id="scp">
	<option <c:if test="${cid==-1}">selected</c:if> value=-1>所有新闻</option>
	<c:forEach var="item" items="${leiBie}">
		<option <c:if test="${cid==item.cid}">selected</c:if> value=${item.cid}>${item.cname}</option>
	</c:forEach>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;共计<span style="color:red">${newsCount}</span>条
	</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">ID号</td>
	<td width="6%">类别(可移动)</td>
	<td width="30%">标题(鼠标移到标题查看内容)</td>
	<td width="5%">热门度</td>
	<td width="15%">发布时间</td>
	<td width="10%">操作</td>
</tr>
<c:forEach var="item" items="${news}">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FFB584';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td>${item.id }</td>
	<td>
		<select id='se_${item.id}' onmousedown="$('#cpval').val(${item.id}+'_'+this.value)" onchange="if(confirm('确定移动到--['+$('#se_${item.id} option:selected').text()+']--类别吗？')){location.href='/admin/news.do?ac=ydlb&id=${item.id }&cid='+$('#scp').val()+'&ycid='+this.value}else{$('#'+$('#cpval').val()).attr('selected','selected')}">
		<c:forEach var="lei" items="${leiBie}">
		<option id='${item.id}_${lei.cid}' <c:if test="${item.cid==lei.cid}">selected</c:if> id="d" value=${lei.cid}>${lei.cname}</option>
		</c:forEach>
		</select>
		<input type="hidden" id="cpval" value="" />
	</td>
	<td><a href="javascript:;" title="内容_:${item.note}">${item.title }</a></td>
	<td>${item.hot}</td>
	<td>${item.sendtime }</td>
	<td>
	<form id="frm_edit${item.id}" action="/admin/news.do" method="post" style="display:inline">
	<input type="hidden" name="ac" value="update"/>
	<input type="hidden" name="id" value="${item.id}"/>
	<input type="hidden" name="cid" value="${item.cid}"/>
	<a href="javascript:;" onclick="$('#frm_edit${item.id}}')[0].submit();">编辑</a></form> |
	 <form id="frm_del${item.id}" action="/admin/news.do" method="post" style="display:inline">
	 <input type="hidden" name="ac" id="ac" value="del"/>
	 <input type="hidden" name="cid" value="${cid}"/>
	 <input type="hidden" name="id" value="${item.id}"/>
	 <a href="javascript:;" onclick="if (confirm('您确定要删除吗?')){ $('#frm_del${item.id}')[0].submit();}" >删除</a></form>
	 </td>
</tr>
</c:forEach>
<!--  快速转换位置按钮  -->
  <tr align="right" bgcolor="#EEF4EA">
  <td height="20" colspan="10" align="center">
    <a href="/admin/news.do?ac=insert">新增新闻</a>
 </td>
 </tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center" id="pager">${pageNumbers}</td>
</tr>

</table>
</body>
</html>