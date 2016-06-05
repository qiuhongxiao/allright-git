package cn.edu.scau.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.Producttype;
@Repository("producttypeDao")
public class ProducttypeDaoImpl extends HibernateDAO<Producttype> {

}
