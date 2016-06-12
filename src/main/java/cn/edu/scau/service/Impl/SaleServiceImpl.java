package cn.edu.scau.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.BillDaoImpl;
import cn.edu.scau.dao.CustomerDaoImpl;
import cn.edu.scau.dao.OrderDaoImpl;
import cn.edu.scau.dao.OrderdetailDaoImpl;
import cn.edu.scau.dao.PaymentDaoImpl;
import cn.edu.scau.dao.ProductDaoImpl;
import cn.edu.scau.service.SaleService;
import cn.edu.scau.service.SerialnumberService;
import cn.edu.scau.util.createNoUtils;
import cn.edu.scau.vo.Bill;
import cn.edu.scau.vo.Customer;
import cn.edu.scau.vo.Orderdetail;
import cn.edu.scau.vo.Payment;
import cn.edu.scau.vo.Product;
import cn.edu.scau.vo.Saleorder;

/**
 * @author wxj
 *
 */
@Service("saleService")
@Transactional
public class SaleServiceImpl implements SaleService {

	@Resource(name="billDao")
	private BillDaoImpl billDao;
	@Resource(name="paymentDao")
	private PaymentDaoImpl paymentDao;
	//@Resource(name="customerDao")
	@Autowired
	private CustomerDaoImpl customerDao;
	@Resource(name="productDao")
	private ProductDaoImpl productDao;
	@Resource(name="saleorderDao")
	private OrderDaoImpl saleorderDao;
	@Resource(name="orderdetailDao")
	private OrderdetailDaoImpl orderdetailDao;
	@Resource(name="serialnumberService")
	private SerialnumberService serialnumberService;

	@Override
	public List<Bill> getAllBill() {
		// TODO Auto-generated method stub
		return billDao.findAll();
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return billDao.countAll();
	}

