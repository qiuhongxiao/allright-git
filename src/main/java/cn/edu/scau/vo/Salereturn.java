package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Salereturn implements Serializable {

	private int id;
	private String salereturnno;
	private Timestamp returntime;
	private String returnpeople_name;
	private String order_no;
	private String returnreason;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSalereturnno() {
		return salereturnno;
	}
	public void setSalereturnno(String salereturnno) {
		this.salereturnno = salereturnno;
	}
	public Timestamp getReturntime() {
		return returntime;
	}
	public void setReturntime(Timestamp returntime) {
		this.returntime = returntime;
	}
	public String getReturnpeople_name() {
		return returnpeople_name;
	}
	public void setReturnpeople_name(String returnpeople_name) {
		this.returnpeople_name = returnpeople_name;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getReturnreason() {
		return returnreason;
	}
	public void setReturnreason(String returnreason) {
		this.returnreason = returnreason;
	}
	
}
