package cn.edu.scau.vo;

public class Productbbb implements java.io.Serializable{
	
	private int id;
	
	private String productno;	
	
	private String productname;	
	
	private String description;	
	
	private double price;	
	
	private Producttype producttype;
	
	private int stockquantity;
	
	//private int producttype_id;

	public Productbbb() {
		super();
	}
	
	
	
	public Productbbb(int id, String productno, String productname, String description, double price,
			Producttype producttype, int stockquantity) {
		super();
		this.id = id;
		this.productno = productno;
		this.productname = productname;
		this.description = description;
		this.price = price;
		this.producttype = producttype;
		this.stockquantity = stockquantity;
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
	public Producttype getProducttype() {
		return producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}
	public int getStockquantity() {
		return stockquantity;
	}

	public void setStockquantity(int stockquantity) {
		this.stockquantity = stockquantity;
	}
	
}
