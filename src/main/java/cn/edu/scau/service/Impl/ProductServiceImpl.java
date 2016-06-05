package cn.edu.scau.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.ProductDaoImpl;
import cn.edu.scau.service.ProductService;
import cn.edu.scau.vo.Product;

/**
 * @author wxj
 *
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{
	@Resource(name="productDao")
	private ProductDaoImpl productDao;

	
	@Override
	public boolean delProduct(int productid) {
		
		try{
			String delhql = "delete from Product p where p.id="+productid;
			productDao.delete(delhql);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		try{
			productDao.update(product);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}

	
	@Override
	public int getTotal(String productnamesearch) {
		// TODO Auto-generated method stub
		return productDao.countAllForSearch(productnamesearch,"productname");
	}

	@Override
	public List<Product> findAllProduct(int page, int rows, String sort,
			String order,String productnamesearch) {
		// TODO Auto-generated method stub
		return productDao.findAll(page,rows,sort,order,productnamesearch,"productname");
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}

	@Override
	public Product getProduct(String productno) {
		// TODO Auto-generated method stub
		String hql = "from Product p where p.productno='"+productno+"'";
		return productDao.get(hql);
	}

}
