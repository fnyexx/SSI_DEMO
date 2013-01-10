<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>产品列表管理</title>
<%@ include file="/main/page/jscss.jsp" %>
<script src="/main/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="/main/js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/main/js/jBox/jquery.jBox-zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="/main/js/jBox/Blue/jbox.css" />
<style type="text/css">
 form {margin:0;border:0;}
 #pager span a{ margin:0 5px;}

 /* 上传组件 */
#at_upload { width:100%; height:auto; display:none }
#at_upload .loadingbar { width:100%; height:30px; line-height:30px; overflow:hidden; padding:0; margin:0; border:0; border-bottom:1px solid #eee; }
#at_upload .loadingbar dt, #at_upload .loadingbar dd, #at_upload .loadingbar a { float:left; overflow:hidden; padding:0; margin:0; border:0; display:inline }
#at_upload .loadingbar dt { width:auto; margin-left:12px }
#at_upload .loadingbar dt.tit { margin-left:16px; color:#666 }
#at_upload .loadingbar .bar { width:100px; height:8px; border:1px solid #FFAE00; background:#fffaf0; margin:10px 0 0 12px }
#at_upload .loadingbar .bar span { width:0px; height:8px; overflow:hidden; padding:0; margin:0; border:0; background:#FFAE00; display:block }
#at_upload .loadingbar .del { width:100px; margin-left:12px; }
#at_upload .loadingbar .del a { color:#0080C8 }
#at_upload .loadingbar .del s { text-decoration:none; color:#FF9000 }
#at_upload_exp_span { margin:0; padding:0; color:#999 }
#at_upload_exp_span a { margin-left:12px; text-decoration:underline; color:#0080C8 }
</style>
<script language="javascript">
function viewArc(aid){
	if(aid==0) aid = getOneItem();
	window.open("archives.asp?aid="+aid+"&action=viewArchives");
}
function editArc(aid){
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=editArchives";
}
function updateArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=makeArchives&qstr="+qstr+"";
}
function checkArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=checkArchives&qstr="+qstr+"";
}
function moveArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=moveArchives&qstr="+qstr+"";
}
function adArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=commendArchives&qstr="+qstr+"";
}
function delArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=delArchives&qstr="+qstr+"";
}

//获得选中文件的文件名
function getCheckboxItem()
{
	var allSel="";
	if(document.form2.id.value) return document.form2.id.value;
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
			if(allSel=="")
				allSel=document.form2.id[i].value;
			else
				allSel=allSel+"`"+document.form2.id[i].value;
		}
	}
	return allSel;
}

