<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	 %>
 <%
    String contextPath = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
   //Map map = (Map)ActionContext.getContext().getParameters();    
    String allhangmoney=(String)request.getAttribute("allhangmoneytemp");
   // String hangcustomernamestring = (String)session.getAttribute("hangcustomernamestring");
  //  String hangtelephonestring =(String)session.getAttribute("hangtelephonestring");
//    out.println(""+allhangmoney);
//    out.println(""+hangcustomernamestring);
//    out.println(""+hangtelephonestring);
   out.println("到底有没有刷新:"+allhangmoney);
    if(allhangmoney==null){
    	allhangmoney="0.0";
    } 
    out.println("判断后："+allhangmoney);
    
%>  
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
            	 var hangcustomername=document.getElementById("hangcustomername").value;
            	 var hangtelephone=document.getElementById("hangtelephone").value;
            	 var href="http://localhost:8080/allright-git/hangcreditcheck?hangcustomername="+hangcustomername+"&hangtelephone="+hangtelephone;
                 $('#grid').datagrid({
                 title : '挂账支付',
                 url : href,
                 loadMsg : '正在加载…',  //当从远程站点载入数据时，显示的一条快捷信息。
                 fit : true,  //窗口自适应
                 nowrap: false, //设置为true，当数据长度超出列宽时将会自动截取
                 fitColumns : true, // 自动适应列宽
                 rownumbers:"true",
                 singleSelect : true, // 每次只选中一行
                 sortName : 'saleprice', //默认排序字段
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
                                   
                 {title : '订单号', width : '250', field : 'order_no', sortable : true}, 
                 {title : '商品号', width : '100',    field : 'product_no',sortable : true}
                 ] ],
                 columns : [ [ 
                 {title : '商品名称', width : '100', field : 'productname', sortable : false}, 
                 {title : '单价', width : '100', field : 'price', sortable : false}, 
                 {title : '折扣', width : '50',    field : 'discount', sortable : false},
                 {title : '购买数量', width : '100', field : 'quantity', sortable : false},
                 {title : '销售价格', width : '100', field : 'saleprice', sortable : false},
                 
                 {title : '销售时间', width : '200',    field : 'saletime', sortable : false}  
                
                 ] ],       
                 // 工具栏按钮
                 //toolbar:"#tb",
                 
             });
         }
         </script>
<script type="text/javascript" charset="utf-8">
	function caculateallhangmoney() {
		//alert("dslfjdfkdls");
		var length1 = $("#grid").datagrid('getRows').length;        	
	   	 if(length1<=0){
	   		 $.messager.alert("提示", "没有任何挂账商品！",'info');
	   	 }else{
		var hangcustomername = document.getElementById("hangcustomername").value;
		var hangtelephone = document.getElementById("hangtelephone").value;
		var h = "http://localhost:8080/allright-git/caculateallhangmoney?hangcustomername="
				+ hangcustomername + "&hangtelephone=" + hangtelephone;
		document.getElementById("caculateallhangmoney").href = h;
	   	 }
	}
	/*确认购买支付*/
	function hangpay() {
		//alert("dslfjdfkdls");
		var length1 = $("#grid").datagrid('getRows').length;        	
   	 if(length1<=0){
   		 $.messager.alert("提示", "没有任何挂账商品！",'info');
   	 }else{
		var hangcustomername = document.getElementById("hangcustomername").value;
		var hangtelephone = document.getElementById("hangtelephone").value;
		var h = "http://localhost:8080/allright-git/hangcreditpay?hangcustomername=" + hangcustomername
				+ "&hangtelephone=" + hangtelephone;
		document.getElementById("hangpay").href = h;
   	 }
	}

	/*查找客户对应未付钱记录*/
	function check() {

		loadgrid();

	}
	/*找零*/
	function retail() {
		var pay = document.getElementById("pay").value;

		var total =<%=allhangmoney%>;
		var change = pay - total;
		var change1=change.toFixed(2);
		document.getElementById("change").value = change1;
	}
</script>
</head>
<body>
	<div style="padding: 3px; background-color: #ccddaa; font-size: 20;">
		<div style="padding: 10px; font-size: 45;">
			<a id="caculateallhangmoney" class="easyui-linkbutton" onclick="caculateallhangmoney()"
				data-options="iconCls:'icon-ok',plain:true"
				style="border: 3px solid #999999; background-color: #ccddaa; width: 220px; height: 60px; font-size: 38px"><label style="font-size: 30px">计算总价:</label></a>&nbsp;<%=allhangmoney %> &nbsp;&nbsp;<label style="font-size: 35;">支付:</label><input
				id="pay" type="text"
				style="border: none; background-color: #ccddaa; width: 160px; height: 60px; font-size: 50px" onkeyup="if(isNaN(value))execCommand('undo')">
			<button onclick="retail()"
				style="border: 3px solid #999999; background-color: #ccddaa; width: 110px; height: 60px; font-size: 38px">找零:</button>
			<input id="change" type="text"
				style="border: none; background-color: #ccddaa; width: 160px; height: 60px; font-size: 50px">
		</div>
		<div>
			<br> &nbsp;&nbsp;<label style="font-size: 20px">客户姓名：</label><input
				id="hangcustomername" name="hangcustomername" type="text"
				value="${hangcustomernamestring }" size=6 height=20 class="easyui-validatebox"
				data-options="required:true,missingMessage:'客户姓名为必填项'">
			&nbsp;<label style="font-size: 20px">客户电话：</label><input
				id="hangtelephone" name="hangtelephone" type="text" value="${hangtelephonestring }" size=11
				height=20 class="easyui-validatebox"
				data-options="required:true,missingMessage:'客户电话为必填项'">&nbsp;<a
				id="saleitemadd" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok',plain:true" onclick="check()"
				style="border: 2px #999999 solid"><lable style="font-size:20px">查找</lable></a>

			&nbsp;&nbsp;<a id="hangpay" class="easyui-linkbutton" onclick="hangpay()"
				data-options="iconCls:'icon-ok',plain:true"
				style="border: 2px #999999 solid"><label style="font-size: 20px">支付完成</label></a>&nbsp;&nbsp;&nbsp;&nbsp;<label style="color:red;font-size:23px">${successmessage}</label>

			<br>
		</div>
	</div>
	<div style="width: 100%; height: 90%; padding: 0px; overflow: hidden">
		<table id="grid"></table>
	</div>


</body>
</html>