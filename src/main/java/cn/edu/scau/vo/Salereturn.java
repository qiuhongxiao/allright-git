package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 创建时间：2015年5月14日 下午9:08:10
 * 项目名称：Post
 * @author pnz
 * @version 1.0
 * 类说明：
 */
@Entity
@Table(name="salereturn")
public class Salereturn implements Serializable {

	private int id;
	private String salereturnno;
	private Timestamp returntime;
	private String returnpeople_name;
	private String order_no;
	private String returnreason;
	@Id
	@Column(name="id",nullable=false,unique=true,length=11)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="salereturnno",nullable=false,unique=true,length=36)
	public String getSalereturnno() {
		return salereturnno;
	}
	public void setSalereturnno(String salereturnno) {
		this.salereturnno = salereturnno;
	}
	@Column(name="returntime",nullable=false)
	public Timestamp getReturntime() {
		return returntime;
	}
	public void setReturntime(Timestamp returntime) {
		this.returntime = returntime;
	}
	@Column(name="returnpeople_name",nullable=false,unique=false,length=36)
	public String getReturnpeople_name() {
		return returnpeople_name;
	}
	public void setReturnpeople_name(String returnpeople_name) {
		this.returnpeople_name = returnpeople_name;
	}
	@Column(name="order_no",nullable=false,unique=false,length=36)
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	@Column(name="returnreason",nullable=true,unique=false,length=200)
	public String getReturnreason() {
		return returnreason;
	}
	public void setReturnreason(String returnreason) {
		this.returnreason = returnreason;
	}
	
}
