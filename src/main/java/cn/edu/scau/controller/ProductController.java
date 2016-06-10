package cn.edu.scau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSONObject;

import cn.edu.scau.model.Customermodel;
import cn.edu.scau.model.Productmodel;
import cn.edu.scau.model.Producttypemodel;
import cn.edu.scau.service.CustomerService;
import cn.edu.scau.service.ProductService;
import cn.edu.scau.service.ProducttypeService;
import cn.edu.scau.util.AuthPassport;
import cn.edu.scau.util.ResultMessage;
import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.Product;
import cn.edu.scau.vo.Producttype;

/**
 * @author wxj
 *
 */
@Controller
public class ProductController  {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="productService")
    private ProductService productService;
	
    @Resource(name="producttypeService")
    private ProducttypeService producttypeService;
    
    private int producttype_id;
    
    @AuthPassport 
    @RequestMapping(value = "productgrid")
    public @ResponseBody Map<String, Object> grid(String productnamesearch,int page,int rows,String sort,String order){
        int total = productService.getTotal(productnamesearch);
        
        
        List<Product> list = productService.findAllProduct(page, rows, sort, order,productnamesearch);  
        Map<String, Object> map=new HashMap<>();
        
        map.put("rows", getProductModel(list));
        map.put("total", total);
        return map;
    }
   
	
    @AuthPassport
    @RequestMapping(value = "productadd")
    public @ResponseBody ResultMessage add(Product product,int producttype_id ){
            ResultMessage result = new ResultMessage();
            try {
            	
            	Producttype pt = producttypeService.get(producttype_id);
            //	pt.setId(producttype_id);
            	product.setProducttype(pt);
            	productService.saveProduct(product);
                result.setSuccess(true);
                result.setMsg("成功添加产品");                
            } catch (Exception e) {
            	e.printStackTrace();
                result.setMsg("添加产品失败");                
            }
            return result;
    }
    
    
     @AuthPassport
     @RequestMapping(value = "productedit")
    public @ResponseBody ResultMessage edit(Product product,int producttype_id) {
    	ResultMessage result = new ResultMessage();
            try {
            	System.out.println("开始编辑产品");
            	Producttype pt = producttypeService.get(producttype_id);
            	//Producttype pt=new Producttype();
            	//pt.setId(producttype_id);
            	//Producttype ptt=new Producttype(pt.getId(), pt.getProducttypename(), pt.getDescription(), pt.getProducts());
            	//Producttype pt=new Producttype(1, a, description, products)
            	product.setProducttype(pt);
                boolean flag = productService.updateProduct(product);
                if(flag){
                result.setSuccess(true);
                result.setMsg("成功编辑更新产品");
                }else{
                	result.setSuccess(false);
                	result.setMsg("编辑更新产品失败");
                }
               
            } catch (Exception e) {
            	e.printStackTrace();
            	result.setSuccess(false);
                result.setMsg("出错了！");
            }
            return result;
    }
   
     
    @AuthPassport
     @RequestMapping(value = "productremove")
    public @ResponseBody ResultMessage remove(Product product) {
    	ResultMessage result = new ResultMessage();
            try {
                boolean flag = productService.delProduct(product.getId());
                if(flag){
                   result.setSuccess(true);
                   result.setMsg("成功删除产品");
                }else{
                	result.setSuccess(false);
                	 result.setMsg("删除产品失败");
                }
            } catch (Exception e) {
            	e.printStackTrace();
            	result.setSuccess(false);
                result.setMsg("出错了！");
            }
            return result;
    }
    
     
     @RequestMapping(value = "product_producttypegrid")
    public @ResponseBody List<Producttypemodel> producttypegrid(){
        List<Producttype> listproducttype = producttypeService.findAllProducttype(); 
        for(Producttype producttype:listproducttype){
        	System.out.println("id:"+producttype.getId()+"producttypename:"+producttype.getProducttypename());
        }
        return getProducttypeModel(listproducttype);
    }
    

	
	 private List<Productmodel> getProductModel(List<Product> list) {
			// TODO Auto-generated method stub
		 List<Productmodel> productmodels = new ArrayList<Productmodel>();
		    Productmodel productmodel=null;
			for(Product product:list){
				productmodel=new Productmodel(product.getId(),product.getProductno(),product.getProductname(),
						product.getDescription(),product.getPrice(),product.getProducttype().getId(),product.getProducttype().getProducttypename());
				productmodel.setStockquantity(product.getStockquantity());
				productmodels.add(productmodel);
				System.out.println("输出产品类型的名字： "+product.getProducttype().getProducttypename());
			}
			return productmodels;
			
		}
	 /**
	  * 
	  * @param list
	  * @return
	  */
	 private List<Producttypemodel> getProducttypeModel(List<Producttype> list) {
			// TODO Auto-generated method stub
			List<Producttypemodel> producttypemodels = new ArrayList<Producttypemodel>();
			Producttypemodel producttypemodel=null;
			for(Producttype producttype:list){
				producttypemodel = new Producttypemodel(producttype.getId(),producttype.getProducttypename());
			    producttypemodels.add(producttypemodel);
			}
			return producttypemodels;
		}
}
