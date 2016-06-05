<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>水果pos机</title>
</head>
<body bgcolor=#FFFFFF background="<%=request.getContextPath() %>/views/1.gif">
<p><p><p>
  <center> 
  <font face="华文彩云" size =9 color =blue>欢迎使用本系统！</font>  
    <p><p><p><p><p>
    <input type="button" name="1" value="订单操作" style="font-size:20px" onclick="window.location='salesitem.jsp'"><p>
     <input type="button" name="2" value="管理水果" style="font-size:20px" onclick="window.location='operate.jsp'"><p>
      <input type="button" name="3" value="退出" style="font-size:20px" onclick="window.location='login.jsp'">
    </center>
</body>
</html>