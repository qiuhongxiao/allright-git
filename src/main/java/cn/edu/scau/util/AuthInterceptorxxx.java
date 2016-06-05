package cn.edu.scau.util;
/*package cn.edu.scau.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.edu.scau.model.Customermodel;
import cn.edu.scau.service.UserService;
import cn.edu.scau.service.UserroleService;
import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.User;
import cn.edu.scau.vo.Userrole;

public class AuthInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	@Resource(name = "userroleService")
	private UserroleService userroleService;

	private void t(String s) {
		System.out.println(s);
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// 获取请求的action名称  
		Map request=(Map)ActionContext.getContext().getSession();
        String actionName = invocation.getInvocationContext().getName();  
        System.out.println("action的名称："+actionName);
//        // 获取action后附带参数  
//        Map parameters = aInvocation.getInvocationContext().getParameters(); 
		String result = "";
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		User user = (User) session.get("user");
		t("begin");
		if (user != null) {
			Userrole userrole = userroleService.getRoleFromUserId(user.getUserrole_rolename());
			*//**
			 * 基础数据权限控制开始
			 *//*
			//客户查看
			if(actionName.equals("customergrid")&&userrole.getBasedatamanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("customergrid")&&userrole.getBasedatamanage().equals("否")){
//				Customermodel customermodel = new Customermodel();
//				customermodel.setCustomername("抱歉！你没有基础数据权限...");
//				List<Customermodel> list = new ArrayList<Customermodel>();
//				list.add(customermodel);
//				writeJson(1,list);
				System.out.println("无此权限！！！");
				//invocation.getInvocationContext()
				return "error";
			}
			//客户添加
			if(actionName.equals("customeradd")&&userrole.getBasedatamanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("customeradd")&&userrole.getBasedatamanage().equals("否")){
				returnmessage("抱歉！你没有基础数据权限...");	
				result = "error";
			}
			//客户修改和删除没有必要控制了，因为记录无法被选择
			//商品查看
			if(actionName.equals("productgrid")&&userrole.getBasedatamanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("productgrid")&&userrole.getBasedatamanage().equals("否")){
				returnmessage("抱歉！你没有基础数据权限...");	
				result = "error";
			}
			//商品添加
			if(actionName.equals("productadd")&&userrole.getBasedatamanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("productadd")&&userrole.getBasedatamanage().equals("否")){
				returnmessage("抱歉！你没有基础数据权限...");
				result = "error";
			}
			//商品修改和删除没有必要控制了，因为记录无法被选择
			//商品类型查看
			if(actionName.equals("producttypegrid")&&userrole.getBasedatamanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("producttypegrid")&&userrole.getBasedatamanage().equals("否")){
				returnmessage("抱歉！你没有基础数据权限...");	
				result = "error";
			}
			//商品类型添加
			if(actionName.equals("producttypeadd")&&userrole.getBasedatamanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("producttypeadd")&&userrole.getBasedatamanage().equals("否")){
				returnmessage("抱歉！你没有基础数据权限...");
				result = "error";
			}
			//商品类型修改和删除没有必要控制了，因为记录无法被选择
			*//**
			 * 基础数据权限控制结束
			 *//*
			*//**
			 * 退货权限控制开始
			 *//*
			if(actionName.equals("canreturnitem")&&userrole.getSalereturn().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("canreturnitem")&&userrole.getSalereturn().equals("否")){
				returnmessage("抱歉！你没有退货权限...");		
				result = "error";
			}
			if(actionName.equals("salereturnmoney")&&userrole.getSalereturn().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("salereturnmoney")&&userrole.getSalereturn().equals("否")){
				returnmessage("抱歉！你没有退货权限...");	
				result = "error";
			}
			if(actionName.equals("salereturnconfirm")&&userrole.getSalereturn().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("salereturnconfirm")&&userrole.getSalereturn().equals("否")){
				returnmessage("抱歉！你没有退货权限...");	
				result = "error";
			}
			*//**
			 * 退货权限控制结束
			 *//*
			*//**
			 * 挂账权限控制开始
			 *//*
			
			if(actionName.equals("hangcredit")&&userrole.getHangcredit().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("hangcredit")&&userrole.getHangcredit().equals("否")){
				returnmessage("抱歉！你没有挂账权限...");	
				result = "error";
			}
			*//**
			 * 挂账权限控制结束
			 *//*
			*//**
			 * 挂账管理支付权限控制开始
			 *//*
			
			if(actionName.equals("hangcreditcheck")&&userrole.getHangpay().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("hangcreditcheck")&&userrole.getHangpay().equals("否")){
				returnmessage("抱歉！你没有挂账管理支付权限...");	
				result = "error";
			}
			*//**
			 * 挂账管理支付控制结束
			 *//*
			*//**
			 * 用户以及角色权限控制开始
			 *//*
			//用户查看
			if(actionName.equals("usergrid")&&userrole.getPermissionmanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("usergrid")&&userrole.getPermissionmanage().equals("否")){

				result = "error";
			}
			//用户添加
			if(actionName.equals("useradd")&&userrole.getPermissionmanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("useradd")&&userrole.getPermissionmanage().equals("否")){
				returnmessage("抱歉！你没有用户以及角色权限...");	
				result = "error";
			}
			//用户修改和删除没有必要控制了，因为记录无法被选择
			//角色查看
			if(actionName.equals("userrolegrid")&&userrole.getPermissionmanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("userrolegrid")&&userrole.getPermissionmanage().equals("否")){
				returnmessage("抱歉！你没有用户以及角色权限...");		
				result = "error";
			}
			//角色添加
			if(actionName.equals("userroleadd")&&userrole.getPermissionmanage().equals("是")){
				result = invocation.invoke();				
			}else if(actionName.equals("userroleadd")&&userrole.getPermissionmanage().equals("否")){
				returnmessage("抱歉！你没有用户以及角色权限...");	
				result = "error";
			}
			//角色修改和删除没有必要控制了，因为记录无法被选择
			
			*//**
			 * 用户以及角色权限控制结束
			 *//*
			*//**
			 * 销售权限控制开始
			 *//*
			if(actionName.equals("saleitemadd")&&userrole.getSale().equals("是")){				
				result = invocation.invoke();
				
			}else if(actionName.equals("saleitemadd")&&userrole.getSale().equals("否")){					
				returnmessage("抱歉！你没有销售权限...");	
				result="error";
			}
			*//**
			 * 销售权限控制结束
			 *//*
			*//**
			 * 报表权限控制开始
			 *//*
			if(actionName.equals("salemonthcountreport")&&userrole.getReportmanage().equals("是")){				
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result = invocation.invoke();
				
			}else if(actionName.equals("salemonthcountreport")&&userrole.getReportmanage().equals("否")){					
				returnmessage("抱歉！你没有权限...");	
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result="error";
			}
			if(actionName.equals("producttypereport")&&userrole.getReportmanage().equals("是")){				
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result = invocation.invoke();
				
			}else if(actionName.equals("producttypereport")&&userrole.getReportmanage().equals("否")){					
				returnmessage("抱歉！你没有权限...");	
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result="error";
			}
			if(actionName.equals("salemonthcountreportPie")&&userrole.getReportmanage().equals("是")){				
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result = invocation.invoke();
				
			}else if(actionName.equals("salemonthcountreportPie")&&userrole.getReportmanage().equals("否")){					
				returnmessage("抱歉！你没有权限...");	
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result="error";
			}
			if(actionName.equals("producttypereportPie")&&userrole.getReportmanage().equals("是")){				
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result = invocation.invoke();
				
			}else if(actionName.equals("producttypereportPie")&&userrole.getReportmanage().equals("否")){					
				returnmessage("抱歉！你没有权限...");	
				System.out.println("actionName="+actionName+"\reportmanage="+userrole.getReportmanage());
				result="error";
			}
			*//**
			 * 报表权限控制结束
			 *//*
			
		} else {
			System.out.println("拦截器error");
			result = "error";
		}
		t("end");
		System.out.println("result:"+result);
		return result;

	}
	*//**
	 * 返回信息提示
	 * @param message
	 *//*
	private void returnmessage(String message) {
		//返回成功提示信息
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("permissionmessage", message);
	}
	public void writeJson(int total,List list) {
	    try {
	        Map<String, Object> maps = new HashMap<String, Object>();     
	        maps.put("rows", list);
	        maps.put("total", total);
	        String json = JSON.toJSONString(maps);
	        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
	        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
	        ServletActionContext.getResponse().getWriter().write(json);
	        ServletActionContext.getResponse().getWriter().flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	        }
	    }
}*/