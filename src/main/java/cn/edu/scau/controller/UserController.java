package cn.edu.scau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.scau.service.ProductService;
import cn.edu.scau.service.UserService;
import cn.edu.scau.service.UserroleService;
import cn.edu.scau.util.ResultMessage;
import cn.edu.scau.vo.User;
import cn.edu.scau.vo.Userrole;

/**
 * @author wxj
 *
 */
@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private UserroleService userroleService;
	
	/**
	 * 
	 * 用户登录
	 */
	@RequestMapping(value="/login",method={ RequestMethod.POST })
    public  String login(String username,String password,HttpSession httpSession){
    	
    	
			User user=userService.userLogin(username, password);
			if (user != null) {
				httpSession.setAttribute("username", user.getUsername());
				return "index";
			} else {
				return "error";
			}
				
			
		
    	
    }
	
	
	@RequestMapping(value = "logout")
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute("usrename");
		return "login";
	}
	
	
	
	
	/**
     * 得到数据列表
     */   
	@RequestMapping(value = "usergrid")
    public @ResponseBody Map<String, Object> grid(int page,int rows,String sort,String order,String usernamesearch){
        int total = userService.getTotal(usernamesearch);
        //page为页数，rows为每页记录数
        List<User> list = userService.findAllUser(page, rows, sort, order,usernamesearch); 
        Map<String, Object> map=new HashMap<>();
        map.put("total", total);
        map.put("rows", list);
        return map;
    }
    
    
    
	/**
     * 添加操作
     */  
	@RequestMapping(value = "useradd")
    public @ResponseBody ResultMessage add(User user){
            ResultMessage result = new ResultMessage();
            try {
            	
            	userService.saveUser(user);
                result.setSuccess(true);
                result.setMsg("添加用户成功");                
            } catch (Exception e) {
                result.setMsg("添加用户失败");                
            }
            return result;
    }
		/**
		 * 编辑操作
		 * 
		 */
	
	   @RequestMapping(value = "useredit")
	    public @ResponseBody ResultMessage edit(User user) {
	    	ResultMessage result = new ResultMessage();
	            try {
	                boolean flag = userService.updateUser(user);
	                if(flag){
	                result.setSuccess(true);
	                result.setMsg("修改用户成功");
	                }else{
	                	result.setSuccess(false);
	                	result.setMsg("修改用户失败");
	                }
	               
	            } catch (Exception e) {
	            	result.setSuccess(false);
	                result.setMsg("修改用户失败");
	            }
	           return result;
	    }
	
	    /**
	     * 删除操作
	     */   
	   @RequestMapping(value = "userremove")
	    public @ResponseBody ResultMessage remove(User user) {
	    	ResultMessage result = new ResultMessage();
	            try {
	                boolean flag = userService.delUser(user.getId());
	                if(flag){
	                   result.setSuccess(true);
	                   result.setMsg("删除用户成功");
	                }else{
	                	result.setSuccess(false);
	                	 result.setMsg("删除用户失败");
	                }
	            } catch (Exception e) {
	            	result.setSuccess(false);
	                result.setMsg("删除用户失败");
	            }
	           return result;
	    }
	   
	   
	   /**
	     * 得到所有角色数据列表
	     */   
	   @RequestMapping(value = "user_userrolegrid")
	    public @ResponseBody List<Userrole> producttypegrid(){
	        List<Userrole> list = userroleService.findAllUserrole(); 
	        return list;
	    }
	   
	
	
	 /**
     * 关于自己的权限
     */   
	@RequestMapping(value = "userabout")
    public String userabout(HttpSession httpSession){
      
		String username=(String) httpSession.getAttribute("username");
		User user=userService.getUser(username);
		if(user!=null){
			Userrole userrole=userroleService.getRoleFromUserId(user.getUserrole_rolename());
			httpSession.setAttribute("username", user.getUsername());
			httpSession.setAttribute("sale", userrole.getSale());
			httpSession.setAttribute("salereturn", userrole.getSalereturn());
			httpSession.setAttribute("hangcredit", userrole.getHangcredit());
			httpSession.setAttribute("hangpay", userrole.getBasedatamanage());
			httpSession.setAttribute("basedatamanage", userrole.getBasedatamanage());
			httpSession.setAttribute("permissionmanage", userrole.getPermissionmanage());
			httpSession.setAttribute("reportmanage", userrole.getReportmanage());
		}
        return "about";
    }
	
}
