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
        <title>产品类型报表</title>
        <jsp:include page="insertrely.jsp"></jsp:include>
        <script type="text/javascript" charset="utf-8">
        function doSearch(){
        	var first = $('#first').datebox('getValue');
        	var last =  $('#last').datebox('getValue');
        	//alert("first="+first);
        	if(first>last){
        		alert("起始时间:"+first+"不能大于终止时间:"+last+"!");
        		return false;
        	}else{
        	    var hreftemp = "http://localhost:8080/allright-git/producttypereport?firstDate="+first+"&lastDate="+last;
        	    document.getElementById("confirm").href=hreftemp;
        }
        	
        }
        function doSearchPie(){
        	var first = $('#first').datebox('getValue');
        	var last =  $('#last').datebox('getValue');
        	//alert("first="+first);
        	if(first>last){
        		alert("起始时间:"+first+"不能大于终止时间:"+last+"!");
        		return false;
        	}else{
        	    var hreftemp = "http://localhost:8080/allright-git/producttypereportPie?firstDate="+first+"&lastDate="+last;
        	    document.getElementById("confirmPie").href=hreftemp;
        }
        	
        }
        </script>
    </head>
    <body>    
     <div id="tb" style="padding:3px;background-color:#ccddaa;font-size:18px" >
		
		<center><span> 产品类别固定期间销售额:从:</span><input id="first" type="text" class="easyui-datebox" required="required" value="new Date()"></input>零时
	&nbsp;到:</span><input id="last" type="text" class="easyui-datebox" required="required" value="new Date()"></input>零时
			<a target="monthcountiframe" id="confirm" class="easyui-linkbutton" plain="true" onclick="doSearch()" data-options=" iconCls:'icon-search',plain:true"><b>确定</b></a>&nbsp;&nbsp;
			<a target="monthcountiframe" id="confirmPie" class="easyui-linkbutton" plain="true" onclick="doSearchPie()" data-options=" iconCls:'icon-search',plain:true"><b>饼图</b></a></center>
			
		&nbsp;&nbsp;&nbsp;<label style="color:red;font-size:23px"><%-- <%=permissionmessage%> --%></label>
	    </div>
    <div title="About" data-options="iconCls:'icon-tip'">
                <iframe src="http://localhost:8080/allright-git/producttypereport" name="monthcountiframe" style="border: 4px; width: 100%; height: 98%;" align="middle"
                scrolling="yes" frameborder="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>
    </div>
      
    </body>
</html>