package cn.edu.scau.dao;

import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.Userrole;


/**
 * @author wxj
 *
 */
@Repository("userroleDao")
public class UserroleDaoImpl extends HibernateDAO<Userrole> {

}
