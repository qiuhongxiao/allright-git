package cn.edu.scau.dao;

import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.Bill;
@Repository("billDao")
public class BillDaoImpl extends HibernateDAO<Bill> {

}
