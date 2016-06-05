package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
@Entity
@Table(name="saleorder")
public class Saleorder implements Serializable {
   
	private int id;
	
	private String orderno;
	
	private String customer_no;
	
	private int state;
	
	private Timestamp ordertime;
	
	private String seller_name;

	@Id
	@Column(name="id",nullable=false,unique=true,length=11)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="orderno",nullable=false,unique=true,length=36)
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name="customer_no",nullable=false,unique=true,length=36)
	public String getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}
	@Column(name="state",nullable=false,unique=false,length=11)
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	@Column(name="ordertime",nullable=false,unique=false)
	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	@Column(name="seller_name",nullable=false,unique=false,length=36)
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	
	
	
	
}
