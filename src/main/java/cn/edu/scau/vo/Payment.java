package cn.edu.scau.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 创建时间：2015年4月20日 下午5:53:52 项目名称：Post
 * 
 * @author pnz
 * @version 1.0 类说明：
 */
@Entity
@Table(name = "payment")
public class Payment implements java.io.Serializable {

	private int id;
	private String paymentno;
	private String paymentmethod;
	private double amount;
	private Timestamp paytime;
	private String order_no;
	private String payee_name;

	@Id
	@Column(name = "id", nullable = false, unique = true, length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "paymentno", nullable = false, unique = true, length = 36)
	public String getPaymentno() {
		return paymentno;
	}

	public void setPaymentno(String paymentno) {
		this.paymentno = paymentno;
	}

	@Column(name = "paymentmethod", nullable = false, unique = false, length = 36)
	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	@Column(name = "amount", nullable = false, unique = false)
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "paytime", nullable = false, unique = false)
	public Timestamp getPaytime() {
		return paytime;
	}

	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	@Column(name = "payee_name", nullable = false, length = 36, unique = false)
	public String getPayee_name() {
		return payee_name;
	}

	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}

	@Column(name = "order_no", nullable = false, unique = true, length = 36)
	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

}