	@Override
	public boolean saleItemRemove(int id) {
		// TODO Auto-generated method stub
		try {
			String delhql = "delete from Bill b where b.id=" + id;
			billDao.delete(delhql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// @Override
	// public void saveBill(Bill bill) {
	// // TODO Auto-generated method stub
	// billDao.save(bill);
	// }
	@Override
	public Bill getBill(String productno) {
		// TODO Auto-generated method stub
		String hql = "from Bill b where b.productno='" + productno + "'";
		return billDao.get(hql);
	}

	@Override
	public Product getProduct(String productno) {
		// TODO Auto-generated method stub
		String hql = "from Product p where p.productno='" + productno + "'";
		return productDao.get(hql);
	}

	@Override
	public void updateBill(Bill bill) {
		// TODO Auto-generated method stub
		billDao.update(bill);
	}

	@Override
	public void deleteAllBills() {
		// TODO Auto-generated method stub
		String hql = "delete Bill b where 1=1";
		billDao.delete(hql);
	}

	// @Override
	// public void saveSaleorder(Saleorder saleorder) {
	// // TODO Auto-generated method stub
	// saleorderDao.save(saleorder);
	// }
	@Override
	public void saveOrderdetail(Orderdetail orderdetail) {
		// TODO Auto-generated method stub
		orderdetailDao.save(orderdetail);

	}

	@Override
	public void saveBill(String productno, String discount,double quantity) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Bill b where b.productno='" + productno + "'";
		// 要对获取的对象进行操作，不能新建一个新的Bill对象，否则就会出现如下错误
		// org.hibernate.NonUniqueObjectException: a different object with the
		// same identifier value was already associated with the session:
		// [org.pnz.post.vo.Bill#39]
		Bill existbill = billDao.get(hql);
		if (existbill == null && productno != null && !productno.isEmpty()
				&& discount != null && !discount.isEmpty()) {
			Product product = this.getProduct(productno);
            existbill = new Bill();
			existbill.setProductno(product.getProductno());
			existbill.setProductname(product.getProductname());
			existbill.setPrice(product.getPrice());
			double newdiscount = Double.parseDouble(discount);
			existbill.setDiscount(newdiscount);
			double newsaleprice = (newdiscount * product.getPrice()*quantity) / 100;
			existbill.setSaleprice(newsaleprice);
			existbill.setQuantity(quantity);
			billDao.save(existbill);
		} else {
			// existbill.setId(existbill.getId());
			// bill.setProductno(existbill.getProductno());
			// bill.setProductname(existbill.getProductname());
			// bill.setPrice(existbill.getPrice());
			double price = existbill.getPrice();
			double discount1 = Double.parseDouble(discount);
			double addsaleprice = (price * discount1*quantity) / 100;			
			double newdiscount = (existbill.getSaleprice() + addsaleprice)
					/ (price * (existbill.getQuantity() + quantity));
			existbill.setSaleprice(existbill.getSaleprice() + addsaleprice);
			existbill.setDiscount(newdiscount * 100);
			existbill.setQuantity(existbill.getQuantity() + quantity);
			billDao.update(existbill);
		}

	}

	@Override
	public String getCustomerno(String customerno, String customername,
			String telephone, String address) {
		// TODO Auto-generated method stub
		String hql =null;
		if(customername==null&&telephone==null&&address==null){
			hql = "from Customer c where c.customerno='" + customerno + "'";
		}else{
			hql = "from Customer c where c.telephone='"+telephone+"' and c.customername='"+customername+"'";
		}
		System.out.println("============================"+customerDao.toString());
		Customer customer = customerDao.get(hql);
		if (customer == null) {
			String createCustomerno = createNoUtils
					.createCustomerno(serialnumberService.getCustomernumber());
			customer = new Customer();
			customer.setCustomerno(createCustomerno);
			if(customername!=null){
				customer.setCustomername(customername);
			}
			if(telephone!=null){
				customer.setTelephone(telephone);
			}
			if(address!=null){
				customer.setAddress(address);
			}
			customer.setCustomerclass("散客");
			customerDao.save(customer);
			return createCustomerno;
		} else {
			return customer.getCustomerno();
		}

	}

	@Override
	public String saveSaleorder(String customer_no, int paystate,
			String seler_name) {
		// TODO Auto-generated method stub
		String orderno = createNoUtils.createOrderno(serialnumberService
				.getSaleordernumber());
		Saleorder saleorder = new Saleorder();
		saleorder.setOrderno(orderno);
		saleorder.setCustomer_no(customer_no);
		Date date = new Date();
		Timestamp ordertime = new Timestamp(date.getTime());
		saleorder.setOrdertime(ordertime);
		System.out.println("paystate===============" + paystate);
//		if (paystate == 1) {
//			saleorder.setState(1);
//		} else {
//			saleorder.setState(0);
//		}
		saleorder.setState(paystate);
		if (seler_name != null && !seler_name.isEmpty()) {
			saleorder.setSeller_name(seler_name);
		} else {
			saleorder.setSeller_name("无名");
		}
		saleorderDao.save(saleorder);
		return orderno;
	}

	@Override
	public void saveOrderdetailAndUpdateStock(List<Bill> bills,
			String neworderno) {
		// TODO Auto-generated method stub
		Product newproduct = new Product();
		String hql = null;
		for (Bill bill : bills) {
			Orderdetail orderdetail = new Orderdetail();
			orderdetail.setOrder_no(neworderno);

			orderdetail.setProduct_no(bill.getProductno());
			orderdetail.setProductname(bill.getProductname());
			;
			orderdetail.setPrice(bill.getPrice());
			orderdetail.setDiscount(bill.getDiscount());
			orderdetail.setSaleprice(bill.getSaleprice());
			orderdetail.setQuantity(bill.getQuantity());
			// 销售时间
			Date orderdetaildate = new Date();
			Timestamp timestamp = new Timestamp(orderdetaildate.getTime());
			Date date = new Date(timestamp.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			String saletime = sdf.format(date); 
			orderdetail.setSaletime(saletime);
			
			orderdetailDao.save(orderdetail);
			// 更新库存
			hql = "from Product p where p.productno='" + bill.getProductno()
					+ "'";
			newproduct = productDao.get(hql);
			newproduct.setStockquantity(newproduct.getStockquantity()
					- bill.getQuantity());
			productDao.update(newproduct);
		}
		this.deleteAllBills();

	}

	@Override
	public void savePayment(String order_no, double amount, String payee_name) {
		// TODO Auto-generated method stub
		String paymentno = createNoUtils.createPaymentno(serialnumberService
				.getPaymentnumber());
		Payment payment = new Payment();
		payment.setPaymentno(paymentno);
		payment.setOrder_no(order_no);
		payment.setAmount(amount);
		payment.setPayee_name(payee_name);
		Date paytime = new Date();
		Timestamp timestamp = new Timestamp(paytime.getTime());
		payment.setPaytime(timestamp);
		payment.setPaymentmethod("现金支付");	
		paymentDao.save(payment);
	}

	@Override
	public List<Orderdetail> hangcreditcheck(String hangcustomername,
			String hangtelephone) {
		// TODO Auto-generated method stub
		List<Orderdetail> list = new ArrayList<Orderdetail>();
		String   customerhql = "from Customer c where c.customername='"+hangcustomername+"' and c.telephone='"+hangtelephone+"'";
		Customer customer = customerDao.get(customerhql);
		if(customer==null){
			System.out.println("============"+hangcustomername+" =====================    "+hangtelephone);
			return list;
		}
		String   saleorderhql = "from Saleorder s where s.customer_no='"+customer.getCustomerno()+"' and s.state=0";
		List<Saleorder> saleorderlist = saleorderDao.find(saleorderhql);
		
		String orderdetailhql = null;
		List<Orderdetail> list2 = new ArrayList<Orderdetail>();
		for(int i=0;i<saleorderlist.size();i++){
			orderdetailhql="from Orderdetail o where o.order_no='"+saleorderlist.get(i).getOrderno()+"'";
			list2 = orderdetailDao.find(orderdetailhql);
			list.addAll(list2);
			list2=null;
		}
		
		return list;
	}

	@Override
	public void hangPay(String hangcustomername, String hangtelephone,
			String seler_name) {
		// TODO Auto-generated method stub
		String   customerhql = "from Customer c where c.customername='"+hangcustomername+"' and c.telephone='"+hangtelephone+"'";
		Customer customer = customerDao.get(customerhql);
		if(customer==null){
			System.out.println(""+hangcustomername+"     "+hangtelephone);
		}
		String   saleorderhql = "from Saleorder s where s.customer_no='"+customer.getCustomerno()+"' and s.state=0";
		List<Saleorder> saleorderlist = saleorderDao.find(saleorderhql);
		String orderdetailhql = null;
		List<Orderdetail> list2 = new ArrayList<Orderdetail>();
		for(int i=0;i<saleorderlist.size();i++){
			orderdetailhql="from Orderdetail o where o.order_no='"+saleorderlist.get(i).getOrderno()+"'";
			list2 = orderdetailDao.find(orderdetailhql);
			double amount = 0;
			for(int j=0;j<list2.size();j++){
				amount += list2.get(j).getSaleprice();
			}
			saleorderlist.get(i).setState(1);
			saleorderDao.update(saleorderlist.get(i));
			this.savePayment(saleorderlist.get(i).getOrderno(), amount, seler_name);
		}
	}

	
}
