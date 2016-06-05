package cn.edu.scau.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import cn.edu.scau.model.Productmodel;
import cn.edu.scau.model.Producttypemodel;
import cn.edu.scau.service.ProducttypeService;
import cn.edu.scau.util.AuthPassport;
import cn.edu.scau.util.ResultMessage;
import cn.edu.scau.vo.Producttype;


/**
 * @author wxj
 *
 */
@Controller
public class ProducttypeController {
	private static final long serialVersionUID = 1L;
	@Resource(name="producttypeService")
    private ProducttypeService producttypeService;
    /**
     * 得到数据列表
     */ 
	@AuthPassport
    @RequestMapping(value = "producttypegrid")
    public @ResponseBody Map<String, Object> grid(int page,int rows,String sort,String order,String producttypenamesearch){
        int total = producttypeService.getTotal(producttypenamesearch);
        //page为页数，rows为每页记录数
        List<Producttype> list = producttypeService.findAllProducttype(page, rows, sort, order,producttypenamesearch); 
        Map<String, Object> map=new HashMap<>();
        map.put("rows", getProducttypeModel(list));
        map.put("total", total);
        return map;
    }
    
	
    
    
    @RequestMapping(value = "producttypeadd")
    public @ResponseBody ResultMessage add(Producttype producttype){
            ResultMessage result = new ResultMessage();
            try {
            	producttypeService.saveProducttype(producttype);
                result.setSuccess(true);
                result.setMsg("添加产品类别成功");                
            } catch (Exception e) {
                result.setMsg("添加产品类别失败");                
            }
           return result;
    }
   
    
    
    @RequestMapping(value = "producttypeedit")
    public @ResponseBody ResultMessage edit(Producttype producttype) {
    	ResultMessage result = new ResultMessage();
            try {
            	System.out.println("producttypeedit被调用");
                boolean flag = producttypeService.updateProducttype(producttype);
                if(flag){
                result.setSuccess(true);
                result.setMsg("修改产品类别成功");
                }else{
                	result.setSuccess(false);
                	result.setMsg("修改产品类别失败");
                }
               
            } catch (Exception e) {
            	result.setSuccess(false);
                result.setMsg("修改产品类别失败");
            }
            return result;
    }
     
    @RequestMapping(value = "producttyperemove")
    public @ResponseBody ResultMessage remove(Producttype producttype) {
    	ResultMessage result = new ResultMessage();
            try {
                boolean flag = producttypeService.delProducttype(producttype.getId());
                if(flag){
                   result.setSuccess(true);
                   result.setMsg("删除产品类别成功");
                }else{
                	result.setSuccess(false);
                	 result.setMsg("删除产品类别失败");
                }
            } catch (Exception e) {
            	result.setSuccess(false);
                result.setMsg("删除产品类别失败");
            }
            return result;
    }
     
	private List<Producttypemodel> getProducttypeModel(List<Producttype> list) {
		// TODO Auto-generated method stub
		List<Producttypemodel> producttypemodels = new ArrayList<Producttypemodel>();
		Producttypemodel producttypemodel=null;
		for(Producttype producttype:list){
			producttypemodel = new Producttypemodel(producttype.getId(),producttype.getProducttypename(),producttype.getDescription());
		    producttypemodels.add(producttypemodel);
		}
		return producttypemodels;
	}
    
}
