package cn.edu.scau.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.UserDaoImpl;
import cn.edu.scau.service.UserService;
import cn.edu.scau.vo.User;
import cn.edu.scau.vo.Userrole;




/**
 * @author wxj
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource(name="userDao")
    private UserDaoImpl userDao;
   
	@Override
	public User userLogin(String username, String password) {
		// TODO Auto-generated method stub
		String hql="from User u where u.username='"+username+"' and u.password='"+password+"'";
		return userDao.get(hql);
	}

	@Override
	public int getTotal(String usernamesearch) {		
		return userDao.countAllForSearch(usernamesearch,"username");
	}

	@Override
	public List<User> findAllUser(int page, int rows, String sort,
			String order, String usernamesearch) {		
		return userDao.findAll(page,rows,sort,order,usernamesearch,"username");
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public boolean updateUser(User user) {
		try{
			userDao.update(user);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public boolean delUser(int id) {
		try{
			String delhql = "delete from User u where u.id="+id;
			userDao.delete(delhql);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.username='"+username+"'";
		return userDao.get(hql);
	}

	

}
