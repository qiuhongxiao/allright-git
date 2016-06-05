package cn.edu.scau.model;


/**
 * @author wxj
 *
 */
public class Productmodel implements java.io.Serializable {
	
	private int id;
	
	private String productno;	
	
	private String productname;	
	
	private String description;	
	
	private double price;	
	
	private int producttype_id;
	
	private String producttypename;
	
	private int stockquantity;

	public Productmodel() {
		super();
	}

	public Productmodel(int id, String productno, String productname,
			String description, double price, int producttype_id,
			String producttype_name) {
		super();
		this.id = id;
		this.productno = productno;
		this.productname = productname;
		this.description = description;
		this.price = price;
		this.producttype_id = producttype_id;
		this.producttypename = producttype_name;
	}
	

	public Productmodel(int id, String productno, String productname,
			String description, double price, int producttype_id) {
		super();
		this.id = id;
		this.productno = productno;
		this.productname = productname;
		this.description = description;
		this.price = price;
		this.producttype_id = producttype_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProducttype_id() {
		return producttype_id;
	}

	public void setProducttype_id(int producttypeid) {
		this.producttype_id = producttypeid;
	}

	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}

	public int getStockquantity() {
		return stockquantity;
	}

	public void setStockquantity(int stockquantity) {
		this.stockquantity = stockquantity;
	}
	

	
	
	
}
