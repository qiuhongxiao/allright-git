package cn.edu.scau.dao;


import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.Customer;
@Repository("customerDao")
public class CustomerDaoImpl extends HibernateDAO<Customer>{
}
