<%@ page language="java" import="java.util.*"
	 pageEncoding="utf-8"%>

<jsp:include page="insertrely.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>水果君pos系统</title>
    
   
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/treeandmenu.js"></script>
</head>
<body class="easyui-layout"  >
	 <bgsound src="url" loop="-1">
     <EMBED src="url" autostart="true" loop="true" width="80" height="20"  playbutton="true"> 
    <div id="head" data-options="region:'north'" style="height:60px;">
        <div style="height:30px;font-size:30px;background-color:pink;text-align:center;"><label style="font-size:43px;weight:53px;"><font face="华文彩云" size =6 color =blue>水果君pos系统</font></label></div>
        <div style="text-align: right;background-color:pink;">
          <label style="font-size:23px">${username }</label>&nbsp;<a href="logout.action" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">退出</a>
            <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#theme',iconCls:'icon-filter'">主题</a>
        </div>
        <div id="theme" style="width: 100px; display: none;">
            <div onclick="changeTheme('default');">浅蓝</div>
            <div onclick="changeTheme('gray');">灰色</div>
            <div onclick="changeTheme('black');">黑色</div>
            <div onclick="changeTheme('metro');">Metro</div>
            <div onclick="changeTheme('bootstrap');">Bootstrap</div>
        </div>
    </div>
    <div id="foot" data-options="region:'south'" style="height:30px;text-align: center;background: pink">CopyRight:Allright-git.WXJ</div>
    <div id="nav" data-options="region:'east',split:true" style="width:200px" title="导航">
            <div id="navMenu" class="easyui-accordion" data-options="fit:true,border:false">
                <div title="系统管理" data-options="iconCls:'icon-tip'" style="overflow:auto;padding:10px;">
                <ul id="tree1" class="easyui-tree" data-options="lines:true"></ul>
                </div>
                <div title="基础数据" data-options="iconCls:'icon-save',selected:true" style="padding:10px;">
                <ul id="tree" class="easyui-tree" data-options="lines:true"></ul>    
                </div>    
            </div>
    </div>
    <div id="mainPanle" data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true, border: false" >
            <div title="About" data-options="iconCls:'icon-tip'">
                <iframe src="<%=request.getContextPath() %>/res/source/about.html" style="border: 4px; width: 100%; height: 98%;"></iframe>
            </div>
        </div>    
    </div>
    <div id="tabsMenu" class="easyui-menu" style="width:150px;">
        <div data-options="name:'close'">关闭</div>
        <div class="menu-sep"></div>
        <div data-options="name:'closeall'">全部关闭</div>
        <div data-options="name:'closeother'">关闭其他</div>
    </div>
</body>
</html>