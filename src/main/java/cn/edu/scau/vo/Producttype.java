package cn.edu.scau.vo;

import java.util.HashSet;
import java.util.Set;

public class Producttype implements java.io.Serializable {

	private int id;

	private String producttypename;

	private String description;

	private Set<Product> products = new HashSet<Product>();

	public Producttype() {
		super();
	}
	
	
	
	
	
	public Producttype(int id, String producttypename, String description, Set<Product> products) {
		super();
		this.id = id;
		this.producttypename = producttypename;
		this.description = description;
		this.products = products;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
