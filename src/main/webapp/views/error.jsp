<%@ page language="java"   pageEncoding="utf-8"%>
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
    
    <title>error</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  <br>
   <b>  亲~不好意思，您没有权限哟~请<a href="http://localhost:8080/allright-git/userabout">点此这里</a>查看您的具体权限~么么哒~</b>
  </body>
</html>
