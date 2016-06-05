<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <jsp:include page="insertrely.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
function success(){
	alert("success");
}


function query() {
	
	 var strid=$("#bh2").val();
		$.ajax( {
			type : "get",
			url : "http://localhost:8080/allright-git/product/query",
			data : "productid="+strid,
			dataType: 'json',
			success : function(data) {
				
				var textarea=$("#result");
				var str=data.id+"&nbsp;"+data.productid+"&nbsp;"+data.productname+"&nbsp;"
				+data.price+"&nbsp;"+data.quantity+"&nbsp;"+data.updated_at+"&nbsp;"+data.ttype.typename;
				var text=document.getElementById('result');
				text.innerHTML=str;
				console.dir(data);
	            //alert(arr.length+"success");
			},
			error : function(data) {
				var textarea=$("#result");
				alert("恭喜发财");
				//var arr=eval(msg);
				console.dir(data);
	            //alert(arr.length+"error");
			}
		});
	}

/* function del() {
	
	 var strid=$("#bh3").val();
		$.ajax( {
			type : "get",
			url : "http://localhost:8080/allright-git/product/del",
			data : "productid="+strid,
			dataType: 'json',
			success : function(data) {
				console.dir(data);
				alert("success");
	            //alert(arr.length+"success");
			},
			error : function(data) {
				
				alert("恭喜发财");
				//var arr=eval(msg);
				console.dir(data);
	            //alert(arr.length+"error");
			}
		});
	} */

</script>


<title>管理水果页面</title>
</head>
<body background="<%=request.getContextPath() %>/views/5.gif">
<form method="get"  action="/allright-git/product/create">
<input type="button" name="back" value="退出" style="font-size:14px" onclick="window.location='start.jsp'"><p>
 <div align="center">
  新增水果：（编号）<input type=text name="productid" id="Product.productid" size=16  maxlength=5>
  （名字）<input type=text name="productname" size=16  >
  （价格）<input type=text name="price" size=16  maxlength=5>
  （重量）  <input type=text id=quantity  style="font-size:16px" >
  （类型）<select name="typename">
  <option value='本地'>本地
    <option value='外省'>外省
      <option value="进口">进口
   </select>
  <p>
  <input type="submit" value="确定增加以上内容" style="font-size:20px" ><p>
  </p>
  </form>
  
  <form  method="get"  action="/allright-git/product/update">
  编辑水果：（编号）<input type=text  name="productid" size=16  maxlength=5>
  （名字）<input type=text name="productname" size=16 >
  （价格）<input type=text name="price" size=16  maxlength=5>
  （重量）  <input type=text name="quantity"  style="font-size:16px" >
  （类型）<select name="typename">
  <option value='本地'>本地
    <option value='外省'>外省
      <option value='进口'>进口
   </select>
    <p>
  <input type="submit" name="xg" value="确定修改以上内容" style="font-size:20px"><p>
  </p>
  </form>
  
  
  
  输入关键字：<input type=text id="bh2" size=22 > 
  <input type="button" id="cx" value="查询" style="font-size:20px" onclick="query()"><p>
  <textarea id="result" cols=100 rows=4 ></textarea><p>
  </p>
  
  
  <form method="get"  action="/allright-git/product/del">
   输入编号：<input type=text name="bh3" size=22  maxlength=5> 
  <input type="submit" name="sc" value="删除" style="font-size:20px" >
  </div>
  </form>
</body>
</html>