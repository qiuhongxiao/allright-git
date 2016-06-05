package cn.edu.scau.dao;


import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.Serialnumber;
@Repository("serialnumberDao")
public class SerialnumberDaoImpl extends HibernateDAO<Serialnumber>{
}
