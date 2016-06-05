package cn.edu.scau.service;

import java.util.List;

import cn.edu.scau.vo.Customer;

public interface CustomerService {
	
	public boolean delCustomer(int customerid);

	public boolean updateCustomer(Customer customer);

	public int getTotal(String customernamesearch);

	public List<Customer> findAllCustomer(int page, int rows, String sort,
			String order,String customernamesearch);

	public void saveCustomer(Customer customer);

	public Customer getCustomer(String customerno);

	public List<Customer> androidFindAllCustomer();

}
