<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>product</title>
        <jsp:include page="insertrely.jsp"></jsp:include>
         <script type="text/javascript" charset="utf-8">
         /* jquery入口*/
         $(function() {
             loadgrid(); //加载datagrid
         });
         /* 加载datagrid列表*/
             function loadgrid(){
            	 

            	 var x = document.getElementById("productnamesearch").value;
             	
                  //document.getElementById("form2").submit();
                 
                 $('#grid').datagrid({
                 title : '产品',
                 url : "http://localhost:8080/allright-git/productgrid?productnamesearch="+x,
                 loadMsg : '正在加载…',  //当从远程站点载入数据时，显示的一条快捷信息。
                 fit : true,  //窗口自适应
                 nowrap: false, //设置为true，当数据长度超出列宽时将会自动截取
                 fitColumns : true, // 自动适应列宽
                 rownumbers:"true",
                 singleSelect : true, // 每次只选中一行
                 sortName : 'productno', //默认排序字段
                 sortOrder : 'asc', // 升序asc/降序desc
                 striped : true,  // 隔行变色  
                 pagination : true,  // 在底部显示分页工具栏
                 pageNumber : 1, //初始化页码 
                 pageSize : 20,  // 指定每页的大小，服务器要加上page属性和total属性
                 pageList : [ 3, 20, 30, 50 ], // 可以设置每页记录条数的列表，服务器要加上rows属性
                 //rownumbers : true, // 在最前面显示行号 
                 idField : 'id', // 主键属性
                 // 冻结列,当很多咧出现滚动条时该列不会动
                 frozenColumns : [ [
                 {title : '行号', width : '100', field : 'ck', checkbox : true},
                 {title : '序号', width : '100', field : 'id', sortable : true}, 
                 {title : '产品编号', width : '100', field : 'productno', sortable : true}, 
                 {title : '产品姓名', width : '100',    field : 'productname',sortable : true}
                 ] ],
                 columns : [ [ 
                 {title : '产品描述', width : '100', field : 'description', sortable : false}, 
                 {title : '产品价格', width : '200',    field : 'price', sortable : false},
                 {title : '产品类别编号', width : '200',    field : 'producttype_id', sortable : false},
                 {title : '产品类别名称', width : '200',    field : 'producttypename', sortable : false},
                 {title : '库存量', width : '200',    field : 'stockquantity', sortable : false}
                 ] ],       
                 // 工具栏按钮
                 toolbar: [
         "-", {id: 'add', text: '添加',    iconCls: 'icon-add', handler: function () { add()} },
         "-", {id: 'edit', text: '修改',   iconCls: 'icon-edit',   handler: function () { edit()} }, 
         "-", {id: 'remove', text: '删除',iconCls: 'icon-remove', handler: function () {remove()} }, 
         "-", {id: 'remove',  text: '刷新',iconCls: 'icon-reload', handler: function () {reload()} } 
                 ]
             });
         }
         </script>
         <script type="text/javascript"  charset="utf-8">
         /* 显示保存Dialog*/
         function openDialog(title){
             $("#dialog").dialog({
                 resizable: false,
                 modal: true,
                 buttons: [{ //设置下方按钮数组
                     text: '保存',
                     iconCls: 'icon-save',
                     handler: function () {                    	
                        save();
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
         /* 显示修改Dialog*/
         function openChangeDialog(title,editid){
             $("#dialog").dialog({
                 resizable: false,
                 modal: true,
                 buttons: [{ //设置下方按钮数组
                     text: '修改',
                     iconCls: 'icon-save',
                     handler: function () {                    	
                        change(editid);
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
         /* 关闭Dialog*/
         function closeDialog() {  
             $("#form").form('clear'); // 清空form的数据
             $("#dialog").dialog('close');// 关闭dialog
         }
         /* 添加数据*/
         function add() {
             openDialog('添加产品'); // 显示添加数据dialog窗口
             $("#form").form('clear'); // 清空form的数据
             url = 'http://localhost:8080/allright-git/productadd'; //后台添加数据action
         }
         /* 修改数据*/
         function edit() {
             var row = $('#grid').datagrid('getSelected'); //// 得到选中的一行数据
             //如果没有选中记录
             if(row == null){
                 $.messager.alert("提示", "请选择一条记录",'info');
                 return;
             }
             editid = row.id; //后台更新数据action
             openChangeDialog('修改产品',editid); // 显示更新数据dialog窗口
             $("#form").form('load', row); // 加载选择行数据
             
         }
         /* 保存数据*/
         function save(){
             $('#form').form('submit',{
                 url: "http://localhost:8080/allright-git/productadd",  //提交地址
                 onSubmit: function(){
                     return $(this).form('validate'); //前台字段格式校验
                 },
                 success: function(result){
                	 try{
                	 console.dir(result);
                     var result = eval('('+result+')');
                     if (result.success){
                         closeDialog();// 调用closeDialog;    
                         reload();// 重新加载
                         $.messager.show({    //显示正确信息
                             title: '提示',
                             msg: result.msg
                         });
                     } else {              
                         $.messager.show({   //显示错误信息
                             title: '错误',
                             msg: result.msg
                         });
                     }
                	 }catch(e){
                		 window.location.replace("error.jsp");
                	 }
                 },
                 error:function(result){
                	 window.location.replace("error.jsp");
                 }
             }); 
             
             
             /*  var productno=$("#productno").val();var productname=$("#productname").val();
             var description=$("#description").val();var price=$("#price").val();
             var producttype_id=$("#cc").val();var stockquantity=$("#stockquantity").val();
             
     		$.ajax( {
     			type : "get",
     			url : "http://localhost:8080/allright-git/productadd",
     			data : "productno="+productno+"&productname="+productname+"&description="+description+"&price="+price+"&producttype_id="+producttype_id+"&stockquantity="+stockquantity,
     			dataType: 'json',
     			success: function(result){
                    var result = eval('('+result+')');
                    if (result.success){
                        closeDialog();// 调用closeDialog;    
                        reload();// 重新加载
                        $.messager.show({    //显示正确信息
                            title: '提示',
                            msg: result.msg
                        });
                    } else {              
                        $.messager.show({   //显示错误信息
                            title: '错误',
                            msg: result.msg
                        });
                    }
                }
     		});  */
         }
         /*提交修改的数据*/
         function change(editid){
             $('#form').form('submit',{
                 url: "http://localhost:8080/allright-git/productedit?id="+editid,  //提交地址
                 onSubmit: function(){
                     return $(this).form('validate'); //前台字段格式校验
                 },
                 success: function(result){
                     var result = eval('('+result+')');
                     if (result.success){
                         closeDialog();// 调用closeDialog;    
                         reload();// 重新加载
                         $.messager.show({    //显示正确信息
                             title: '提示',
                             msg: result.msg
                         });
                     } else {              
                         $.messager.show({   //显示错误信息
                             title: '错误',
                             msg: result.msg
                         });
                     }
                 }
             });
         }
         /* 删除数据*/
         function remove(){
             var row = $('#grid').datagrid('getSelected');
             //如果没有选中记录
             if(row == null){
                 $.messager.alert("提示", "请选择一条记录",'info');
                 return;
             }
             $.messager.confirm('确认', '确定要删除吗？', function (r) {
                     if (r) {
                         //提交到后台的action
                         $.post('http://localhost:8080/allright-git/productremove', { id: row.id }, function (result) { 
                             if (result.success) {
                                 reload();
                                 $.messager.show({   //显示正确信息
                                     title: '提示',
                                     msg: result.msg
                                 });                                 
                             } else {
                                 $.messager.show({   //显示错误信息
                                     title: '错误',
                                     msg: result.msg
                                 });
                             }
                         }, 'json');
                     }
                 });
              
         }
         /* 刷新grid*/
         function reload(){
        	 $('#cc').combobox({    
     		    url:'http://localhost:8080/allright-git/product_producttypegrid',    
     		    valueField:'id',    
     		    textField:'producttypename'   
     		});  
             $('#grid').datagrid('reload');
         }
         //根据产品名查询
         function doSearch(){        	 
        	 loadgrid();
         }   	
		</script>
    </head>
    <body>
    <div id="tb" style="padding:3px;background-color:#ccddaa;" >
		
		<form id="form2"  method="post"><span>商品名称:</span><input id="productnamesearch" name="productnamesearch" type="text" size="20" required="required"  style="line-height:20px;border:1px solid #ccc ">	
			<a href="#" class="easyui-linkbutton" onclick="doSearch()" data-options=" iconCls:'icon-search',plain:true"><b>搜一搜</b></a>
		</form>	
		
	    </div>
    <div style="width:100%;height:90%;padding:0px;overflow:hidden" >
        <table id="grid" background="<%=request.getContextPath() %>/views/5.gif"></table>
    </div>
    <div id="dialog" data-options="closed:true" class="easyui-dialog" title="产品管理" style="width:300px;height:300px;text-align:center" >
              <form id="form" method="get">
              <br>
                     <div>
                        <label>产品编号</label>
                        <input id="productno" name="productno"  class="easyui-validatebox" data-options="required:true,missingMessage:'产品编号描述为必填项'"  />
                 </div>
                 <br>
                 <div>
                        <label>产品名称</label>
                        <input id="productname" name="productname"  class="easyui-validatebox" data-options="required:true,missingMessage:'产品名称描述为必填项，不能为空'"  />
                 </div>
                 <br>
                 <div>
                        <label>产品描述</label>
                        <input id="description" name="description" data-options="required:false" />
                 </div>
                 <br>
                 <div>
                        <label>产品价格</label>
                        <input id="price" name="price"  class="easyui-validatebox" data-options="required:true,missingMessage:'产品价格描述为必填项，数值类型'" onkeyup="if(isNaN(value))execCommand('undo')" onpaste="return false"  onafterpaste="if(isNaN(value))execCommand('undo')"/>
                 </div>   
                 <br>          
                 <div>
                        <label>产品类别名称</label>
                        <input id="cc" class="easyui-combobox" name="producttype_id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'producttypename',url:'http://localhost:8080/allright-git/product_producttypegrid',required:true,missingMessage:'产品类别为必填项，数值类型'" /> 
                 </div>
                 <br>
                 <div>
                        <label>库存量</label>
                        <!-- <input name="stockquantity"  class="easyui-validatebox" data-options="required:true,missingMessage:'产品库存描述为必填项，整数类型'" onkeyup="this.value=this.value.replace(/\D/g,'')" onpaste="return false"  onafterpaste="this.value=this.value.replace(/\D/g,'')"/> -->
                        
                         <input id="stockquantity" name="stockquantity"  class="easyui-validatebox" data-options="required:true,missingMessage:'产品库存描述为必填项，整数类型'" onkeyup="if(isNaN(value))execCommand('undo')" onpaste="return false"  onafterpaste="if(isNaN(value))execCommand('undo')"/>
                 </div>   
                 <br>                    
              </form>
       </div>
    </body>
</html>