package cn.edu.scau.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfree.chart.labels.IntervalCategoryItemLabelGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.dao.OrderdetailDaoImpl;
import cn.edu.scau.dao.ProductDaoImpl;
import cn.edu.scau.dao.ProducttypeDaoImpl;
import cn.edu.scau.dao.SalereturndetailDaoImpl;
import cn.edu.scau.model.MonthCountModel;
import cn.edu.scau.model.ProducttypeCountModel;
import cn.edu.scau.service.SaleReportService;
import cn.edu.scau.vo.Orderdetail;
import cn.edu.scau.vo.Producttype;
import cn.edu.scau.vo.Salereturndetail;


/**
 * @author wxj
 *
 */
@Service("saleReportService")
@Transactional
public class SaleReportServiceImpl implements SaleReportService {

	@Resource(name="orderdetailDao")
	OrderdetailDaoImpl orderdetailDao;
	@Resource(name="salereturndetailDao")
	SalereturndetailDaoImpl salereturndetailDao;
	@Resource(name="producttypeDao")
	ProducttypeDaoImpl producttypeDao;
	@Resource(name="productDao")
	ProductDaoImpl productDao;
	@Override
	public List<MonthCountModel> getSaleMonthCountList(String year) {
		// TODO Auto-generated method stub
		List<MonthCountModel> monthcountlist = new ArrayList<MonthCountModel>();
		int month = 1;
		//从订单明细中取出已经销售的每个月的总金额
		String hqlorderdetail;
		//从退货中取出已退货的对应月总金额，已售减去已退
		String hqlsalereturndetail;
		List<Orderdetail> listOrderdetail = new ArrayList<Orderdetail>();
		List<Salereturndetail> listSalereturndetail = new ArrayList<Salereturndetail>();
		MonthCountModel tempMonthCount ;
		try{
			while(month<=12){
			
			//hqlorderdetail = "from Orderdetail o where o.saletime between '"+year+"-"+month+"-1"+"' and '"+year+"-"+(month+1)+"-1"+"'";
			hqlorderdetail = "from Orderdetail o where o.saletime>='"+year+"-"+String.format("%02d", month)+"-1"+"' and o.saletime<'"+year+"-"+String.format("%02d", (month+1))+"-1"+"'";
			hqlsalereturndetail = "from Salereturndetail s where s.returntime between '"+year+"-"+month+"-1"+"' and '"+year+"-"+(month+1)+"-1"+"'";
		    System.out.println("月销售报表service\n hqlorderdetail=:"+hqlorderdetail+"\n hqlsalereturndetail="+hqlsalereturndetail);
		    listOrderdetail  = orderdetailDao.find(hqlorderdetail);
		    listSalereturndetail = salereturndetailDao.find(hqlsalereturndetail);
		    double monthmoneycount=0;
		    if(listOrderdetail.size()!=0){
		    	for(Orderdetail o:listOrderdetail){
		    		monthmoneycount += o.getSaleprice();
		    	}
		    }
		    if(listSalereturndetail.size()!=0){
		    	for(Salereturndetail s:listSalereturndetail){
		    		monthmoneycount -= s.getReturnmoney();
		    	}
		    }
		    String monthStr = month+"月";
		    BigDecimal   b   =   new   BigDecimal(monthmoneycount);  
		    monthmoneycount   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		   tempMonthCount = new MonthCountModel(monthStr,monthmoneycount);
		    monthcountlist.add(tempMonthCount);
		    tempMonthCount=null;
		    month++;
		    
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		MonthCount arg0 = new MonthCount();
//		arg0.setMonth("1月");
//		arg0.setMonthsalemoneycount(100005.50);
//		MonthCount arg1 = new MonthCount();
//		arg1.setMonth("2月");
//		arg1.setMonthsalemoneycount(100523423.50);
//		monthcountlist.add(arg0);	
//		monthcountlist.add(arg1);	
		return monthcountlist;
	}
	@Override
	public List<ProducttypeCountModel> getProducttypeCountList(
			String firstDate, String lastDate) {
		List<ProducttypeCountModel> producttypecountlist = new ArrayList<ProducttypeCountModel>();
		String hqlorderdetail;//select * from orderdetail where product_no IN (select productname from product where producttype_id=1);
	    String hqlsalereturndetail;
	    ProducttypeCountModel tempProducttypeMoneyCount;
	    List<Producttype> listproducttype = producttypeDao.findAll();
		for(Producttype p:listproducttype){
			//嵌套查询
			hqlorderdetail = "from Orderdetail o where o.saletime>='"+firstDate+"' and o.saletime<'"+lastDate+"' and o.product_no in (select productno from Product where producttype_id="+p.getId()+")";
			hqlsalereturndetail = "from Salereturndetail s where s.returntime>='"+firstDate+"' and s.returntime<'"+lastDate+"' and s.product_no in (select productno from Product where producttype_id="+p.getId()+")";
			List<Orderdetail> listorderdetail = orderdetailDao.find(hqlorderdetail);
			List<Salereturndetail> listsalereturndetail = salereturndetailDao.find(hqlsalereturndetail);
			double producttypemoneycount=0;
		    if(listorderdetail.size()!=0){
		    	for(Orderdetail o:listorderdetail){
		    		producttypemoneycount += o.getSaleprice();
		    	}
		    }
		    if(listsalereturndetail.size()!=0){
		    	for(Salereturndetail s:listsalereturndetail){
		    		producttypemoneycount -= s.getReturnmoney();
		    	}
		    }
		    //只保留两位小数		    
		    BigDecimal   b   =   new   BigDecimal(producttypemoneycount);  
		    producttypemoneycount   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		    tempProducttypeMoneyCount = new ProducttypeCountModel(p.getProducttypename(),producttypemoneycount);
		    producttypecountlist.add(tempProducttypeMoneyCount);
			tempProducttypeMoneyCount=null;
			System.out.println("orderdetail.size()="+listorderdetail.size());	
			System.out.println("salereturndetail.size()="+listsalereturndetail.size());
		}
	    return producttypecountlist;
	}

}
