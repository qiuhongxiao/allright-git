package cn.edu.scau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

import cn.edu.scau.model.Customermodel;
import cn.edu.scau.service.CustomerService;
import cn.edu.scau.util.ResultMessage;
import cn.edu.scau.vo.Customer;

@Controller
public class CustomerController  {
	private static final long serialVersionUID = 1L;
	@Resource(name="customerService")
	private CustomerService customerService;

	/**
	 * 得到数据列表
	 */
	@RequestMapping(value = "customergrid")
	public @ResponseBody Map<String, Object>  grid(String customernamesearch,int page,int rows,String sort,String order) {		
		int total = customerService.getTotal(customernamesearch);
		System.out.println("tatol" + total);
		// page为页数，rows为每页记录数
		List<Customer> list = customerService.findAllCustomer(page, rows, sort,
				order, customernamesearch);
		//writeJson(total, getCustomerModel(list));
		Map<String, Object> map=new HashMap<>();
		map.put("rows", getCustomerModel(list));
		map.put("total", total);
		return map;
			
	}
	@RequestMapping(value = "customeradd")
	public @ResponseBody ResultMessage add(Customer customer) {
		ResultMessage result = new ResultMessage();
		try {
			customerService.saveCustomer(customer);
			result.setSuccess(true);
			result.setMsg("添加客户成功");
		} catch (Exception e) {
			result.setMsg("添加客户失败");
		}
		return result;
	}

	
	
	@RequestMapping(value = "customeredit")
	public @ResponseBody ResultMessage edit(Customer customer) {
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
		return result;
	}

	@RequestMapping(value = "customerremove")
	public @ResponseBody ResultMessage remove(Customer customer) {
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
		return result;
	}



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
	}

}
