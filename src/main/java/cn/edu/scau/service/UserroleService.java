package cn.edu.scau.service;

import java.util.List;

import cn.edu.scau.vo.Userrole;


public interface UserroleService {

	public List<Userrole> findAllUserrole();

	public boolean delUserrole(int id);

	public boolean updateUserrole(Userrole userrole);

	public void saveUserrole(Userrole userrole);

	public int getTotal(String rolenamesearch);

	public List<Userrole> findAllUserrole(int page, int rows, String sort,
			String order, String rolenamesearch);

	public Userrole getRoleFromUserId(String userrole_rolename);

	

}
