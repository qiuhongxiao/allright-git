package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;
public class Salereturndetail implements Serializable {

	private int id;
	private String salereturn_no;
	private String product_no;
	private String productname;
	private double price;
	private double returnmoney;
	private double returnquantity;
	private Timestamp returntime;
	private String order_no;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSalereturn_no() {
		return salereturn_no;
	}
	public void setSalereturn_no(String salereturn_no) {
		this.salereturn_no = salereturn_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getReturnmoney() {
		return returnmoney;
	}
	public void setReturnmoney(double returnmoney) {
		this.returnmoney = returnmoney;
	}
	public double getReturnquantity() {
		return returnquantity;
	}
	public void setReturnquantity(double returnquantity) {
		this.returnquantity = returnquantity;
	}
	public Timestamp getReturntime() {
		return returntime;
	}
	public void setReturntime(Timestamp returntime) {
		this.returntime = returntime;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	
}
