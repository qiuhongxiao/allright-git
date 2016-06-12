package cn.edu.scau.service;
import java.util.List;

import cn.edu.scau.vo.Bill;
import cn.edu.scau.vo.Orderdetail;
import cn.edu.scau.vo.Product;
import cn.edu.scau.vo.Saleorder;
public interface SaleService {
	public List<Bill> getAllBill();

	public int getTotal();

	public boolean saleItemRemove(int id);

	public void saveBill(String productno,String discount,double quantity) throws Exception;

	public Bill getBill(String productno);

	public Product getProduct(String productno);

	public void updateBill(Bill bill);

	public void deleteAllBills();

	//public void saveSaleorder(Saleorder saleorder);

	public void saveOrderdetail(Orderdetail orderdetail);

	public String getCustomerno(String customerno, String customername, String telephone, String address );

	public String saveSaleorder(String customer_no, int paystate,
			String seler_name);

	public void saveOrderdetailAndUpdateStock(List<Bill> bills,
			String neworderno);
	public void savePayment(String order_no,double amount,String payee_name);

	public List<Orderdetail> hangcreditcheck(String hangcustomername,
			String hangtelephone);

	public void hangPay(String hangcustomername, String hangtelephone,
			String seler_name);

	

	
	
}
