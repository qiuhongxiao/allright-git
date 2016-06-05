package cn.edu.scau.controller;

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
import cn.edu.scau.vo.User;

/**
 * @author wxj
 *
 */
@Controller
public class UserController {
	@Autowired
	//@Resource
	private UserService userService;

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

	/*@RequestMapping(value = "/create", method = { RequestMethod.GET })
	public String create(@RequestParam String productid,@RequestParam String productname,@RequestParam Double price,@RequestParam String typename) {
		//System.out.println(product.getProductid());
		TType type=new TType();
		type.setTypename(typename);
		Product product=new Product();
		product.setTtype(type);
		product.setProductid(productid);
		product.setProductname(productname);
		product.setPrice(price);
		productService.saveProduct(product);
		return "success";

	}*/
	
	
	
}
