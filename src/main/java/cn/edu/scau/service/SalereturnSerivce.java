package cn.edu.scau.service;

import java.util.List;

import cn.edu.scau.vo.Orderdetail;


public interface SalereturnSerivce {

	public Orderdetail getCanreturnitem(String order_no, String product_no);

	public double getSalereturnmoney(String order_no, String product_no,
			int returnquantity);

	public void updateSalereturnAndSalereturndetail(String order_no,
			String product_no, int returnquantity,String returnpeople_name,String returnreason);

}
