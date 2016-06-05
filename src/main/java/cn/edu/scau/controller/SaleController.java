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

	private Bill bill = new Bill();
	private int id;
	private String customerno;
	/* 前面传过来的数据，折扣为字符串类型，所以需要做适当的转换 */
	private String discount;
	private String productno;
	private int paystate;
	//挂账所需下面三个变量支持
	private String customername;
	private String telephone;
	private String address;
	//挂账订单的查询
	private String hangcustomername;
	private String hangtelephone;

	// @Override
	// public Bill getModel() {
	// // TODO Auto-generated method stub
	// return bill;
	// }
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
			httpSession.setAttribute("totalmoney", str);
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
				//updateTotalMoney();
			

	}

	
	

	@AuthPassport
	@RequestMapping(value = "saleconfrim")
	public String saleconfirm(String customerno,int paystate,HttpSession httpSession) {
		
		 
		try {
			List<Bill> bills = saleService.getAllBill();
			if (bills.size() != 0) {
				
				 
				String customer_no = saleService.getCustomerno(customerno,null,null,null);
				
				 
//				Map<String, Object> session = (Map<String, Object>) ActionContext
//						.getContext().getSession();
				
				//User user = (User) session.get("user");
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
			//updateTotalMoney();
			httpSession.setAttribute("totalmoney", updateTotalMoney());
			httpSession.setAttribute("successmessage", "支付成功，可继续下一次销售！");
		} catch (Exception e) {
			httpSession.setAttribute("totalmoney", updateTotalMoney());
			e.printStackTrace();
			return "error";
		}
		return "sale";
	}	

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
			httpSession.setAttribute("totalmoney", updateTotalMoney());
			result.setMsg("客户挂账成功");
			//return "error";
		}
		return result;
		//return "hangcredit";
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
		//String totalmoney = allsaleprice.toString();
		System.out.println("doubleallslfkd" + allsaleprice.toString());
		
		return allsalepriceformat;
	}
	/**
	 * 返回成功信息
	 * @param message
	 */
	/*private String returnmessage(String message) {
		//返回成功提示信息
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("successmessage", message);
	}*/

	/**
	 * 得到挂账定单的商品销售明细列表，暂无法查找，有待解决。。。
	 */
	@RequestMapping(value = "hangcreditcheck")
	// , results = { @Result(name = "saleitem", location = "/sale.jsp") })
	public @ResponseBody Map<String, Object> hangcreditcheck(String hangcustomername,String hangtelephone,HttpSession httpSession) {
		try {
			List<Orderdetail> list = saleService.hangcreditcheck(
					hangcustomername, hangtelephone);
			
			httpSession.setAttribute("hangcustomernamestring", hangcustomername);
			httpSession.setAttribute("hangtelephonestring", hangtelephone);
			
			/*Map maptest = (Map)ActionContext.getContext().getSession();
			System.out.println("allhangmoney=:"+maptest.get("allhangmoney")+"hangcustomername=:"
			+maptest.get("hangcustomernamestring")+"hangtelephonestring=:"
					+maptest.get("hangtelephonestring"));*/
			//caculateAllHangMoney(list);
			//writeJson(list.size(), list);
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
	 * 计算总金额
	 * 注意事项，action的方法范围不可以声明为private
	 * @return
	 */
	/*@Action(value = "caculateallhangmoney", results = {
			@Result(name = "caculateallhangmoney", location = "/hangcredit.jsp"),
			@Result(name = "error", location = "/hangcredit.jsp") })
	public String caculateAllHangMoney() {
		// TODO Auto-generated method stub
		try{
		List<Orderdetail> list = saleService.hangcreditcheck(
				this.getHangcustomername(), this.getHangtelephone());
		Double allhangmoney = (double) 0;
		if (list != null) {
			for (Orderdetail orderdetail : list) {
				allhangmoney += orderdetail.getSaleprice();
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern("0.00");
		String allsalepriceformat = decimalFormat.format(allhangmoney);
		
		//String allhangmoneystring = allhangmoney.toString();
		System.out.println("hangallhangmoney:" + allhangmoney.toString());
		Map session = (Map) ActionContext.getContext().getSession();		
		session.put("allhangmoney", allsalepriceformat);
		
		Map map1 = (Map) ActionContext.getContext().getSession();
		String allhangmoney1 = (String) map1.get("allhangmoney");

		System.out.println("计算总金额hangallhangmoney=" + allhangmoney1);
		return "caculateallhangmoney";
		}catch(Exception e){
			
			e.printStackTrace();
			return "caculateallhangmoney";
		}
		
	}*/

	/**
	 * 客户支付欠账，更新支付状态，生成支付记录
	 */
	/*@Action(value = "hangcreditpay", results = {
			@Result(name = "hangcreditpay", location = "/hangcredit.jsp"),
			@Result(name = "error", location = "/hangcredit.jsp") })
	public String hangcreditpay() {
		
		 * 如果客户不存在，生产客户号 如果客户不存在，需要生产客户表 生成订单号 已经确认的订单，生产订单表，和订单明细表，以及支付表
		 
		try {
			
				Map<String, Object> session = (Map<String, Object>) ActionContext
						.getContext().getSession();
				User user = (User) session.get("user");
				String seler_name = null;
				if (user != null) {
					seler_name = user.getUsername();
				}
				else{
					seler_name="无名";
				}				
				
				saleService.hangPay(this.getHangcustomername(), this.getHangtelephone(), seler_name);
				//Map<String, Object> map = new HashMap<String, Object>();
				Map session1 = (Map) ActionContext.getContext().getSession();
				session1.put("allhangmoney", "0");
				session1.put("hangcustomernamestring", "");
				session1.put("hangtelephonestring", "");
				
				Map maptest = (Map)ActionContext.getContext().getSession();
				System.out.println("确认之后allhangmoney=:"+maptest.get("allhangmoney")+"确认之后hangcustomername=:"
				+maptest.get("hangcustomernamestring")+"确认之后hangtelephonestring=:"
						+maptest.get("hangtelephonestring"));
				returnmessage("挂账支付成功，可进行下一次挂账支付！");
				
		} catch (Exception e) {
			updateTotalMoney();
			e.printStackTrace();
			return "error";
		}
		return "hangcreditpay";
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCustomerno() {
		return customerno;
	}

	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPaystate() {
		return paystate;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHangcustomername() {
		return hangcustomername;
	}

	public void setHangcustomername(String hangcustomername) {
		this.hangcustomername = hangcustomername;
	}

	public String getHangtelephone() {
		return hangtelephone;
	}

	public void setHangtelephone(String hangtelephone) {
		this.hangtelephone = hangtelephone;
	}
	
	

}
