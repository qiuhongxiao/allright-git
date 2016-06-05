<%@ page language="java" import="java.util.*" <%-- import="org.pnz.post.vo.User" --%> pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// User user = (User)session.getAttribute("user");
// int id = user.getId();
// String username=user.getUsername();
// String password = user.getPassword();
// String permission = user.getPermission();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>关于自己</title>
    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="easyui-1.4.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="easyui-1.4.2/themes/icon.css"/>
        <script type="text/javascript" src="easyui-1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="easyui-1.4.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="easyui-1.4.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body>
  多多兽便利店pos机溫馨提示！！！<br><br>
 用户名:&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.username"/><br><br>
 登陆时间:&nbsp;&nbsp;&nbsp;<%=new Date() %><br><br>
销售权限:&nbsp;&nbsp;&nbsp;<s:property value="#request.sale"/><br><br>
挂账权限:&nbsp;&nbsp;&nbsp;<s:property value="#request.hangcredit"/><br><br>
挂账支付权限:&nbsp;<s:property value="#request.hangpay"/><br><br>
基础数据权限:&nbsp;<s:property value="#request.basedatamanage"/><br><br>
报表权限:&nbsp;&nbsp;&nbsp;<s:property value="#request.reportmanage"/><br><br>
权限控制:&nbsp;&nbsp;&nbsp;<s:property value="#request.permissionmanage"/><br><br>
退货权限:&nbsp;&nbsp;&nbsp;<s:property value="#request.salereturn"/><br><br>

<br><br><br><br><script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"2","bdPos":"left","bdTop":"290"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
  </body>
</html>
