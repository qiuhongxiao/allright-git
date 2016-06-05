<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="insertrely.jsp"></jsp:include>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	  
	  $(function() {
	      $("#addOneRow").click(function() {
	          var tempTr = $("tr:first").next().clone(true);
	          $("tr:last").after(tempTr);
	          $("tr:last > td > #id").val("");//新添加行productname为空 
	          $("tr:last > td > #quantity").val("");//新添加行quantity为空 
	      });
	      $(".delOneRow").click(function() {
	          alert("tr="+$("tr").length)
	          if ($("tr").length <3) {
	              alert("至少保留一行!");
	          } else {
	              if (confirm("确认删除?")) {
	                  $(this).parent().parent().remove();
	              }
	          }
	      });
	  });

	});   
	
	
	
function tijiao() {
	  /* var ids=$("input[name^='id']");
	  var strid="";
	 for(var i=0;i<ids.length;i++){
		 strid=strid+ids[i].value+",";
	 }   */
	 var username=$("#username").val();
	 var userid=$("#userid").val();
	  var ids=new Array();
	 $(".bh").each(function(index){
		 ids[index]=$(this).val();
	 });
	 console.log(ids);
	 var strid="";
	 for(var i=0;i<ids.length;i++){
		 strid=strid+ids[i]+",";
	 }  
		$.ajax( {
			type : "get",
			url : "http://localhost:8080/allright-git/order/salesitem",
			data : "id="+strid+"&username="+username+"&userid="+userid,
			dataType: 'json',
			success : function(data) {
				//alert("print datalength:"+data.length);
				//alert("print datasize:"+data.size);
				var table=document.createElement("table");
				table.setAttribute("border","1");
				table.setAttribute("background","red");
				table.setAttribute("width","50%");
				table.setAttribute("height","80%");
				table.setAttribute("cellspacing","10");
				table.setAttribute("cellpadding","10");
				table.setAttribute("bordercolor","blue");
				var length=0;
				for(var i in data){
					length++;
				}
				for(var i=1;i<length;i++){
					var tr=document.createElement("tr");
					var tdid=document.createElement("td");
					//alert("success:"+data[i]);
					tdid.innerHTML=data[i].id;
					
					var tdproductid=document.createElement("td");
					tdproductid.innerHTML=data[i].productid;
					
					var tdproductname=document.createElement("td");
					tdproductname.innerHTML=data[i].productname;
					
					var tdprice=document.createElement("td");
					tdprice.innerHTML=data[i].price;
					
					var tdquantity=document.createElement("td");
					tdquantity.innerHTML=data[i].quantity;
					
					var tdupdated_at=document.createElement("td");
					tdupdated_at.innerHTML=data[i].updated_at;
					
					tr.appendChild(tdid);
					tr.appendChild(tdproductid);
					tr.appendChild(tdproductname);
					tr.appendChild(tdprice);
					tr.appendChild(tdquantity);
					tr.appendChild(tdupdated_at);
					table.appendChild(tr);
				}
				document.getElementById("salesitemtable").appendChild(table);
				
				var toolbar="（应付）<input type=text  id='total' size=16  maxlength=5>"+
				  "（实付）<input type=text id='pay' size=16 >"+
				  "（应找）<input type=text id='change' size=16  maxlength=5>"+
				  "<input type='button' id='changebar' value='找零' style='font-size:20px' onclick='change()'>";
				var money=document.getElementById("money");
				money.innerHTML=toolbar;
				document.getElementById("total").value=data[length];
				
				//alert("success:"+data.1);
				//var arr=eval(msg);
				console.dir(data);
	            //alert(arr.length+"success");
			},
			error : function(data) {
				alert("fail");
				//var arr=eval(msg);
				console.dir(data);
	            //alert(arr.length+"error");
			}
		});
	}
	
	
function change(){
	var pay=$("#pay").val();
	 var total=$("#total").val();
	 var change=pay-total;
	 document.getElementById("change").value=change;
	 
	 $.ajax( {
			type : "get",
			url : "http://localhost:8080/allright-git/order/updateorderstate",
			data : "total="+total,
			dataType: 'json',
			
		});
}

function delorder(){
	var id=$("#bh3").val();
	$.ajax( {
		type : "get",
		url : "http://localhost:8080/allright-git/order/delorder",
		data : "id="+id,
		dataType: 'json',
		
	});
}
function success(){
	alert("success!");
}
	/*  $(function() {
		 var fieldCount = 1;
		 $("#addFieldButton").click(function() {
		  fieldCount++;
		  if(fieldCount <= 5)
		  {
		  var fieldID = "recipient_email_" + fieldCount;
		  $("#additionalEmails").append("<label for='"+fieldID+"'>Recipient Email "+fieldCount+": </label>"+ 
		      "<input type='text' name='"+fieldID+"' " +
		      "id='"+fieldID+"' size='30'><br />" );
		  }
		  else
		  {
		  alert("Maximum email fields reached.");
		  }
		 });
		 });
}); */
</script>
</head>

<body background="<%=request.getContextPath() %>/views/5.gif">
  <p><p><p> 
  <p><p><p> 
<input type="button" name="back" value="退出" style="font-size:14px" onclick="window.location='start.jsp'"><p>
    <div align="center">  
   
  <table border="1" align=center width=50%  height=80% cellspacing =10 cellpadding=10 bordercolor=blue>
         <tr>
         	<td>用户姓名:</td>
            <td><input type="text" id="username" name="username"  style= "text-align: center; font-size: 20px "/></td>
            <td>证件号:</td>
            <td><input type="text" id="userid" name="userid" style= "text-align: center; font-size: 20px "/></td>
               
        </tr>
         
        <tr id="create">
            <td>产品id:</td>
            <td><input type="text" id="id" name="id" class="bh" style= "text-align: center; font-size: 20px "/></td>
            <td>产品重量:</td>
            <td><input type="text" id="quantity" name="quantity" style= "text-align: center; font-size: 20px "/></td>
            <td><input type="button" class="delOneRow" value="删除" style= "text-align: center; font-size: 20px "/></td>
        </tr>
    </table>
    <p><p><p>
    <input type="button"  id="addOneRow" value="添加一行" style= "text-align: center; font-size: 20px "/>
    <input type="button"  id="tijiao" value="提交" style= "text-align: center; font-size: 20px " onclick="tijiao()"/>
     </div>

    
   <div id="salesitemtable" align="center"></div>
   <div id="money" align="center"></div>
   
   <div align="center">
   
   输入订单id：<input type=text id="bh3" size=22  maxlength=5> 
  <input type="button" id="sc" value="删除" style="font-size:20px" onclick="delorder()">
  </form>
  </div>
  
</body>

</html> 