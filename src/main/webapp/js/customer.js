/* 显示Dialog*/
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
        /* 关闭Dialog*/
        function closeDialog() {  
            $("#form").form('clear'); // 清空form的数据
            $("#dialog").dialog('close');// 关闭dialog
        }
        /* 添加数据*/
        function add() {
            openDialog('添加客户'); // 显示添加数据dialog窗口
            $("#form").form('clear'); // 清空form的数据
            url = 'customer!add.action'; //后台添加数据action
        }
        /* 修改数据*/
        function edit() {
            var row = $('#grid').datagrid('getSelected'); //// 得到选中的一行数据
            //如果没有选中记录
            if(row == null){
                $.messager.alert("提示", "请选择一条记录",'info');
                return;
            }
            openDialog('修改客户'); // 显示更新数据dialog窗口
            $("#form").form('load', row); // 加载选择行数据
            url = 'customer!edit.action?id='+row.id; //后台更新数据action
        }
        /* 保存数据*/
        function save(){
            $('#form').form('submit',{
                url: url,  //提交地址
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
                        $.post('customer!remove.action', { id: row.id }, function (result) { 
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