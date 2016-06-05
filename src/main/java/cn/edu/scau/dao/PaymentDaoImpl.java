package cn.edu.scau.dao;

import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.Payment;


@Repository("paymentDao")
public class PaymentDaoImpl extends HibernateDAO<Payment> {

}
