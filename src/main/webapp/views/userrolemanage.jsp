<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    
    
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>easyui学习笔记</title>
        <link id="easyuiTheme" rel="stylesheet" type="text/css" href="easyui-1.4.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="easyui-1.4.2/themes/icon.css"/>
        <script type="text/javascript" src="easyui-1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="easyui-1.4.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="easyui-1.4.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
         <script type="text/javascript" charset="utf-8">
         /* jquery入口*/
         $(function() {
             loadgrid(); //加载datagrid
         });
         /* 加载datagrid列表*/
             function loadgrid(){
            	 

            	 var x = document.getElementById("rolenamesearch").value;
             	
                  //document.getElementById("form2").submit();
                 
                 $('#grid').datagrid({
                 title : '产品',
                 url : "userrolegrid.action?rolenamesearch="+x,
                 loadMsg : '正在加载…',  //当从远程站点载入数据时，显示的一条快捷信息。
                 fit : true,  //窗口自适应
                 nowrap: false, //设置为true，当数据长度超出列宽时将会自动截取
                 fitColumns : true, // 自动适应列宽
                 rownumbers:"true",
                 singleSelect : true, // 每次只选中一行
                 sortName : 'rolename', //默认排序字段
                 sortOrder : 'asc', // 升序asc/降序desc
                 striped : true,  // 隔行变色  
                 pagination : true,  // 在底部显示分页工具栏
                 pageNumber : 1, //初始化页码 
                 pageSize : 3,  // 指定每页的大小，服务器要加上page属性和total属性
                 pageList : [ 3, 20, 30, 50 ], // 可以设置每页记录条数的列表，服务器要加上rows属性
                 //rownumbers : true, // 在最前面显示行号 
                 idField : 'id', // 主键属性
                 // 冻结列,当很多咧出现滚动条时该列不会动
                 frozenColumns : [ [
                 {title : '行号', width : '100', field : 'ck', checkbox : true},                  
                 {title : '角色名', width : '100', field : 'rolename', sortable : true}, 
                 {title : '创建者', width : '100',    field : 'rolecreator',sortable : true}
                 ] ],
                 columns : [ [ 
                 {title : '报表管理权限', width : '100', field : 'reportmanage', sortable : false},
                 {title : '基础数据管理', width : '100',    field : 'basedatamanage',sortable : false},
                 {title : '退货管理权限', width : '100',    field : 'salereturn',sortable : false},
                 {title : '挂账权限', width : '100',    field : 'hangcredit',sortable : false},
                 {title : '挂账支付权限', width : '100',    field : 'hangpay',sortable : false},
                 {title : '用户权限管理', width : '100',    field : 'permissionmanage',sortable : false},
                 {title : '销售', width : '100',    field : 'sale',sortable : false}
                
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
             openDialog('添加权限集合(角色)'); // 显示添加数据dialog窗口
             $("#form").form('clear'); // 清空form的数据
             url = 'userroleadd.action'; //后台添加数据action
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
             openChangeDialog('角色修改',editid); // 显示更新数据dialog窗口
             $("#form").form('load', row); // 加载选择行数据
             
         }
         /* 保存数据*/
         function save(){
             $('#form').form('submit',{
                 url: "userroleadd.action",  //提交地址
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
         /*提交修改的数据*/
         function change(editid){
             $('#form').form('submit',{
                 url: "userroleedit.action?id="+editid,  //提交地址
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
                         $.post('userroleremove.action', { id: row.id }, function (result) { 
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
//         	 $('#cc').combobox({    
//      		    url:'user_userrolegrid.action',    
//      		    valueField:'rolename',    
//      		    textField:'rolename'   
//      		});  
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
		
		<form id="form2"  method="post"><span>角色名称:</span><input id="rolenamesearch" name="rolenamesearch" type="text" size="20" required="required"  style="line-height:20px;border:1px solid #ccc ">	
			<a href="#" class="easyui-linkbutton" onclick="doSearch()" data-options=" iconCls:'icon-search',plain:true"><b>搜一搜</b></a>
		</form>	
		
	    </div>
    <div style="width:100%;height:90%;padding:0px;overflow:hidden">
        <table id="grid"></table>
    </div>
    <div id="dialog" data-options="closed:true" class="easyui-dialog" title="产品管理" style="width:400px;height:400px;text-align:center" >
              <form id="form" method="post">
              <br>
                     <div>
                        <label>角色名:&nbsp;&nbsp;</label>
                        <input name="rolename"  class="easyui-validatebox" data-options="required:true,missingMessage:'角色名为必填项'"  />
                 </div>
                 <br>                 
                 <div>
                 <label>报表权限:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                       <select name="reportmanage" class="easyui-combobox" style="width:60px;" data-options="panelHeight:'auto',editable:false,required:true,missingMessage:'必填项，不能为空'">   
                           <option value="否" selected>否</option>   
                        <option value="是">是</option> 
                       </select>  
                 </div>
                 <br>
                 <div>
                 <label>销售权限:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                       <select name="sale" class="easyui-combobox" style="width:60px;" data-options="panelHeight:'auto', editable:false,required:true,missingMessage:'必填项，不能为空'">   
                           <option value="否" selected>否</option>   
                        <option value="是">是</option> 
                       </select>  
                 </div>
                 <br>
                 <div>
                 <label>基础数据权限:&nbsp;&nbsp;</label>
                       <select name="basedatamanage" class="easyui-combobox" style="width:60px;" data-options="panelHeight:'auto', editable:false,required:true,missingMessage:'必填项，不能为空'">   
                           <option value="否" selected>否</option>   
                        <option value="是">是</option> 
                       </select>  
                 </div>
                 <br>
                 <div>
                 <label>退货权限:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                       <select name="salereturn" class="easyui-combobox" style="width:60px;" data-options="panelHeight:'auto', editable:false,required:true,missingMessage:'必填项，不能为空'">   
                           <option value="否" selected>否</option>   
                        <option value="是">是</option> 
                       </select>  
                 </div>
                 <br>
                 <div>
                 <label>挂账权限:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                       <select name="hangcredit" class="easyui-combobox" style="width:60px;" data-options="panelHeight:'auto', editable:false,required:true,missingMessage:'必填项，不能为空'">   
                           <option value="否" selected>否</option>   
                        <option value="是">是</option> 
                       </select>  
                 </div>
                 <br>
                 <div>
                 <label>挂账支付权限:&nbsp;&nbsp;</label>
                       <select name="hangpay" class="easyui-combobox" style="width:60px;" data-options="panelHeight:'auto', editable:false,required:true,missingMessage:'必填项，不能为空'">   
                           <option value="否" selected>否</option>   
                        <option value="是">是</option> 
                       </select>  
                 </div>
                 <br>
                 <div>
                 <label>权限管理权限:&nbsp;&nbsp;</label>
                       <select name="permissionmanage" class="easyui-combobox" style="width:60px;" data-options="panelHeight:'auto', editable:false,required:true,missingMessage:'必填项，不能为空'">   
                           <option value="否" selected>否</option>   
                        <option value="是">是</option> 
                       </select>  
                 </div>
                 <br>             
                    
                    
                                          
              </form>
       </div>
    </body>
</html>