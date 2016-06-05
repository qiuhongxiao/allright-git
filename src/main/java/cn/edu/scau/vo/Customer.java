package cn.edu.scau.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "customer")
public class Customer implements java.io.Serializable{
	/**
	 * 客户ID
	 */
	@Id
	@Column(name = "id",unique=true,nullable=false,length=11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/**
	 * 客户编号
	 */
	@Column(name = "customerno", length = 36,unique=true,nullable=false)
	private String customerno;
	
	/**
	 * 客户名
	 */
	@Column(name = "customername", length = 36,nullable=true)
	private String customername;
	/**
	 * 客户电话
	 */
	@Column(name = "telephone", length = 36,nullable=true)
	private String telephone;
	/**
	 * 客户电话
	 */
	@Column(name = "address", length = 100,nullable=true)
	private String address;
	@Column(name = "customerclass",length=100,nullable=false)
	private String customerclass;
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerno() {
		return customerno;
	}
	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerclass() {
		return customerclass;
	}
	public void setCustomerclass(String customerclass) {
		this.customerclass = customerclass;
	}
	
	
	
	

	
	

	

}
