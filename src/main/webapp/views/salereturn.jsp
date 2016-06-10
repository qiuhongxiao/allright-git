<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="javax.servlet.http.HttpSession"
	%>
<%-- <% String canreturnquantity=(String)session.getAttribute("canreturnquantity"); %> --%>
<%-- <%
    String contextPath = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    Map map = (Map)ActionContext.getContext().getParameters();    
    String totalmoney=(String)map.get("totalmoney");
    //out.println("到底有没有刷新1"+totalmoney);
    if(totalmoney==null){
    	totalmoney="0.0";
    }
    
    String orderno=(String)session.getAttribute("order_no");
    String productno = (String)session.getAttribute("product_no");
    Integer returnquantity =(Integer)session.getAttribute("returnquantity");
    Integer canreturnquantity =(Integer)session.getAttribute("canreturnquantity");
    String salereturnmoney1 = (String)session.getAttribute("salereturnmoney1");
//    out.println(""+allhangmoney);
//    out.println(""+hangcustomernamestring);
//    out.println(""+hangtelephonestring);
    if(orderno==null){
    	orderno="";
    }
    if(productno==null){
    	productno="";
    }
    if(returnquantity==null){
    	returnquantity=0;
    }
    if(canreturnquantity==null){
    	canreturnquantity=0;
    }
    if(salereturnmoney1==null){
    	salereturnmoney1="0";
    }
    //接受成功消息
    Map map1 = (Map)ActionContext.getContext().get("request");   
    String successmessage=(String)map1.get("successmessage");
    //out.println("到底有没有刷新1"+totalmoney);
    if(successmessage==null){
     	successmessage="";
     }
//     Map map1 = (Map)ActionContext.getContext().get("request");   
    String permissionmessage=(String)map1.get("permissionmessage");    
    if(permissionmessage==null){
    	permissionmessage="";
     }
%> --%>
<html>
<head>
<meta charset="UTF-8">
<title>销售</title>
<jsp:include page="insertrely.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
         /* jquery入口*/
         $(function() {
             loadgrid(); //加载datagrid
         });
         /* 加载datagrid列表*/
             function loadgrid(){
        	 	//alert("into grid");
            	 var order_no = document.getElementById("orderno").value;
            	 var product_no = document.getElementById("productno").value;
                 $('#grid').datagrid({
                 title : '销售',
                 url : "http://localhost:8080/allright-git/canreturnitem?order_no="+order_no+"&product_no="+product_no,//可以退货的项目
                 loadMsg : '正在加载…',  //当从远程站点载入数据时，显示的一条快捷信息。
                 fit : true,  //窗口自适应
                 nowrap: false, //设置为true，当数据长度超出列宽时将会自动截取
                 fitColumns : true, // 自动适应列宽
                 rownumbers:"true",
                 singleSelect : true, // 每次只选中一行
                 sortName : 'order_no', //默认排序字段
                 sortOrder : 'asc', // 升序asc/降序desc
                 striped : true,  // 隔行变色  
                 pagination : false,  // 在底部显示分页工具栏
                 //pageNumber : 1, //初始化页码 
                 //pageSize : 3,  // 指定每页的大小，服务器要加上page属性和total属性
                 //pageList : [ 3, 20, 30, 50 ], // 可以设置每页记录条数的列表，服务器要加上rows属性
                 //rownumbers : true, // 在最前面显示行号 
                 idField : 'id', // 主键属性
                 // 冻结列,当很多咧出现滚动条时该列不会动
                 frozenColumns : [ [
                 
                 {title : '订单编号', width : '300', field : 'order_no', sortable : true}, 
                 {title : '商品编号', width : '100', field : 'product_no', sortable : true}, 
                 {title : '商品名称', width : '100',    field : 'productname',sortable : true},
                 {title : '销售时间', width : '300', field : 'saletime', sortable : true}
                 ] ],
                 columns : [ [ 
                 {title : '单价', width : '100', field : 'price', sortable : false}, 
                 {title : '折扣', width : '200',    field : 'discount', sortable : false},
                 
                 {title : '可退货数量', width : '200',    field : 'quantity', sortable : false}  ,
                 {title : '金额小计', width : '200',    field : 'saleprice', sortable : false}
                 ] ],       
                 // 工具栏按钮
                 toolbar:"#tb",
                 
             });
         }
         </script>
