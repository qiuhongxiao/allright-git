package cn.edu.scau.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.scau.service.CustomerService;
import cn.edu.scau.service.ProductService;
import cn.edu.scau.service.SaleService;
import cn.edu.scau.service.SerialnumberService;
import cn.edu.scau.service.UserService;
import cn.edu.scau.service.Impl.SaleServiceImpl;
import cn.edu.scau.util.AuthPassport;
import cn.edu.scau.util.ResultMessage;
import cn.edu.scau.util.createNoUtils;
import cn.edu.scau.vo.Bill;
import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.Orderdetail;
import cn.edu.scau.vo.Product;
import cn.edu.scau.vo.Producttype;
import cn.edu.scau.vo.Saleorder;
import cn.edu.scau.vo.User;



/**
 * @author wxj
 *
 */
@Controller
public class SaleController {

	private static final long serialVersionUID = 1L;
	@Resource(name = "saleService")
	private SaleService saleService;
	@Resource(name = "customerService")
	private CustomerService customerService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "serialnumberService")
	private SerialnumberService serialnumberService;
	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 得到开单的商品列表
	 */
	@RequestMapping(value = "saleitem")
	public @ResponseBody Map<String, Object> grid(HttpSession httpSession) {
		Map<String, Object> map=new HashMap<>();
		List<Bill> list =new ArrayList<Bill>();
		try {
			int total = saleService.getTotal();
			 list = saleService.getAllBill();
			String str=updateTotalMoney();
			
			map.put("rows", list);
			map.put("total", total);
		//	httpSession.setAttribute("totalmoney", str);
			//map.put("totalmoney", str);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			map.put("rows", list);
			map.put("total", 0);
		//	return null;
		}
		return map;
	}
	
	@AuthPassport
	@RequestMapping(value = "saleitemadd")
	public ModelAndView saleitemadd(String productno,String discount) {
		
		 
		try {
			saleService.saveBill(productno, discount);
		} catch (Exception e) {
			e.printStackTrace();
			updateTotalMoney();
			return null;
		}
		Map<String, Object> map=new HashMap<>();
		map.put("totalmoney", updateTotalMoney());
		//return map;
		ModelAndView mv=new ModelAndView("sale", map);
		return mv;

	}

	/**
	 * 删除操作
	 */
	@AuthPassport
	@RequestMapping(value = "saleitemremove")
	public ModelAndView remove(int id) {
		
			
				saleService.saleItemRemove(id);
				List<Bill> list = saleService.getAllBill();
				Map<String, Object> map=new HashMap<>();
				map.put("totalmoney", updateTotalMoney());
				ModelAndView mv=new ModelAndView("sale", map);
				return mv;
			

	}

	
	

	@AuthPassport
	@RequestMapping(value = "saleconfrim")
	public ModelAndView saleconfirm(String customerno,int paystate,HttpSession httpSession) {
		
		 
		try {
			List<Bill> bills = saleService.getAllBill();
			if (bills.size() != 0) {
				
				 
				String customer_no = saleService.getCustomerno(customerno,null,null,null);
				
				 
				String username=(String)httpSession.getAttribute("username");
				User user=userService.getUser(username);
				String seler_name = null;
				if (user != null) {
					seler_name = user.getUsername();
				}
				else{
					seler_name="无名";
				}
				String neworderno = saleService.saveSaleorder(customer_no,
						paystate, seler_name);
				
				 
				saleService.saveOrderdetailAndUpdateStock(bills, neworderno);
				double amount = 0;
				for(Bill bill:bills){
					amount += bill.getSaleprice();
				}
				saleService.savePayment(neworderno, amount, seler_name);				
			}
			//httpSession.setAttribute("totalmoney", updateTotalMoney());
		//	httpSession.setAttribute("successmessage", "支付成功，可继续下一次销售！");
		} catch (Exception e) {
			//httpSession.setAttribute("totalmoney", updateTotalMoney());
			e.printStackTrace();
			//return "error";
		}
		Map<String, Object> map=new HashMap<>();
		map.put("totalmoney", updateTotalMoney());
	//	map.put("successmessage", "支付成功，可继续下一次销售！");
		ModelAndView mv=new ModelAndView("sale", map);
		return mv;
	}	
	/**
	 * 
	 * 挂账
	 */
	@AuthPassport
	@RequestMapping(value = "hangcredit")
	public @ResponseBody ResultMessage hangcredit(String customerno,String customername,String telephone,String address,HttpSession httpSession) {	
		
		ResultMessage result = new ResultMessage();
		try {
			List<Bill> bills = saleService.getAllBill();
			if (bills.size() != 0) {
				
				 
				String customer_no = saleService.getCustomerno(customerno,customername,telephone,address);
				
				 
				String username=(String)httpSession.getAttribute("username");
				User user=userService.getUser(username);
				String seler_name = null;
				if (user != null) {
					seler_name = user.getUsername();
				}
				else{
					seler_name="无名";
				}
				String neworderno = saleService.saveSaleorder(customer_no,
						0, seler_name);
				
				 
				saleService.saveOrderdetailAndUpdateStock(bills, neworderno);
				double amount = 0;
				for(Bill bill:bills){
					amount += bill.getSaleprice();
				}
				//saleService.savePayment(neworderno, amount, seler_name);
			}
			httpSession.setAttribute("totalmoney", updateTotalMoney());
			result.setSuccess(true);
			result.setMsg("客户挂账成功");
		} catch (Exception e) {
			e.printStackTrace();
		//	httpSession.setAttribute("totalmoney", updateTotalMoney());
			result.setMsg("客户挂账成功");
			//return "error";
		}
		return result;
	}
	
	/**
	 * 得到挂账定单的商品销售明细列表
	 */
	@RequestMapping(value = "hangcreditcheck")
	public @ResponseBody Map<String, Object> hangcreditcheck(String hangcustomername,String hangtelephone,HttpSession httpSession) {
		try {
			List<Orderdetail> list = saleService.hangcreditcheck(
					hangcustomername, hangtelephone);
			
			httpSession.setAttribute("hangcustomernamestring", hangcustomername);
			httpSession.setAttribute("hangtelephonestring", hangtelephone);
			//httpSession.setAttribute("allhangmoneytemp", "0.0");
			
			Map<String, Object> map=new HashMap<>();
	        
	        map.put("rows",list );
	        map.put("total", list.size());
	        return map;

		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 更新总金额
	 */
	private String updateTotalMoney() {
		// TODO Auto-generated method stub
		List<Bill> list = saleService.getAllBill();
		Double allsaleprice = (double) 0;
		if (list != null) {
			for (Bill bill2 : list) {
				allsaleprice = allsaleprice + bill2.getSaleprice();
			}
			
		}
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern("0.00");
		String allsalepriceformat = decimalFormat.format(allsaleprice);
		System.out.println("doubleallslfkd" + allsaleprice.toString());
		
		return allsalepriceformat;
	}

	
	/**
	 * 计算总金额
	 */
	@RequestMapping(value = "caculateallhangmoney")
	public ModelAndView caculateAllHangMoney(String hangcustomername,String hangtelephone,HttpSession httpSession) {
		// TODO Auto-generated method stub
		String allsalepriceformat="";
		try{
		List<Orderdetail> list = saleService.hangcreditcheck(hangcustomername,hangtelephone);
		Double allhangmoney = (double) 0;
		if (list != null) {
			for (Orderdetail orderdetail : list) {
				allhangmoney += orderdetail.getSaleprice();
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern("0.00");
		 allsalepriceformat = decimalFormat.format(allhangmoney);
		
		System.out.println("hangallhangmoney:" + allhangmoney.toString());
	//	httpSession.setAttribute("allhangmoneytemp", allsalepriceformat);

		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		Map<String, Object> map=new HashMap<>();
		map.put("allhangmoneytemp", allsalepriceformat);
		ModelAndView mv=new ModelAndView("hangcredit", map);
		return mv;
	}

	/**
	 * 客户支付欠账，更新支付状态，生成支付记录
	 */
	@RequestMapping(value = "hangcreditpay")
	public String hangcreditpay(String hangcustomername,String hangtelephone,HttpSession httpSession) {
		try {
			
				String username=(String) httpSession.getAttribute("username");
				User user=userService.getUser(username);
				String seler_name = null;
				if (user != null) {
					seler_name = user.getUsername();
				}
				else{
					seler_name="无名";
				}				
				
				saleService.hangPay(hangcustomername, hangtelephone, seler_name);
				httpSession.setAttribute("successmessage", "挂账支付成功，可进行下一次挂账支付！");
				
		} catch (Exception e) {
			updateTotalMoney();
			e.printStackTrace();
			return "error";
		}
		return "hangcredit";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
