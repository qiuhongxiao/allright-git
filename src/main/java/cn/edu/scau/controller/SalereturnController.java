package cn.edu.scau.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.scau.service.SalereturnSerivce;
import cn.edu.scau.service.UserService;
import cn.edu.scau.vo.Orderdetail;
import cn.edu.scau.vo.User;




/**
 * @author wxj
 *
 */
@Controller
public class SalereturnController {
	private static final long serialVersionUID = 1L;
	@Resource(name="salereturnService")
	private SalereturnSerivce salereturnService;
	@Resource(name="userService")
	private UserService userService;
	private String order_no;
	private String product_no;
	private int returnquantity;
	private String returnreason;
	private String returnpeople_name;
	/**
	 * 查询可以进行退货的数量，特别是同一商品已经退货了一部分的，则可退货数量将会相应地减少
	 */
	@RequestMapping(value="canreturnitem")
	public @ResponseBody Map<String, Object> salereturnCheck(String order_no,String product_no ,HttpSession httpSession){
		List<Orderdetail> items = new ArrayList<Orderdetail>();
		Orderdetail item = new Orderdetail();
		if(order_no!=null&&order_no!=""&&product_no!=null&&product_no!=""){
			item = salereturnService.getCanreturnitem(order_no,product_no);
			if(item!=null){
				items.add(item);
			}
			/*Map session = (Map) ActionContext.getContext().getSession();		
			session.put("order_no", this.getOrder_no());
			session.put("product_no", this.getProduct_no());
			session.put("canreturnquantity", item.getQuantity());*/
			//this.writeJson(items.size(), items);
		//	httpSession.setAttribute("orderno", order_no);
		//	httpSession.setAttribute("productno", product_no);
			httpSession.setAttribute("canreturnquantity", item.getQuantity());
			Map<String, Object> map=new HashMap<>();
	        map.put("rows",items );
	        map.put("total", items.size());
	        return map;
		}else{
			//this.writeJson(0, null);
			Map<String, Object> map=new HashMap<>();
	        map.put("rows",null );
	        map.put("total", 0);
	        return map;
		}
	}
	/**
	 *计算退货是要退多少钱
	 * @return
	 */
	@RequestMapping(value = "salereturnmoney")
	public String Salereturnmoney(String order_no,String product_no,int returnquantity,HttpSession httpSession){
		double money = 0;
		if(order_no!=null&&order_no!=""&&product_no!=null&&product_no!=""&&returnquantity!=0){
			try{
				money = salereturnService.getSalereturnmoney(order_no,product_no,returnquantity);
//				Map session = (Map) ActionContext.getContext().getSession();				
//				session.put("returnquantity", this.getReturnquantity());	
				
			}catch(Exception e){
				e.printStackTrace();
				return "error";
			}					
		}
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern("0.00");
		String allsalepriceformat = decimalFormat.format(money);
		System.out.println("moneyString="+allsalepriceformat);
//		Map session = (Map) ActionContext.getContext().getSession();		
//		session.put("salereturnmoney1", allsalepriceformat);
		httpSession.setAttribute("orderno", order_no);
		httpSession.setAttribute("productno", product_no);
		httpSession.setAttribute("returnquantity", returnquantity);
		httpSession.setAttribute("salereturnmoneytwo", allsalepriceformat);
		//httpSession.getAttribute("orderno");
		return "salereturn";
	}
	
	@RequestMapping(value="salereturnconfirm")
	public String salereturnconfirm(String order_no,String product_no,int returnquantity,HttpSession httpSession){
		if(order_no!=null&&order_no!=""&&product_no!=null&&product_no!=""&&returnquantity!=0){
			try{
//				Map<String, Object> session = (Map<String, Object>) ActionContext
//						.getContext().getSession();
//				User user = (User) session.get("user");
				String str=(String)httpSession.getAttribute("username");
				User user=userService.getUser(str);
				String returnpeople_name = null;
				if (user != null) {
					returnpeople_name = user.getUsername();
				}
				else{
					returnpeople_name="无名";
				}
				salereturnService.updateSalereturnAndSalereturndetail(order_no,product_no,returnquantity,returnpeople_name,returnreason);				
//				returnmessage("退款成功，可进行下一次退货！");
				httpSession.setAttribute("successmessage", "退款成功，可进行下一次退货！");
			}catch(Exception e){
				e.printStackTrace();
				return "error";
			}					
		}
		httpSession.setAttribute("orderno", "");
		httpSession.setAttribute("productno", "");
		httpSession.setAttribute("returnquantity", "0");
		httpSession.setAttribute("salereturnmoneytwo", "0");
		return "salereturn";
	}
	
	 
	 /*	private void returnmessage(String message) {
		//返回成功提示信息
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("successmessage", message);
	}*/
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public int getReturnquantity() {
		return returnquantity;
	}
	public void setReturnquantity(int returnquantity) {
		this.returnquantity = returnquantity;
	}
	public String getReturnreason() {
		return returnreason;
	}
	public void setReturnreason(String returnreason) {
		this.returnreason = returnreason;
	}
	public String getReturnpeople_name() {
		return returnpeople_name;
	}
	public void setReturnpeople_name(String returnpeople_name) {
		this.returnpeople_name = returnpeople_name;
	}
	

}
