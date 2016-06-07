<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
    pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// User user = (User)session.getAttribute("user");
// int id = user.getId();
// String username=user.getUsername();
// String password = user.getPassword();
// String permission = user.getPermission();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>关于自己</title>
    <jsp:include page="insertrely.jsp"></jsp:include>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body background="<%=request.getContextPath() %>/views/5.gif">
  <CENTER>
 <font face="华文彩云" size =6 color =blue>水果君pos系统温馨提示~~~</font><br><br>
<table border="5" align=center width=50%  height=80% cellspacing =5 cellpadding=5 bordercolor=pink>
<tr>
<td>用户名</td>
<td>${username }</td>
</tr>
<tr>
<td>登陆时间</td>
<td><%=new Date() %></td>
</tr>
<tr>
<td>挂账权限</td>
<td>${hangcredit }</td>
</tr>
<tr>
<td>挂账支付权限</td>
<td>${hangpay }</td>
</tr>
<tr>
<td>报表权限</td>
<td>${reportmanage }</td>
</tr>
<tr>
<td>权限控制</td>
<td>${permissionmanage }</td>
</tr>
<tr>
<td>退货权限</td>
<td>${salereturn }</td>
</tr>

</table>
</CENTER>
<br><br><br><br><script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"2","bdPos":"left","bdTop":"290"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
  </body>
</html>
