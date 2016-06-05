package cn.edu.scau.service;

import java.util.List;

import cn.edu.scau.vo.Product;
import cn.edu.scau.vo.User;
import cn.edu.scau.vo.Userrole;

public interface UserService {
      public User userLogin(String username,String password);

	public int getTotal(String usernamesearch);

	public List<User> findAllUser(int page, int rows, String sort,
			String order, String usernamesearch);

	public void saveUser(User user);

	public boolean updateUser(User user);

	public boolean delUser(int id);
	
	public User getUser(String username);

	
}
