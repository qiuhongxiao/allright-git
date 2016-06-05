<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextPath + "/";
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var mis = mis || {};
mis.path = '<%=contextPath%>';
</script>

<!-- 引入JQuery -->
<script type="text/javascript" src="<%=request.getContextPath() %>/res/jquery-easyui-1.4.5/jquery.min.js"></script>
<!-- 引入EasyUI -->
<script type="text/javascript" src="<%=request.getContextPath() %>/res/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/res/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
 <!-- 引入EasyUI的样式文件-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/res/jquery-easyui-1.4.5/themes/material/easyui.css" type="text/css"/>
<!-- 引入EasyUI的图标样式文件-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/res/jquery-easyui-1.4.5/themes/icon.css" type="text/css"/>

<script type="text/javascript" src="<%=contextPath%>/res/js/handlebars-v3.0.3.js"></script>