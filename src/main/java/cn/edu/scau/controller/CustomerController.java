package cn.edu.scau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSONObject;

import cn.edu.scau.service.CustomerService;
import cn.edu.scau.vo.Customer;

@Controller
public class CustomerController  {
	private static final long serialVersionUID = 1L;
	@Resource(name="customerService")
	private CustomerService customerService;
	// 采用modeldriven从页面获取数据应该要new Customer（）一下
	private Customer customer = new Customer();
	private String customernamesearch;

	/**
	 * 得到数据列表
	 */
//	 results = {
//				@Result(name = "customergird", location = "/customer.jsp"),
//				@Result(name = "error", location = "/customer.jsp") },
	/*@Action(value = "customergrid", results = {
			@Result(name = "customergird", location = "/customer.jsp"),
			@Result(name = "error", location = "/error.jsp") },interceptorRefs = { @InterceptorRef(value = "myinterceptor") })
	public void  grid() {		
		System.out.println("search的客户名称：" + getCustomernamesearch()
				+ super.getPage() + super.getRows());
		int total = customerService.getTotal(getCustomernamesearch());
		System.out.println("tatol" + total);
		// page为页数，rows为每页记录数
		List<Customer> list = customerService.findAllCustomer(page, rows, sort,
				order, getCustomernamesearch());
		writeJson(total, getCustomerModel(list));
			
	}
	*//**
	 * 得到用于安卓的试一试数据列表
	 *//*
	@Action(value = "androidcustomergrid")
			
	public void androidcustomergrid() {
//		System.out.println("search的客户名称：" + getCustomernamesearch()
//				+ super.getPage() + super.getRows());
//		int total = customerService.getTotal(getCustomernamesearch());
//		System.out.println("tatol" + total);
//		// page为页数，rows为每页记录数
		List<Customer> list = customerService.androidFindAllCustomer();			
		writeJson(4, list);
		// Date date=new Date();
		// java.sql.DateTime timestamp=new java.sql.Timestamp(date.getTime());
	}


	*//**
	 * 添加操作
	 *//*
	@Action(value = "customeradd", results = { @Result(name = "customeradd", location = "/customer.jsp"),
			@Result(name = "error", location = "/error.jsp") }
	,interceptorRefs = { @InterceptorRef(value = "myinterceptor") })
	public void add() {
		ResultMessage result = new ResultMessage();
		try {
			customerService.saveCustomer(customer);
			result.setSuccess(true);
			result.setMsg("添加客户成功");
		} catch (Exception e) {
			result.setMsg("添加客户失败");
		}
		writeJson(result);
	}

	*//**
	 * 修改操作
	 *//*
	@Action(value = "customeredit", results = { @Result(name = "customeredit", location = "/customer.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public void edit() {
		ResultMessage result = new ResultMessage();
		try {
			System.out.println("edit被调用");
			boolean flag = customerService.updateCustomer(customer);
			if (flag) {
				result.setSuccess(true);
				result.setMsg("修改客户成功");
			} else {
				result.setSuccess(false);
				result.setMsg("修改客户失败");
			}

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("修改客户失败");
		}
		writeJson(result);
	}

	*//**
	 * 删除操作
	 *//*
	@Action(value = "customerremove", results = { @Result(name = "customerremove", location = "/customer.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public void remove() {
		ResultMessage result = new ResultMessage();
		try {
			boolean flag = customerService.delCustomer(customer.getId());
			if (flag) {
				result.setSuccess(true);
				result.setMsg("删除客户成功");
			} else {
				result.setSuccess(false);
				result.setMsg("删除客户失败");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("删除客户失败");
		}
		writeJson(result);
	}

	@Override
	public Customer getModel() {
		return customer;
	}

	public String getCustomernamesearch() {
		if (customernamesearch != null && !customernamesearch.isEmpty())
			return customernamesearch;
		return "";
	}

	public void setCustomernamesearch(String customernamesearch) {

		this.customernamesearch = customernamesearch;
	}

	*//**
	 * 将获取的多表记录进行封装，为便于返回json显示
	 * 
	 * @param list
	 * @return
	 *//*
	private List<Customermodel> getCustomerModel(List<Customer> list) {
		// TODO Auto-generated method stub
		List<Customermodel> customermodels = new ArrayList<Customermodel>();
		Customermodel customermodel = null;
		for (Customer customer : list) {
			customermodel = new Customermodel(customer.getId(),
					customer.getCustomerno(), customer.getCustomername(),
					customer.getTelephone(), customer.getAddress());
			customermodel.setCustomerclass(customer.getCustomerclass());
			customermodels.add(customermodel);
		}
		return customermodels;
	}*/

}
