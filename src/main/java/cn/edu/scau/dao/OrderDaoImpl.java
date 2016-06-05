package cn.edu.scau.dao;

import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.Saleorder;
@Repository("saleorderDao")
public class OrderDaoImpl extends HibernateDAO<Saleorder> {

}