<script type="text/javascript" charset="utf-8">
         /* 刷新grid*/
         function reload(){           	
             $('#grid').datagrid('reload');
         }
         /* 刷新grid*/
         function returncheck(){           	
             loadgrid();
         }
         function go( ) {//定义函数 
        	 window.location.href="salereturn.jsp";//页面跳转 
        	 } 
         /*对选中的记录，把订单编号和产品编号一起传送到服务器端*/
         function calculatereturnmoney(){
        	// alert("进入 calculatereturnmoney方法");
        	// window.setTimeout("go()",1000);
        	 //var row = $('#grid').datagrid[1,1].toString();
             //如果没有选中记录
//              if(row == null){
                 //$.messager.alert("提示", "请选择一条记录"+row,'info');
               var a =  ${canreturnquantity} ;
             if(document.getElementById("returnquantity").value == null||document.getElementById("returnquantity").value ==0||document.getElementById("returnquantity").value>a){
            	 $.messager.alert("提示:","数量不能为零，请输入小于可退货数量的数字","info");
                 return false;
              
             }    
            // }            
            //提交到后台的action
                      
            
             var orderno = document.getElementById("orderno").value;
        	 var productno = document.getElementById("productno").value;
        	 var returnquantity = document.getElementById("returnquantity").value;
        	 
        	 var h="http://localhost:8080/allright-git/salereturnmoney?order_no="+orderno+"&product_no="+productno+"&returnquantity="+returnquantity;
        	 document.getElementById("calculatereturnmoney").href=h;

         }
         /*对选中的记录，订单确认*/
         function returnconfirm(){
        	 var row = $('#grid').datagrid('getSelected');
             //如果没有选中记录
//              var a = row.quantity;
        	 
        	 var a = ${canreturnquantity} ; 
             if(document.getElementById("returnquantity").value == null||document.getElementById("returnquantity").value ==0){
                 $.messager.alert("提示:","数量不能为零，请输入小于可退货数量的数字","info");
              return false;
             }    
//              if(document.getElementById("returnquantity").value == null||document.getElementById("returnquantity").value ==0){
//                  $.messager.alert("提示", "请输入数量",'info');
              
//              }  
            //提交到后台的action
                      
            var orderno = document.getElementById("orderno").value;
        	 var productno = document.getElementById("productno").value;
        	 var returnquantity = document.getElementById("returnquantity").value;
        	 var returnreason = document.getElementById("returnreason").value;
        	 
        	 var h="http://localhost:8080/allright-git/salereturnconfirm?returnquantity="+returnquantity+"&order_no="+orderno+"&product_no="+productno+"&returnreason="+returnreason;
        	 document.getElementById("returnconfirm").href=h;
        	 

         }
         
         
       
         
         
		</script>
</head>
<body>
	
	<div style="padding: 3px; background-color: #ccddaa; font-size: 20;">

	</div>
	<div style="width: 100%; height: 90%; padding: 0px; overflow: hidden">
		<table id="grid"></table>
	</div>

	<div id="tb" style="border: 5px; background-color: #ccddaa;">
	<div>
			<br> &nbsp;&nbsp;<label style="font-size: 20px">订单编号：</label><input
				id="orderno" name="orderno" type="text"
				value="${orderno }" size=36 height=20 class="easyui-validatebox"
				data-options="required:true,missingMessage:'订单编号为必填项'"><br><br>
			&nbsp;&nbsp;<label style="font-size: 20px">商品编号：</label><input
				id="productno" name="productno" type="text" value="${productno}"  size=36
				height=20 class="easyui-validatebox"
				data-options="required:true,missingMessage:'商品编号为必填项'">&nbsp;<a
				id="returncheck" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok',plain:true" onclick="loadgrid()"
				style="border: 2px #999999 solid"><lable style="font-size:20px">查找</lable></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style="color:red;font-size:23px"></label>
			&nbsp;&nbsp;&nbsp;<label style="color:red;font-size:23px"><%-- <%=permissionmessage%> --%></label>
			<br>
		</div>

			
			<br>&nbsp;&nbsp;<label style="font-size: 20px">退货数量：</label></label><input
 			id="returnquantity" name="returnquantity" type="text" value="${returnquantity }" size=11 onkeyup="if(isNaN(value))execCommand('undo')" 
 			onafterpaste="if(isNaN(value))execCommand('undo')"
 			height=20>&nbsp;&nbsp;<a
				id="calculatereturnmoney" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok',plain:true" onclick="calculatereturnmoney()"
				style="border: 2px #999999 solid"><lable style="font-size:20px">计算退货金额：</lable></a>
				&nbsp;&nbsp;<input 
				id="money" type="text" 
				style="border: none; background-color: #ccddaa; width: 160px; height: 60px; font-size: 50px" value="${salereturnmoneytwo }"  >
				<input 
				id="returnreason" type="text" size=200
				style="width: 500px; height: 36px; font-size: 20px" value="质量不满意">
				<a
				id="returnconfirm" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok',plain:true" onclick="returnconfirm()"
				style="border: 2px #999999 solid"><lable style="font-size:20px">退货确认</lable></a>
				<br><br>
	</div>


	


</body>
</html>