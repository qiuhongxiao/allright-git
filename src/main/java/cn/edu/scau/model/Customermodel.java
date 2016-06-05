package cn.edu.scau.model;

/**
 * 用于封装面向视图的数据
 * @author wxj
 *
 */
public class Customermodel implements java.io.Serializable{
	
	private int id;
	
	private String customerno;

	private String customername;

	private String telephone;

	private String address;	
	
	private String customerclass;
	
	public Customermodel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customermodel(int id, String customerno, String customername,
			String telephone, String address) {
		super();
		this.id = id;
		this.customerno = customerno;
		this.customername = customername;
		this.telephone = telephone;
		this.address = address;
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
