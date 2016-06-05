package cn.edu.scau.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 创建时间：2015年4月20日 下午9:51:39
 * 项目名称：Post
 * @author pnz
 * @version 1.0
 * 类说明：
 */
@Entity
@Table(name="serialnumber")
public class Serialnumber implements Serializable {
	private int saleordernumber;
	private int customernumber;
	private int paymentnumber;
	private int id;
	private int salereturnnumber;
	
	public Serialnumber(){
		
	}
	
	public Serialnumber(int saleordernumber, int customernumber, int paymentnumber, int salereturnnumber) {
		super();
		this.saleordernumber = saleordernumber;
		this.customernumber = customernumber;
		this.paymentnumber = paymentnumber;
		this.salereturnnumber = salereturnnumber;
	}

	@Id
	@Column(name="id",nullable=false,unique=true,length=11)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="saleordernumber",nullable=false,unique=true,length=36)
	public int getSaleordernumber() {
		return saleordernumber;
	}

	public void setSaleordernumber(int saleordernumber) {
		this.saleordernumber = saleordernumber;
	}
	@Column(name="customernumber",nullable=false,unique=true,length=36)
	public int getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
	}
	@Column(name="paymentnumber",nullable=false,unique=true,length=36)
	public int getPaymentnumber() {
		return paymentnumber;
	}

	public void setPaymentnumber(int paymentnumber) {
		this.paymentnumber = paymentnumber;
	}

	@Column(name="salereturnnumber",nullable=false,unique=true,length=36)
	public int getSalereturnnumber() {
		return salereturnnumber;
	}

	public void setSalereturnnumber(int salereturnnumber) {
		this.salereturnnumber = salereturnnumber;
	}

	
	
	

}
