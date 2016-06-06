package cn.edu.scau.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.scau.dao.CustomerDaoImpl;
import cn.edu.scau.dao.ProductDaoImpl;
import cn.edu.scau.service.ProductService;
import cn.edu.scau.service.ProducttypeService;
import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.Product;
import cn.edu.scau.vo.Producttype;

public class HiberTest {
	ApplicationContext factory=new ClassPathXmlApplicationContext("hibernate-context.xml");
	ProductService productService=(ProductService)factory.getBean("productService");
	ProducttypeService producttypeService=(ProducttypeService)factory.getBean("producttypeService");
	CustomerDaoImpl customerDao=(CustomerDaoImpl)factory.getBean("customerDao");
	ProductDaoImpl productDao=(ProductDaoImpl)factory.getBean("productDao");
	@Test
	public void test(){
		
		
		
		Producttype producttype=new Producttype();
		producttype.setProducttypename("huangjiaho");
		Product product =new Product();
		product.setPrice(22);
		product.setProductname("pingguo");
		product.setProductno("AAA2");
		product.setProducttype(producttype);
		productService.saveProduct(product);
	}
	
	
	
	@Test
	public void testfindall(){
		ApplicationContext factory=new ClassPathXmlApplicationContext("hibernate-context.xml");
		//ProductDaoImpl hiber=(ProductDaoImpl)factory.getBean("productDao");
		ProductService hiber=(ProductService)factory.getBean("productService");
		
		List<Product> list=new ArrayList<>();
		list=hiber.findAllProduct(1, 4, "productno", "asc", "aa");
		//list=hiber.findAll();
		for(Product product:list){
			System.out.println(product.getId());
		}
	}
	
	@Test
	public void testget(){
		int id=1;
		Producttype producttype=producttypeService.get(id);
		Product product =new Product();
		product.setPrice(22);
		product.setProductname("pingguo");
		product.setProductno("AAA2");
		product.setProducttype(producttype);
		productService.saveProduct(product);
	}
	@Test
	public void testcustomerdao(){
		String customerno="u001";
		String hql = "from Customer cp where cp.customerno='"+customerno+"'";
		Customer customer=customerDao.get(hql);
		System.out.println(customer.getCustomername());
	}
	
	@Test
	public void testproductdao(){
		String productno="a001";
		String hql = "from Product p where p.productno='"+productno+"'";
		Product product=productDao.get(hql);
		System.out.println(product.getProductname());
	}
	
	
	@Test
	public void testsameprottypeid(){
		int producttype_id=3;
		Producttype pt = producttypeService.get(producttype_id);
		Product product=productService.getProduct("a002");
    	//Producttype pt=new Producttype();
    	//pt.setId(producttype_id);
    	//Producttype ptt=new Producttype(pt.getId(), pt.getProducttypename(), pt.getDescription(), pt.getProducts());
		product.setDescription("bbb");
    	product.setProducttype(pt);
        boolean flag = productService.updateProduct(product);
	}
	
	
	@Test
	public void teststring(){
		String str1="2016-5-31";
		String str2="2016-05-31";
		if(str1.compareTo(str2)==0){
			System.out.println("====");
		}else System.out.println("xxx");
	}
	
	@Test
	public void format(){
		int a=1;
		System.out.println(String.format("%02d", a));
	}
	
	
	//@Test
	public String testswitch(){
		String url="add";
		String judge="是";
		switch (url) {
		case "add": 
			if("是".equals(judge))
			return "add";
			
		
		default:
			System.out.println("===");
			return "=====";
		}
	}
	
	@Test
	public void print(){
		System.out.println(testswitch());
	}
	
	 
}
