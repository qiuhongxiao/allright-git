package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
@Entity
@Table(name="orderdetail")
public class Orderdetail implements Serializable {

	private int id;
	private String order_no;
	private String product_no;
	private String productname;
	private double price;
	private double saleprice;
	private double discount;
	private int quantity;
	private String saletime;
	
	
	@Id
	@Column(name="id",nullable=false,unique=true,length=11)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="order_no",nullable=false,unique=true,length=36)
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	@Column(name="product_no",nullable=false,unique=true,length=36)
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	
	@Column(name="productname",nullable=false,unique=true,length=36)
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
	@Column(name="saleprice",nullable=false,unique=false)
	public double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(double saleprice) {
		this.saleprice = saleprice;
	}
	@Column(name="discount",nullable=false,unique=false)
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@Column(name="quantity",nullable=false,unique=false,length=11)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column(name="saletime",nullable=false,unique=false)
	public String getSaletime() {
//		Date date = new Date(saletime.getTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
//		String saletime = sdf.format(date); 
		return saletime;
	}
	public void setSaletime(String saletime) {
		
		this.saletime = saletime;
	}
	
	
}
