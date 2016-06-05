package cn.edu.scau.dao;

import org.springframework.stereotype.Repository;

import cn.edu.scau.base.dao.HibernateDAO;
import cn.edu.scau.vo.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDAO<User>{

}
