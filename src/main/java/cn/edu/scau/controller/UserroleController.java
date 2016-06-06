package cn.edu.scau.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.scau.service.UserroleService;
import cn.edu.scau.vo.Userrole;

@Controller
public class UserroleController  {

	private static final long serialVersionUID = 1L;
	@Resource(name="userroleService")
    private UserroleService userroleService;
    //采用modeldriven从页面获取数据应该要new userrole（）一下
    private Userrole userrole = new Userrole();
    private String rolenamesearch;
    /**
     * 得到数据列表
     */   
   /* @Action(value = "userrolegrid", results = { @Result(name = "userrolegrid", location = "/userrolemanage.jsp"),
			@Result(name = "error", location = "/error.jsp") },
	interceptorRefs = { @InterceptorRef(value = "myinterceptor") })
    public void grid(){
        int total = userroleService.getTotal(this.getRolenamesearch());
        //page为页数，rows为每页记录数
        List<Userrole> list = userroleService.findAllUserrole(page, rows, sort, order,this.getRolenamesearch()); 
        writeJson(total,list);
    }
    
	*//**
     * 添加操作
     *//*  
    @Action(value = "userroleadd", results = { @Result(name = "userroleadd", location = "/userrolemanage.jsp"),
			@Result(name = "error", location = "/error.jsp") },
			interceptorRefs = { @InterceptorRef(value = "myinterceptor") })
    public void add(){
            ResultMessage result = new ResultMessage();
            try {            	
            	Map<String, Object> session = (Map<String, Object>) ActionContext
						.getContext().getSession();
				User user = (User) session.get("user");
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
//				System.out.println(""+userrole.getBasedatamanage());
            	userroleService.saveUserrole(userrole);
                result.setSuccess(true);
                result.setMsg("添加角色成功");                
            } catch (Exception e) {
                result.setMsg("添加角色失败");                
            }
            writeJson(result);
    }
    *//**
     * 修改操作
     *//*   
    @Action(value = "userroleedit", results = { @Result(name = "userroleedit", location = "/userrolemanage.jsp.jsp"),
			@Result(name = "error", location = "/error.jsp") })
    public void edit() {
    	ResultMessage result = new ResultMessage();
            try {
            	Map<String, Object> session = (Map<String, Object>) ActionContext
						.getContext().getSession();
				User user = (User) session.get("user");
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
            writeJson(result);
    }
    *//**
     * 删除操作
     *//*   
    @Action(value = "userroleremove", results = { @Result(name = "userroleremove", location = "/userrolemanage.jsp.jsp"),
			@Result(name = "error", location = "/error.jsp") })
    public void remove() {
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
            writeJson(result);
    }  
	
	
	public String getRolenamesearch() {
		return rolenamesearch;
	}

	public void setRolenamesearch(String rolenamesearch) {
		this.rolenamesearch = rolenamesearch;
	}

	@Override
	public Userrole getModel() {
		// TODO Auto-generated method stub
		return userrole;
	}*/

}
