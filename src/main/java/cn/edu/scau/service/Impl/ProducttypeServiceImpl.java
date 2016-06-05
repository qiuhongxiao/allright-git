package cn.edu.scau.service.Impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.ProducttypeDaoImpl;
import cn.edu.scau.service.ProducttypeService;
import cn.edu.scau.vo.Producttype;

/**
 * @author wxj
 *
 */
@Service("producttypeService")
@Transactional
public class ProducttypeServiceImpl implements ProducttypeService{
	@Resource(name="producttypeDao")
	private ProducttypeDaoImpl producttypeDao;

	
	@Override
	public boolean delProducttype(int producttypeid) {		
		try{
        String delhql = "delete from Producttype p where p.id="+producttypeid;		
		producttypeDao.delete(delhql);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProducttype(Producttype producttype) {
		// TODO Auto-generated method stub
		try{
			producttypeDao.update(producttype);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	
	@Override
	public int getTotal(String producttypenamesearch) {
		// TODO Auto-generated method stub
		return producttypeDao.countAllForSearch(producttypenamesearch,"producttypename");
	}

	@Override
	public List<Producttype> findAllProducttype(int page, int rows, String sort,
			String order,String producttypenamesearch) {
		// TODO Auto-generated method stub
		return producttypeDao.findAll(page,rows,sort,order,producttypenamesearch,"producttypename");
	}

	@Override
	public void saveProducttype(Producttype producttype) {
		// TODO Auto-generated method stub
		System.out.println("============================"+producttypeDao.toString());
		producttypeDao.save(producttype);
	}

	@Override
	public List<Producttype> findAllProducttype() {
		// TODO Auto-generated method stub
	    return producttypeDao.findAll();
	}
	
	@Override
	public Producttype get(int id){
		return producttypeDao.get(id);
	}

}
