package cn.edu.scau.vo;


public class Customer implements java.io.Serializable{
	/**
	 * 客户ID
	 */
	private int id;
	
	/**
	 * 客户编号
	 */
	private String customerno;
	
	/**
	 * 客户名
	 */
	private String customername;
	/**
	 * 客户电话
	 */
	private String telephone;
	/**
	 * 客户电话
	 */
	private String address;
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
