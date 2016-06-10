package cn.edu.scau.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="product")
public class Product implements java.io.Serializable{
	
	private int id;
	
	private String productno;	
	
	private String productname;	
	
	private String description;	
	
	private double price;	
	
	private Producttype producttype;
	
	private int stockquantity;
	
	//private int producttype_id;

	public Product() {
		super();
	}
	
	@Id
	@Column(name="id",length=11,nullable=false,unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="productno",unique=true,nullable=false,length=36)
	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}
	
	@Column(name="productname",nullable=false,length=36,unique=true)
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	@Column(name="description",nullable=true,length=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="price",nullable=false,length=50)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
//	@Column(name="producttype_id",nullable=false,length=11)
//	public int getProducttype_id() {
//		return producttype_id;
//	}
//
//	public void setProducttype_id(int producttype_id) {
//		this.producttype_id = producttype_id;
//	}
	
	//fetch=FetchType.LAZY,
	/**
	 * 这里的EAGER很有讲究，如果这边多的一方设为EAGER，而那边也设为EAGER，就会导致少的一方出现很多的冗余数据
	 * 而这边设置为LAZY,那边设置问也设置为LAZY就会在浏览调试的时候出现no session,和lazyinitianiztor（忘记怎么写了）错误
	 * 多的一边设为LAZY，少的一边设为EAGER也会出现问题
	 * 所以，多的一边设为EAGER，少的一边设为LAZY
	 * @return
	 */
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name = "producttype_id", referencedColumnName="id")
	public Producttype getProducttype() {
		return producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}
	@Column(name="stockquantity",nullable=false,length=11)
	public int getStockquantity() {
		return stockquantity;
	}

	public void setStockquantity(int stockquantity) {
		this.stockquantity = stockquantity;
	}
	
}
