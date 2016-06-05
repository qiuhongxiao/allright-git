package cn.edu.scau.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.OrderdetailDaoImpl;
import cn.edu.scau.dao.SalereturnDaoImpl;
import cn.edu.scau.dao.SalereturndetailDaoImpl;
import cn.edu.scau.service.SalereturnSerivce;
import cn.edu.scau.service.SerialnumberService;
import cn.edu.scau.util.createNoUtils;
import cn.edu.scau.vo.Orderdetail;
import cn.edu.scau.vo.Salereturn;
import cn.edu.scau.vo.Salereturndetail;


/**
 * @author wxj
 *
 */
@Service("salereturnService")
@Transactional
public class SalereturnServiceImpl implements SalereturnSerivce {
	@Resource(name = "orderdetailDao")
	private OrderdetailDaoImpl orderdetailDao;
	@Resource(name = "salereturndetailDao")
	private SalereturndetailDaoImpl salereturndetailDao;
	@Resource(name = "salereturnDao")
	private SalereturnDaoImpl salereturnDao;
	@Resource(name = "serialnumberService")
	private SerialnumberService serialnumberService;

	@Override
	public Orderdetail getCanreturnitem(String order_no, String product_no) {
		String sql = "from Orderdetail o where o.order_no='" + order_no
				+ "' and o.product_no='" + product_no + "'";
		Orderdetail item = new Orderdetail();
		Orderdetail item1 = new Orderdetail();
		item1 = orderdetailDao.get(sql);
		
		// 是不是已经退过货了呢，如果退了一部分，那么就要减去已经退了的
		String sql2 = "from Salereturndetail s where s.order_no='" + order_no
				+ "' and s.product_no='" + product_no + "'";
		List<Salereturndetail> items2 = new ArrayList<Salereturndetail>();
		items2 = salereturndetailDao.find(sql2);
		int havedreturnquantity = 0;
		double havedreturnmoney = 0;
		if (items2.size() != 0) {
			for (Salereturndetail item2 : items2) {
				havedreturnquantity += item2.getReturnquantity();
				havedreturnmoney += item2.getReturnmoney();
			}
		}
		if (item1 != null) {
			item.setDiscount(item1.getDiscount());
			item.setOrder_no(item1.getOrder_no());
			item.setPrice(item1.getPrice());
			item.setProduct_no(item1.getProduct_no());
			item.setProductname(item1.getProductname());
			item.setSaletime(item1.getSaletime());
//			String[] str = item1.getSaletime().split("-");
//			int year = Integer.parseInt(str[0]);
//			int month = Integer.parseInt(str[1]);
//			System.out.println("year="+year+";month="+month);
			item.setQuantity(item1.getQuantity() - havedreturnquantity);
			item.setSaleprice(item1.getSaleprice() - havedreturnmoney);
		}else{
			return null;
		}
		return item;
	}

	@Override
	public double getSalereturnmoney(String order_no, String product_no,
			int returnquantity) {
		Orderdetail item = getCanreturnitem(order_no, product_no);
		double money = 0;
		if (item != null) {
			money = (item.getSaleprice() / item.getQuantity()) * returnquantity;
		}
		return money;
	}

	@Override
	public void updateSalereturnAndSalereturndetail(String order_no,
			String product_no, int returnquantity, String returnpeople_name,
			String returnreason) {
		try {
			Salereturn salereturn = new Salereturn();
			Salereturndetail salereturndetail = new Salereturndetail();
			Orderdetail item = getCanreturnitem(order_no, product_no);
			// 生成退货单
			salereturn.setOrder_no(order_no);
			String salereturn_no = createNoUtils
					.createSalereturnno(serialnumberService
							.getSalereturnnumber());
			salereturn.setSalereturnno(salereturn_no);
			salereturn.setReturnpeople_name(returnpeople_name);
			salereturn.setReturnreason(returnreason);
			Date date = new Date();
			Timestamp returntime = new Timestamp(date.getTime());
			salereturn.setReturntime(returntime);
			salereturnDao.save(salereturn);
			// 生成退货明细单
			salereturndetail.setOrder_no(order_no);
			salereturndetail.setPrice(item.getPrice());
			salereturndetail.setProduct_no(product_no);
			salereturndetail.setProductname(item.getProductname());
			salereturndetail.setReturnmoney(getSalereturnmoney(order_no,
					product_no, returnquantity));
			salereturndetail.setReturnquantity(returnquantity);
			salereturndetail.setReturntime(returntime);
			salereturndetail.setSalereturn_no(salereturn_no);
			salereturndetailDao.save(salereturndetail);
			System.out.println("service层的updatesalereturnandsalereturndetail被调用:"+salereturn_no);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
