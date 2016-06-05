package cn.edu.scau.model;

import java.io.Serializable;


/**
 * @author wxj
 *
 */
public class ProducttypeCountModel implements Serializable {

	private String producttypename;
	private double producttypesalemoney;
	public ProducttypeCountModel(String producttypename,
			double producttypemoneycount) {
		this.producttypename = producttypename;
		this.producttypesalemoney = producttypemoneycount;
	}
	public String getProducttypename() {
		return producttypename;
	}
	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}
	public double getProducttypesalemoney() {
		return producttypesalemoney;
	}
	public void setProducttypesalemoney(double producttypesalemoney) {
		this.producttypesalemoney = producttypesalemoney;
	}
	
}
