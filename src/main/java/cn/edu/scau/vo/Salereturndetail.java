package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
/**
 * 创建时间：2015年5月14日 下午9:30:59
 * 项目名称：Post
 * @author pnz
 * @version 1.0
 * 类说明：
 */
@Entity
@Table(name="salereturndetail")
public class Salereturndetail implements Serializable {

	private int id;
	private String salereturn_no;
	private String product_no;
	private String productname;
	private double price;
	private double returnmoney;
	private int returnquantity;
	private Timestamp returntime;
	private String order_no;
	@Id
	@Column(name="id",nullable=false,unique=true,length=11)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="salereturn_no",nullable=false,unique=false,length=36)
	public String getSalereturn_no() {
		return salereturn_no;
	}
	public void setSalereturn_no(String salereturn_no) {
		this.salereturn_no = salereturn_no;
	}
	@Column(name="product_no",nullable=false,unique=false,length=36)
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	@Column(name="productname",nullable=false,unique=false,length=36)
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	@Column(name="price",nullable=false,unique=false)
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name="returnmoney",nullable=false,unique=false)
	public double getReturnmoney() {
		return returnmoney;
	}
	public void setReturnmoney(double returnmoney) {
		this.returnmoney = returnmoney;
	}
	@Column(name="returnquantity",nullable=false,unique=false,length=11)
	public int getReturnquantity() {
		return returnquantity;
	}
	public void setReturnquantity(int returnquantity) {
		this.returnquantity = returnquantity;
	}
	@Column(name="returntime",nullable=false,unique=false)
	public Timestamp getReturntime() {
		return returntime;
	}
	public void setReturntime(Timestamp returntime) {
		this.returntime = returntime;
	}
	@Column(name="order_no",nullable=false,unique=false,length=36)
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	
}
