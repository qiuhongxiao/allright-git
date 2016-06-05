package cn.edu.scau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.scau.util.AuthPassport;



/**
 * @author wxj
 *
 */
@Controller
@RequestMapping("/mvc")
public class mvcController {
 
    @RequestMapping("/error")
    public String hello(){        
        return "error";
    }
    
    @RequestMapping("/grid")
    public void grid(String productnamesearch){ 
    	
        System.out.println(productnamesearch);
    }
    
   // @AuthPassport
    @RequestMapping("/sale")
    public String sale(){        
        return "salesitem";
    }
}
