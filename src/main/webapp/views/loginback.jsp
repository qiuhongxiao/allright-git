<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="留学资讯服务">
    <meta name="author" content="Holinc">
    <link rel="icon" href="resources/favicon.ico">
    
	<title>留学资讯服务在线平台</title>
	
	<!-- Bootstrap core CSS -->
    <!-- <link href="resources/dist/css/bootstrap.min.css" rel="stylesheet"> -->
<script type='text/javascript'>
    (function(m, ei, q, i, a, j, s) {
        m[a] = m[a] || function() {
            (m[a].a = m[a].a || []).push(arguments)
        };
        j = ei.createElement(q),
            s = ei.getElementsByTagName(q)[0];
        j.async = true;
        j.charset = 'UTF-8';
        j.src = i + '?v=' + new Date().getUTCDate();
        s.parentNode.insertBefore(j, s);
    })(window, document, 'script', '//eco-api.meiqia.com/dist/meiqia.js', '_MEIQIA');
    _MEIQIA('entId', 8257);
</script>
</head>
    <style>
    	body{
    		height: 94vh;
    	}
    	.container{
    		height:90%;
    		text-align: center;
    	}
    	.footer{
    		height:10%;
    		text-align: center;
    	}
    </style>
<body>
<div class="container">
<h1>
	Hello world!  
</h1>
<P>  The time on the server is ${serverTime}. </P>
<form action="${pageContext.request.contextPath}/login" method="post">
	<input type="text" name="username" /><br />
	<input type="password" name="password" /><br />
	<span><font color="red">${errorInfo }</font></span><br />
	<input type="submit" value="提交">
</form>
</div>
<div class="footer">
留学资讯服务在线平台  v1.0<br />
SCAU Copyright ©&nbsp; 2016华南农业大学. All rights reserved.
</div>
</body>
</html>
