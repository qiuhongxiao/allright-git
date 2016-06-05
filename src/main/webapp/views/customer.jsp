<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Map" import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
            	 var x = document.getElementById("customernamesearch").value;
            	// escape(x);
                // alert(x); 
                 //document.getElementById("form2").submit();
                 $('#grid').datagrid({
                 title : '客户',
                 url : "customergrid.action?customernamesearch="+x,
                 loadMsg : '正在加载…',  //当从远程站点载入数据时，显示的一条快捷信息。
                 fit : true,  //窗口自适应
                 rownumbers:"true",
                 nowrap: false, //设置为true，当数据长度超出列宽时将会自动截取
                 fitColumns : true, // 自动适应列宽
                 singleSelect : true, // 每次只选中一行
                 sortName : 'customerno', //默认排序字段
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
                 {title : '序号', width : '100', field : 'id', sortable : true}, 
                 {title : '客户编号', width : '400', field : 'customerno', sortable : true}, 
                 {title : '客户姓名', width : '100',    field : 'customername',sortable : true}
                 ] ],
                 columns : [ [ 
                 {title : '联系电话', width : '100', field : 'telephone', sortable : false, editor:"{type:'validatebox',options:{validType:'email',options:{required:true}}"}, 
                 {title : '联系地址', width : '200',    field : 'address', sortable : false},
                 {title : '客户级别', width : '200',    field : 'customerclass', sortable : false}
                 ] ],       
                 // 工具栏按钮
                 toolbar: [
         "-", {id: 'add', text: '添加',    iconCls: 'icon-add', handler: function () { add()} },
         "-", {id: 'edit', text: '修改',   iconCls: 'icon-edit',   handler: function () { edit()} }, 
         "-", {id: 'remove', text: '删除',iconCls: 'icon-remove', handler: function () {remove()} }, 
         "-", {id: 'reload',  text: '刷新',iconCls: 'icon-reload', handler: function () {reload()} } 
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
             openDialog('添加客户'); // 显示添加数据dialog窗口
             $("#form").form('clear'); // 清空form的数据
             url = 'customeradd.action'; //后台添加数据action
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
             openChangeDialog('修改客户',editid); // 显示更新数据dialog窗口
             $("#form").form('load', row); // 加载选择行数据
             
         }
         /* 保存数据*/
         function save(){
             $('#form').form('submit',{
                 url: "customeradd.action",  //提交地址
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
                 url: "customeredit.action?id="+editid,  //提交地址
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
                         $.post('customerremove.action', { id: row.id }, function (result) { 
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
             $('#grid').datagrid('reload');
         }
         //根据用户名查询
         function doSearch(){        	 
        	 loadgrid();
         }
         
         </script>
    </head>
    <body>    
     <div id="tb" style="padding:3px;background-color:#ccddaa;" >
		
		<form id="form2"  method="post"><span>客户名称:</span><input id="customernamesearch" name="customernamesearch" type="text" size="20" required="required" name="customernamesearch" style="line-height:20px;border:1px solid #ccc ">	
			<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()" data-options=" iconCls:'icon-search',plain:true"><b>搜一搜</b></a>
		</form>	
		&nbsp;&nbsp;&nbsp;<label style="color:red;font-size:23px"><%-- <%=permissionmessage%> --%></label>
	    </div>
    <div style="width:100%;height:90%;padding:0px;overflow:hidden">
        <table id="grid" class="eaayui-datagrid" toolbar="#tb"></table>
    </div>
    <div id="dialog" data-options="closed:true" class="easyui-dialog" title="客户管理" style="width:250px;height:200px;text-align:center" >
              <form id="form" method="post">
                     <div>
                        <label>客户编号</label>
                        <input name="customerno"  disabled="disabled" value="客户编号由系统自动生成" class="easyui-validatebox" data-options="required:true,missingMessage:'客户编号为必填项'"  />
                 </div>
                 <div>
                        <label>客户名称</label>
                        <input name="customername"  class="easyui-validatebox" data-options="required:true,missingMessage:'客户名称描述为必填项'"  />
                 </div>
                 <div>
                        <label>联系电话</label>
                        <input name="telephone" data-options="required:false" editor="text"/>
                 </div>
                 <div>
                        <label>联系地址</label>
                        <input name="address" data-options="required:false" editor="{type:'validatebox',options:{validType:'email'}"/>
                 </div>
                 <div>
                        <label>客户级别</label>
                        <input name="customerclass"  class="easyui-validatebox" data-options="required:true,missingMessage:'客户级别为必填项'"  />
                 </div>
                                 
              </form>
       </div>
      
    </body>
</html>