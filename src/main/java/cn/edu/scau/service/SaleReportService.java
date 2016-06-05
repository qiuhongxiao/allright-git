package cn.edu.scau.service;

import java.util.List;

import cn.edu.scau.model.MonthCountModel;
import cn.edu.scau.model.ProducttypeCountModel;


public interface SaleReportService {

	List<MonthCountModel> getSaleMonthCountList(String year);

	List<ProducttypeCountModel> getProducttypeCountList(String firstDate,
			String lastDate);

}