//获得选中其中一个的id
function getOneItem()
{
	var allSel="";
	if(document.form2.id.value) return document.form2.id.value;
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
				allSel = document.form2.id[i].value;
				break;
		}
	}
	return allSel;
}
function selAll()
{
	for(i=0;i<document.form2.id.length;i++)
	{
		if(!document.form2.id[i].checked)
		{
			document.form2.id[i].checked=true;
		}
	}
}
function noSelAll()
{
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
			document.form2.id[i].checked=false;
		}
	}
}
<!-- -->
$(function(){
	$("#scp").change(function(){
		location.href=("/admin/cpin.aspx?cid="+$("#scp").val());
	})
});
function showUpImg(id,cid){
				  
	// var html="<div style='width:750px; margin-left:65px; margin-top:3px; margin-bottom:10px;'><div id='upload'><div id='at_uploadbutton'></div><div id='at_upload'><input type='hidden' id='files' name='files' value=''/></div><div id='number'></div><s class='e10'></s></div></div>";
	var html='<div style=" width:750px; margin-left:65px; margin-top:3px; margin-bottom:10px;">'
					+'<div id="upload">'
						+'<div id="at_uploadbutton"><!--insertUploadButton--></div>'
						+'<div id="at_upload"><input type="hidden" id="files" name="files" value=""/><!--储存要使用的附件--></div>'
						+'<div id="number"></div><s class="e10"></s></div></div>'
				  +'<script type="text/javascript" src="/main/upload_component/upload.js">'+'</'+'script>'
				  +'<script type="text/javascript">'
				    +'  var uploadFileApply = new at_upload("/main/upload_component/button.swf", "/admin/upcpin.aspx?idcid='+id+'_'+cid+'", $("#at_uploadbutton").get(0));' 
					  +'uploadFileApply.onError = function(index, msg)  {'
				      +'window.alert(msg); }'
				  +'</'+'script>';
	        $.jBox(html,'上传图片');
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/main/images/allbg.gif'>

<%--  邮件列表   --%>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="/main/images/tbg.gif">&nbsp;产品列表&nbsp;
	<select id="scp">
	<option <c:if test="${cid==-1}">selected</c:if> value=-1>所有产品</option>
	<c:forEach var="item" items="${leiBie}">
		<option <c:if test="${cid==item.cid}">selected</c:if> value=${item.cid}>${item.cname}</option>
	</c:forEach>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;共计<span style="color:red">${chanPinCount}</span>条
	</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">ID号</td>
	<td width="4%">编号</td>
	<td width="6%">类别(可移动)</td>
	<td width="8%">名称(点击传图)</td>
	<td width="15%">产品说明</td>
	<td width="15%">发布时间</td>
	<td width="15%">操作</td>
</tr>
<c:forEach var="item" items="${chanPin}">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FFB584';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td>${item.id }</td>
	<td>${item.cnum }</td>
	<td>
		<select id='se_${item.id}' onmousedown="$('#cpval').val(${item.id}+'_'+this.value)" onchange="if(confirm('确定移动到--['+$('#se_${item.id} option:selected').text()+']--类别吗？')){location.href='/admin/cpin.do?ac=ydlb&id=${item.id }&cid='+$('#scp').val()+'&ycid='+this.value}else{$('#'+$('#cpval').val()).attr('selected','selected')}">
		<c:forEach var="lei" items="${leiBie}">
		<option id='${item.id}_${lei.cid}' <c:if test="${item.cid==lei.cid}">selected</c:if> id="d" value=${lei.cid}>${lei.cname}</option>
		</c:forEach>
		</select>
		<input type="hidden" id="cpval" value="" />
	</td>
	<td><a onclick="showUpImg(${item.id},${item.cid});" href="javascript:;">${item.cname }</a></td>
	<td>${item.note }</td>
	<td>${item.sendtime }</td>
	<td>
	<form id="frm_edit${item.id}" action="/admin/cpin.do" method="post" style="display:inline">
	<input type="hidden" name="ac" value="update"/>
	<input type="hidden" name="id" value="${item.id}"/>
	<input type="hidden" name="cid" value="${item.cid}"/>
	<a href="javascript:;" onclick="$('#frm_edit${item.id}}')[0].submit();">编辑</a></form> |
	 <form id="frm_del${item.id}" action="/admin/cpin.do" method="post" style="display:inline">
	 <input type="hidden" name="ac" id="ac" value="del"/>
	 <input type="hidden" name="cid" value="${cid}"/>
	 <input type="hidden" name="id" value="${item.id}"/>
	 <a href="javascript:;" onclick="if (confirm('您确定要删除吗?')){ $('#frm_del${item.id}')[0].submit();}" >删除</a></form>|
	 
	 <form id="frm_show${item.id}" action="/admin/cpimg.aspx" method="post" style="display:inline">
	<input type="hidden" name="cpid" value="${item.id}"/>
	<a href="javascript:;" onclick="$('#frm_show${item.id}}')[0].submit();">看产品图</a></form> 
	 </td>
</tr>
</c:forEach>
<!--  快速转换位置按钮  -->
  <tr align="right" bgcolor="#EEF4EA">
  <td height="20" colspan="10" align="center">
    <a href="/admin/cpin.do?ac=insert">新增产品</a>
 </td>
 </tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center" id="pager">${pageNumbers}</td>
</tr>

</table>
</body>
</html>