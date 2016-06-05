<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <jsp:include page="insertrely.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎登陆本系统！</title>
<script type="text/javascript">
function login(){
	

var username=$("#username").val();
var password=$("#password").val();

$.ajax( {
	type : "post",
	url : "http://localhost:8080/allright-git/login",
	data : "username="+username+"&password="+password,
	dataType: 'json',
	  success : function(data) {
		console.dir(data);
		if(data==true){
			window.location.href='/allright-git/views/index.jsp';
		}else alert("login fail!");
	}, 
	 error : function(data) {
		console.dir(data);
		
		alert("error!");
		
	}  
});



}

</script>

</head>
<body bgcolor=#FFFFFF background="<%=request.getContextPath() %>/views/1.gif">
  <center> 
  <font face="华文彩云" size =9 color =blue>欢迎登陆本系统！</font>  
    <br><p>
    <form action="/allright-git/login" method="post"><font size=4> 
   帐户: <input type="text" name="username" size=20><br> <p>
   密 码: <input type="password" name="password" size=21><p> 
  <br>
   <input type="submit" value=" 确 定 " name="submit" style="font-size:20px" > 
   <input type="reset" value=" 清 除 " name="reset" style="font-size:20px"></font>
</form>
 </center>
</body>
</html>
