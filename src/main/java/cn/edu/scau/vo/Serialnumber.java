package cn.edu.scau.vo;

import java.io.Serializable;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getSaleordernumber() {
		return saleordernumber;
	}

	public void setSaleordernumber(int saleordernumber) {
		this.saleordernumber = saleordernumber;
	}
	public int getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
	}
	public int getPaymentnumber() {
		return paymentnumber;
	}

	public void setPaymentnumber(int paymentnumber) {
		this.paymentnumber = paymentnumber;
	}

	public int getSalereturnnumber() {
		return salereturnnumber;
	}

	public void setSalereturnnumber(int salereturnnumber) {
		this.salereturnnumber = salereturnnumber;
	}

	
	
	

}
