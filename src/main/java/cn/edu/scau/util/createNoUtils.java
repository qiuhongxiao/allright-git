package cn.edu.scau.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class createNoUtils {
	
	public static String createCustomerno(String number) {

		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		Timestamp nowdate = new Timestamp(System.currentTimeMillis());
		String datestr = sdf.format(nowdate);		
		String customerno = "CUSTOMER-" + datestr.split("-")[0]
				+ datestr.split("-")[1] + datestr.split("-")[2] + "-"
				+ number;

		return customerno;
	}
	public static String createOrderno(String number) {

		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		Timestamp nowdate = new Timestamp(System.currentTimeMillis());
		String datestr = sdf.format(nowdate);		
		String orderno = "ORDER-" + datestr.split("-")[0]
				+ datestr.split("-")[1] + datestr.split("-")[2] + "-"
				+ number;

		return orderno;
	}
	public static String createPaymentno(String number) {

		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		Timestamp nowdate = new Timestamp(System.currentTimeMillis());
		String datestr = sdf.format(nowdate);		
		String paymentno = "PAYMENT-" + datestr.split("-")[0]
				+ datestr.split("-")[1] + datestr.split("-")[2] + "-"
				+ number;

		return paymentno;
	}
	public static String createSalereturnno(String number) {

		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		Timestamp nowdate = new Timestamp(System.currentTimeMillis());
		String datestr = sdf.format(nowdate);		
		String salereturnno = "SALERETURN-" + datestr.split("-")[0]
				+ datestr.split("-")[1] + datestr.split("-")[2] + "-"
				+ number;

		return salereturnno;
	}

}
