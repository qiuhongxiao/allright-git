package cn.edu.scau.service;

import java.util.List;

import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.Producttype;

public interface ProducttypeService {
	
	public boolean delProducttype(int producttypeid);

	public boolean updateProducttype(Producttype producttype);	

	public int getTotal(String producttypenamesearch);

	public List<Producttype> findAllProducttype(int page, int rows, String sort,
			String order,String producttypenamesearch);

	public void saveProducttype(Producttype producttype);
	
	public List<Producttype> findAllProducttype();
	
	public Producttype get(int id);
	

}
