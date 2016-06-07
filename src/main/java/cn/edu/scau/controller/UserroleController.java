package cn.edu.scau.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.scau.service.UserService;
import cn.edu.scau.service.UserroleService;
import cn.edu.scau.util.ResultMessage;
import cn.edu.scau.vo.User;
import cn.edu.scau.vo.Userrole;

@Controller
public class UserroleController  {

	private static final long serialVersionUID = 1L;
	@Resource(name="userroleService")
    private UserroleService userroleService;
	
	@Resource(name="userService")
    private UserService userService;
    /**
     * 得到数据列表
     */   
    @RequestMapping(value = "userrolegrid")
    public @ResponseBody Map<String, Object> grid(int page,int rows,String sort,String order,String rolenamesearch){
        int total = userroleService.getTotal(rolenamesearch);
        //page为页数，rows为每页记录数
        List<Userrole> list = userroleService.findAllUserrole(page, rows, sort, order,rolenamesearch); 
        Map<String, Object> map=new HashMap<>();
        map.put("total", total);
        map.put("rows",list);
        return map;
    }
    
	 
     @RequestMapping(value = "userroleadd")
    public @ResponseBody ResultMessage add(HttpSession httpSession,Userrole userrole){
            ResultMessage result = new ResultMessage();
            try {            	
            	String username=(String) httpSession.getAttribute("username");
            	User user=userService.getUser(username);
				String rolecreator = null;
				if (user != null) {
					rolecreator = user.getUsername();
				}
				else{
					rolecreator="无名";
				}				
				Date date = new Date();
				Timestamp rolecreatetime = new Timestamp(date.getTime());
				userrole.setRolecreator(rolecreator);
				userrole.setRolecreatetime(rolecreatetime);
            	userroleService.saveUserrole(userrole);
                result.setSuccess(true);
                result.setMsg("添加角色成功");                
            } catch (Exception e) {
                result.setMsg("添加角色失败");                
            }
           return result;
    }
     
     
     
   
     @RequestMapping(value = "userroleedit")
    public @ResponseBody ResultMessage edit(HttpSession httpSession,Userrole userrole) {
    	ResultMessage result = new ResultMessage();
            try {
            	String username=(String) httpSession.getAttribute("username");
            	User user=userService.getUser(username);
				String rolecreator = null;
				if (user != null) {
					rolecreator = user.getUsername();
				}
				else{
					rolecreator="无名";
				}				
				Date date = new Date();
				Timestamp rolecreatetime = new Timestamp(date.getTime());
				userrole.setRolecreator(rolecreator);
				userrole.setRolecreatetime(rolecreatetime);
                boolean flag = userroleService.updateUserrole(userrole);
                if(flag){
                result.setSuccess(true);
                result.setMsg("修改角色成功");
                }else{
                	result.setSuccess(false);
                	result.setMsg("修改角色失败");
                }
               
            } catch (Exception e) {
            	result.setSuccess(false);
                result.setMsg("修改角色失败");
            }
            return result;
    }

     
     
     
     @RequestMapping(value = "userroleremove")
    public @ResponseBody ResultMessage remove(Userrole userrole) {
    	ResultMessage result = new ResultMessage();
            try {
                boolean flag = userroleService.delUserrole(userrole.getId());
                if(flag){
                   result.setSuccess(true);
                   result.setMsg("删除角色成功");
                }else{
                	result.setSuccess(false);
                	 result.setMsg("删除角色失败");
                }
            } catch (Exception e) {
            	result.setSuccess(false);
                result.setMsg("删除角色失败");
            }
            return result;
    }  
	
    

}
