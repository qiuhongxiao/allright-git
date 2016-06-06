package cn.edu.scau.util;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.edu.scau.service.UserService;
import cn.edu.scau.service.UserroleService;
import cn.edu.scau.vo.User;
import cn.edu.scau.vo.Userrole;

/**
 * @author wxj
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Resource(name = "userroleService")
	private UserroleService userroleService;
	@Resource(name = "userService")
	private UserService userService;
	
	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
        	AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
        	
       	 String url=request.getRequestURI();
       	 String methodurl=url.replace("/allright-git/", "");
       	 String context=request.getContextPath();
       	 System.out.println(methodurl+"======="+context);
       	
        	//没有声明需要权限,或者声明不验证权限
       	 	if(authPassport == null || authPassport.validate() == false)
                return true;
            else{  
            	 String username=(String)request.getSession().getAttribute("username");
               	 User user=userService.getUser(username);
               	 Userrole userrole=userroleService.getRoleFromUserId(user.getUserrole_rolename());
            	//在这里实现自己的权限验证逻辑
//            	if(false)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
//            		return true;
//            	else//如果验证失败
//            	{
//            		
//            		return decide(request, response);
//            	}   
            	
            	switch (methodurl) {
				case "prdoductgrid":
				case "producttypegrid":
						if("是".equals(userrole.getGrid()))
							return true;
						else if("否".equals(userrole.getGrid()))
							return decide(request, response);
				case "productadd":
				case "productedit":
				case "productremove":
				case "producttypeadd":
				case "producttypeedit":
				case "producttyperemove":
					if("是".equals(userrole.getEdit()))
						return true;
					else if("否".equals(userrole.getEdit()))
						return decide(request, response);

				default:
					return true;
				}
            	
            	
            }
        }
        else
       	 return true;   
	 }
	
	
	
	
	
	
	public boolean decide(HttpServletRequest request, HttpServletResponse response){
		if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with")
                        .equalsIgnoreCase("XMLHttpRequest"))//如果是ajax请求响应头会有，x-requested-with；
        {
            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
            return false;
        }else {
       	 try {
			response.sendRedirect("/allright-git/views/error.jsp");
			//request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
       	 
       	 return false;
        }
	}
	
	
	
	
	
	
	
	
}