<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/main/page/jscss.jsp" %>
<script src="/main/js/jquery.js" type="text/javascript"></script>

<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F8F9FA;
}

</style>


<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" height="29" valign="top" background="/main/images/mail_leftbg.gif"><img src="/main/images/left-top-right.gif" width="17" height="29" /></td>
    <td width="935" height="29" valign="top" background="/main/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">${msgboxStr}</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="/main/images/mail_rightbg.gif"><img src="/main/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td height="71" valign="middle" background="/main/images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="100%" height="138" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="13" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td valign="top"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td class="left_txt">当前位置：${msgboxStr}</td>
          </tr>
          <tr>
            <td height="20"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%" height="55" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="10%" height="55" valign="middle"><img src="/main/images/title.gif" width="54" height="55"/></td>
                <td width="90%" valign="top"><span class="left_txt2">在这里，您可以根据您的网站要求，修改设置网站的</span><span class="left_txt3">产品</span><span class="left_txt2">！</span><br/>
                          <span class="left_txt2">包括</span><span class="left_txt3">新闻标题、内容等等。</span></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              <tr>
                <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;${msgboxStr}信息</td>
              </tr>
            </table></td>
          </tr>
		  
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<form name="form1" method="post" action="/admin/news.do">
			<input type="hidden" name="ac" value="save${meta}" />
			<input type="hidden" name="id" value="${ne_w.id}" />
			   <tr>
			   <td height="30" align="right" class="left_txt2">新闻HOT度：</td>
                <td>&nbsp;</td>
                <td height="30">
					<select name="hot">
						<option id="h_1" value="1">最高级</option>
						<option id="h_2" value="2">2级</option>
						<option id="h_3" value="3">3级</option>
						<option id="h_4" value="4" selected>4级(默认)</option>
						<option id="h_5" value="5">5级</option>
						<option id="h_6" value="6">最低级</option>
					</select>
				</td>
                 <td height="30" align="right" class="left_txt2">新闻类别：</td>
                <td>&nbsp;</td>
                <c:if test="${meta=='update'}">
                </c:if>
                <td height="30">
					<select name="cid">
					<option selected value=-1>选择类别</option>
					<c:forEach var="item" items="${leiBie}">
						<option id="_${item.cid}" value=${item.cid}>${item.cname}</option>
					</c:forEach>
					</select>
				</td>
				<script>
                $(function(){
                	$('#_${ne_w.cid}').attr('selected','selected');
                	$('#h_${ne_w.hot}').attr('selected','selected');
                });
                </script>
              </tr>
              <tr>
				<td width="10%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">新闻名称：</td>
                <td width="1%" bgcolor="#f2f2f2">&nbsp;</td>
                <td width="10%" height="30" bgcolor="#f2f2f2"><input name="title" type="text" size="30" value="${ne_w.title}" /></td>
              </tr>
			<tr>
			<td height="50" align="right" class="left_txt2">新闻说明：</td>
			 <td height="50" colspan="6" align="left" >
				&nbsp;&nbsp;<textarea name="note" cols="70" rows="5"><c:if test="${meta=='update'}">${ne_w.note}</c:if></textarea>
			 </td>
			  <td height="50">
			  </td>
			 </tr>
			 <tr>
			 <td height="40" colspan="6" align="center" >
				<input type="submit" /><input type="button" onclick="history.go(-1)" value="取消" />
			 </td>
			 </tr>
              <tr>
                <td height="30" colspan="4" align="right" >&nbsp;</td>
              </tr>
			  </table>
  
</table>

</body>
