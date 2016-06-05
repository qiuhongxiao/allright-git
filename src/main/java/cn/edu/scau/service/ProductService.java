package cn.edu.scau.service;

import java.util.List;

import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.Product;

public interface ProductService {
	
	public boolean delProduct(int productid);

	public boolean updateProduct(Product product);	

	public int getTotal(String productnamesearch);

	public List<Product> findAllProduct(int page, int rows, String sort,
			String order,String productnamesearch);

	public void saveProduct(Product product);

	public Product getProduct(String productno);

}
