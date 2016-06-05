package cn.edu.scau.model;


/**
 * @author wxj
 *
 */
public class Producttypemodel implements java.io.Serializable{
	
	private int id;	
	
	private String producttypename;	
	
	private String description;		
	
	public Producttypemodel() {
		super();
	}

	public Producttypemodel(int id, String producttypename, String description) {
		super();
		this.id = id;
		this.producttypename = producttypename;
		this.description = description;
	}
	

	public Producttypemodel(int id, String producttypename) {
		super();
		this.id = id;
		this.producttypename = producttypename;
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
	

}
