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


 <script type="text/javascript" charset="utf-8">
//全局的ajax访问，处理ajax清求时sesion超时
 $.ajaxSetup({ 
     contentType:"application/x-www-form-urlencoded;charset=utf-8", 
     complete:function(XMLHttpRequest,textStatus){ 
             var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
             if(sessionstatus=="timeout"){ 
                         //如果超时就处理 ，指定要跳转的页面
                                 //window.location.replace("http://localhost:8080/allright-git/mvc/error"); 
            	 window.location.replace("error.jsp"); 
            	//var iframe=document.createElement("div");
            	// top.location.replace("error.jsp"); 
            	
                         } 
              } 
     } 
   );
 

</script>