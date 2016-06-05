package cn.edu.scau.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.UserroleDaoImpl;
import cn.edu.scau.service.UserroleService;
import cn.edu.scau.vo.Userrole;


/**
 * @author wxj
 *
 */
@Service("userroleService")
@Transactional
public class UserroleServiceImpl implements UserroleService {
	@Resource(name="userroleDao")
	private UserroleDaoImpl userroleDao;

	@Override
	public List<Userrole> findAllUserrole() {
		// TODO Auto-generated method stub
		return userroleDao.findAll();
		
	}

	@Override
	public boolean delUserrole(int id) {
		String dehql = "delete Userrole u where u.id="+id;
		try{
			userroleDao.delete(dehql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
		
	}

	@Override
	public boolean updateUserrole(Userrole userrole) {
		try{
			userroleDao.update(userrole);
		    System.out.println(userrole.getBasedatamanage());
		    System.out.println(userrole.getHangcredit());
		    System.out.println(userrole.getHangpay());
		    System.out.println(userrole.getPermissionmanage());
		    System.out.println(userrole.getReportmanage());
		    System.out.println(userrole.getRolecreator());
		    System.out.println(userrole.getRolename());
		    System.out.println(userrole.getSale());
		    System.out.println(userrole.getSalereturn());
		    System.out.println(userrole.getRolecreatetime());
		    
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void saveUserrole(Userrole userrole) {
		try{
			userroleDao.save(userrole);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public int getTotal(String rolenamesearch) {		
		return userroleDao.countAllForSearch(rolenamesearch, "rolename");
	}

	@Override
	public List<Userrole> findAllUserrole(int page, int rows, String sort,
			String order, String rolenamesearch) {		
		return userroleDao.findAll(page, rows, sort, order, rolenamesearch, "rolename");
	}

	@Override
	public Userrole getRoleFromUserId(String userrole_rolename) {
		try{
			String hql = "from Userrole u where u.rolename='"+userrole_rolename+"'";
			Userrole userrole = userroleDao.get(hql);
			return userrole;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}

}
