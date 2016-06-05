<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%
       
    /*  String totalmoney=request.getParameter("totalmoney");
    out.println("到底有没有刷新1"+totalmoney);
    if(totalmoney==null){
    	totalmoney="0.0";
    }    */
    /* Map map1 = (Map)ActionContext.getContext().get("request");   
    String successmessage=(String)map1.get("successmessage");
    //out.println("到底有没有刷新1"+totalmoney);
    if(successmessage==null){
     	successmessage="";
     }
       
    String permissionmessage=(String)map1.get("permissionmessage");
    //out.println("到底有没有刷新1"+totalmoney);
    if(permissionmessage==null){
    	permissionmessage="";
     } */
   
  
   
%>
<html>
<head>
<meta charset="UTF-8">
<title>销售</title>
<jsp:include page="insertrely.jsp"></jsp:include>
<%-- <script type="text/javascript" charset="utf-8">
var successmessage1 = <%=successmessage%>;

	window.alert(<%=successmessage%>);

</script> --%>
<script type="text/javascript" charset="utf-8">
         /* jquery入口*/
          $(function() {
             loadgrid(); //加载datagrid            
         }); 
         /* 加载datagrid列表*/
             function loadgrid(){             	                  
                 $('#grid').datagrid({
                 title : '销售',
                 url : "http://localhost:8080/allright-git/saleitem",
                 loadMsg : '正在加载…',  //当从远程站点载入数据时，显示的一条快捷信息。
                 fit : true,  //窗口自适应
                 nowrap: false, //设置为true，当数据长度超出列宽时将会自动截取
                 fitColumns : true, // 自动适应列宽
                 rownumbers:"true",
                 singleSelect : true, // 每次只选中一行
                 sortName : 'productno', //默认排序字段
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
                 {title : '行号', width : '100', field : 'ck', checkbox : true},                  
                 {title : '商品编码', width : '100', field : 'productno', sortable : true}, 
                 {title : '商品名称', width : '100',    field : 'productname',sortable : true}
                 ] ],
                 columns : [ [ 
                 {title : '单价', width : '100', field : 'price', sortable : false}, 
                 {title : '折扣', width : '200',    field : 'discount', sortable : false},
                 
                 {title : '购买数量', width : '200',    field : 'quantity', sortable : false}  ,
                 {title : '总计售价', width : '200',    field : 'saleprice', sortable : false}
                 ] ],       
                 // 工具栏按钮
                 toolbar:"#tb",
                 
             });
         }
         </script>
<script type="text/javascript" charset="utf-8">
         
         /* 删除数据*/
//          function remove(){
        	 
//              var row = $('#grid').datagrid('getSelected');
//              //如果没有选中记录
//              if(row == null){
//                  $.messager.alert("提示", "请选择一条记录",'info');
              
//              }            
//                          //提交到后台的action
//              $.post('saleitemremove.action', { id: row.id });                 
//              row=null;
              
//          }
         /* 显示挂账Dialog*/
         function openDialog(title){
             $("#dialog").dialog({
                 resizable: false,
                 modal: true,
                 buttons: [{ //设置下方按钮数组
                     text: '挂账',
                     iconCls: 'icon-save',
                     handler: function () {                    	
                        hang();
                     }
                 }, {
                     text: '取消',
                     iconCls: 'icon-cancel',
                     handler: function () {
                         closeDialog();
                     }
                 }]
             });
             $("#dialog").dialog('setTitle', title);
             $("#dialog").dialog('open');
         }
         function onhang(){
        	 //获取数据表格中有没有数据
        	 var length1 = $("#grid").datagrid('getRows').length;        	
        	 if(length1<=0){
        		 $.messager.alert("提示", "请输入商品！",'info');
        	 }else{
        	 openDialog("挂账必填信息");        	
             $("#form").form('clear'); // 清空form的数据
             url = 'http://localhost:8080/allright-git/hangcredit'; //后台添加数据action    
        	 }
         }
         
         /* 挂账*/
         function hang(){
             $('#form').form('submit',{
                 url: "http://localhost:8080/allright-git/hangcredit",  //提交地址
                 onSubmit: function(){
                     return $(this).form('validate'); //前台字段格式校验
                 },
                 success: function(result){
                     var result = eval('('+result+')');
                     if (result.success){
                         closeDialog();// 调用closeDialog;    
                        
                         $.messager.show({    //显示正确信息
                             title: '提示',
                             msg: result.msg
                         });
                         window.setTimeout("go()",1000);//1秒后执行函数go 
                     } else {              
                         $.messager.show({   //显示错误信息
                             title: '错误',
                             msg: result.msg
                         });
                         window.setTimeout("go()",1000);//1秒后执行函数go 
                     }
                 }
             });
             
         }
         function go( ) {//定义函数 
        	 window.location.href="sale.jsp";//页面跳转 
        	 } 
        	 
         /* 关闭Dialog*/
         function closeDialog() {  
             $("#form").form('clear'); // 清空form的数据
             $("#dialog").dialog('close');// 关闭dialog
         }
         /* 刷新grid*/
         function reload(){           	
             $('#grid').datagrid('reload');
         }
         /*确认购买支付*/
         function doSale(){
        	 //alert("dslfjdfkdls");
        	 var length1 = $("#grid").datagrid('getRows').length;        	
        	 if(length1<=0){
        		 $.messager.alert("提示", "请输入商品！",'info');
        	 }else{
        	 var customerno=document.getElementById("customerno").value;
        	 var h="http://localhost:8080/allright-git/saleconfrim?customerno="+customerno+"&paystate=1";
        	 document.getElementById("sale").href=h; 
        	 }
         }
         /*删除选中的记录，在用$.post的时候出现了点问题，删除之后总金额会不更新，这个问题已经解决*/
         function delete1(){
        	 var row = $('#grid').datagrid('getSelected');
             //如果没有选中记录
             if(row == null){
                 $.messager.alert("提示", "请选择一条记录",'info');
              
             }  else{          
            //提交到后台的action
                      
             var id=row.id;
        	 
        	 var h="http://localhost:8080/allright-git/saleitemremove?id="+id;
        	 document.getElementById("delete1").href=h;
             }

         }
         /*添加的记录*/
         function saleitemadd(){
        	// alert("进入saleitemadd");
        	 var discount=document.getElementById("discount").value;
        	 var productno=document.getElementById("productno").value;
        	 var quantity=document.getElementById("quantity").value;
        	 var h="http://localhost:8080/allright-git/saleitemadd?discount="+discount+"&productno="+productno+"&quantity="+quantity;
        	 document.getElementById("saleitemadd").href=h;
        	
         }
         /*找零*/
         function retail(){        	 
        	 var pay=document.getElementById("pay").value;
        	 
        	 var total=${totalmoney};
        	 var change=pay-total;  
        	 var change1=change.toFixed(2);
        	 document.getElementById("change").value=change1;
         }
         
         
		</script>
