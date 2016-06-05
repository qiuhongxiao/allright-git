package cn.edu.scau.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.CustomerDaoImpl;
import cn.edu.scau.service.CustomerService;
import cn.edu.scau.service.SerialnumberService;
import cn.edu.scau.util.createNoUtils;
import cn.edu.scau.vo.Customer;

/**
 * @author wxj
 *
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	@Resource(name="customerDao")
	private CustomerDaoImpl customerDao;
	@Resource(name = "serialnumberService")
	private SerialnumberService serialnumberService;

	@Override
	public boolean delCustomer(int customerid) {
		// TODO Auto-generated method stub		
		try{
		String delhql= "delete from Customer c where c.id="+customerid;
		customerDao.delete(delhql);
		//这里应该验证一下，先前存在，然后删除就没有了
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try{
			customerDao.update(customer);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}
	
	@Override
	public int getTotal(String customernamesearch) {
		// TODO Auto-generated method stub
		return customerDao.countAllForSearch(customernamesearch,"customername");
	}

	@Override
	public List<Customer> findAllCustomer(int page, int rows, String sort,
			String order, String customernamesearch) {
		// TODO Auto-generated method stub
		return customerDao.findAll(page,rows,sort,order,customernamesearch,"customername");
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String customerno = createNoUtils
				.createCustomerno(serialnumberService.getCustomernumber());
		customer.setCustomerno(customerno);
		customerDao.save(customer);
	}

	@Override
	public Customer getCustomer(String customerno) {
		// TODO Auto-generated method stub
		String hql = "from Customer c where c.customerno='"+customerno+"'";
		return customerDao.get(hql);
	}

	@Override
	public List<Customer> androidFindAllCustomer() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
	}

}
