<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Map" %>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%-- <%
    String contextPath = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
/*String contextPath = request.getContextPath();*/

 Map map1 = (Map)ActionContext.getContext().get("request");   
    String permissionmessage=(String)map1.get("permissionmessage");    
    if(permissionmessage==null){
    	permissionmessage="";
     }
%> --%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>easyui学习笔记</title>
        <jsp:include page="insertrely.jsp"></jsp:include>
        <script type="text/javascript" charset="utf-8">
        function doSearch(){
        	var year = document.getElementById("year").value;
        	var hreftemp = "http://localhost:8080/allright-git/salemonthcountreport?year="+year;
        	document.getElementById("confirm").href=hreftemp;
        	
        }
        function doSearchPie(){
        	var year = document.getElementById("year").value;
        	var hreftemp = "http://localhost:8080/allright-git/salemonthcountreportPie?year="+year;
        	document.getElementById("confirmPie").href=hreftemp;
        	
        }
        </script>
    </head>
    <body>    
     <div id="tb" style="padding:3px;background-color:#ccddaa;" >
		
		<center><span> 请选择需要查看月份销售金额的年份:</span><input id="year" name="year" type="text" size="6" required="required"  style="line-height:20px;border:1px solid #ccc " value="2016" onkeyup="this.value=this.value.replace(/\D/g,'')"/>年	
			<a target="monthcountiframe" id="confirm" class="easyui-linkbutton" plain="true" onclick="doSearch()" data-options=" iconCls:'icon-search',plain:true" style="border: 2px #999999 solid"><b>确定</b></a>&nbsp;&nbsp;
			<a target="monthcountiframe" id="confirmPie" class="easyui-linkbutton" plain="true" onclick="doSearchPie()" data-options=" iconCls:'icon-search',plain:true" style="border: 2px #999999 solid"><b>饼图</b></a></center>
			
		&nbsp;&nbsp;&nbsp;<label style="color:red;font-size:23px"><%-- <%=permissionmessage%> --%></label>
	    </div>
    <div title="About" data-options="iconCls:'icon-tip'">
                <iframe src="http://localhost:8080/allright-git/salemonthcountreport" name="monthcountiframe" style="border: 4px; width: 100%; height: 98%;" 
                scrolling="yes" frameborder="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>
                
    </div>
      
    </body>
</html>