</head>
<body>
	
	<div style="padding: 3px; background-color: #ccddaa; font-size: 20;">
		<div style="padding: 15px; font-size: 45;">
			金额总计:&nbsp; ${totalmoney}&nbsp;&nbsp;<label style="font-size: 35;">支付:</label><input
				id="pay" type="text"
				style="border: none; background-color: #ccddaa; width: 160px; height: 60px; font-size: 50px" onkeyup="if(isNaN(value))execCommand('undo')">
			<button onclick="retail()"
				style="border: 3px solid #999999; background-color: #ccddaa; width: 110px; height: 60px; font-size: 38px">找零:</button>
			<input id="change" type="text"
				style="border: none; background-color: #ccddaa; width: 160px; height: 60px; font-size: 50px">
		</div>
		<div>
			<label>客户编号</label>&nbsp;<input id="customerno" name="customerno"
				type="text" value="" size=11 height=20>&nbsp; <a id="sale"
				class="easyui-linkbutton" onclick="doSale()"
				data-options="iconCls:'icon-ok',plain:true"
				style="border: 2px #999999 solid"><label style="font-size: 23">支付下单</label></a>
				<a id="hang"
				class="easyui-linkbutton" onclick="onhang()"
				data-options="iconCls:'icon-ok',plain:true"
				style="border: 2px #999999 solid"><label style="font-size: 23">挂账</label></a>&nbsp;&nbsp;&nbsp;&nbsp;<label style="color:red;font-size:23px"><%-- ${successmessage } --%></label>
		</div>
	</div>
	<div style="width: 100%; height: 90%; padding: 0px; overflow: hidden">
		<table id="grid"></table>
	</div>

	<div id="tb">
		<br> &nbsp;&nbsp;<label style="font-size: 23px">折扣(%)</label>&nbsp;&nbsp;<input
			id="discount" name="discount" type="text" value="100" size=6
			height=20> &nbsp;<label style="font-size: 23px">商品编码</label>&nbsp;&nbsp;<input
			id="productno" name="productno" type="text" value="" size=11
			height=20> &nbsp;&nbsp;
			<label style="font-size: 23px">重量(斤)</label>&nbsp;&nbsp;<input
			id="quantity" name="quantity" type="text" value="0" size=6
			height=20> &nbsp;<a id="saleitemadd"
			class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true"
			onclick="saleitemadd()" style="border: 2px #999999 solid"><lable
				style="font-size:23px">确定</lable></a> <label style="color:red;font-size:23px"></label><br> <a id="delete1"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="delete1()"><h2>删除</h2></a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" onclick="reload()"><h2>刷新</h2></a>
	</div>


	<div id="dialog" data-options="closed:true" class="easyui-dialog"
		title="挂账信息" style="width: 250px; height: 200px; text-align: center">
		<form id="form" method="post">
			
			<div>
				<label>客户姓名：</label> <input name="customername"
					class="easyui-validatebox"
					data-options="required:true,missingMessage:'客户名称为必填项'" />
			</div>
			<div>
				<label>联系电话：</label> <input name="telephone"
				class="easyui-validatebox"
					data-options="required:true,missingMessage:'客户电话为必填项'" />
			</div>
			<div>
				<label>联系地址：</label> <input name="address"
				class="easyui-validatebox"
					data-options="required:true,missingMessage:'客户地址为必填项'" />
			</div>

		</form>
	</div>





</body>
</html>