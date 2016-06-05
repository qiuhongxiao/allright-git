package cn.edu.scau.controller;

import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.scau.model.MonthCountModel;
import cn.edu.scau.model.ProducttypeCountModel;
import cn.edu.scau.service.SaleReportService;




 
/**
 * @author wxj
 *
 */
@Controller
public class ReportController {
	private static final long serialVersionUID = 1L;	
    @Resource(name="saleReportService")
    private SaleReportService saleReportService;
    //起止时间产品类别销售额
   
	/**
	 * 产品数字报表
	 * @param year
	 * @param request
	 * @param session
	 * @param response
	 */
    @RequestMapping(value = "salemonthcountreport")
	public void salemonthcountreport(String year,HttpServletRequest request,HttpSession session,HttpServletResponse response) {
    	//String path=request.getServletPath();
    	//System.out.println(path);
    	//String path = ServletActionContext.getRequest().getRealPath("/");

		//ServletActionContext.getResponse().setCharacterEncoding("utf-8");
    	if(year!=null){
    	response.setCharacterEncoding("utf-8");
    String temp=request.getSession().getServletContext().getRealPath("/");
    	String path=session.getServletContext().getRealPath("/");
		try {			
			JasperDesign jasperDesign = JRXmlLoader.load(path+"/views/salemonthcountreport.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			List<MonthCountModel> listMonthCount = saleReportService.getSaleMonthCountList(year);
			JRBeanCollectionDataSource dataBeanSource = new JRBeanCollectionDataSource(listMonthCount, false);// false代表空忽略description，不然就会报错
			Map paras=new HashMap();
			paras.put("ReportTitle", year+"年度各月销售额统计");
			//paras.put("Year", "2014年");
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					paras, dataBeanSource);			
			JRAbstractExporter expoter = new JRHtmlExporter();
			expoter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//			try {
//				expoter.setParameter(JRExporterParameter.OUTPUT_WRITER,
//						ServletActionContext.getResponse().getWriter());
//			} catch (IOException e) {				
//				e.printStackTrace();
//			}
			try {
				expoter.setParameter(JRExporterParameter.OUTPUT_WRITER,
						response.getWriter());
			} catch (IOException e) {				
				e.printStackTrace();
			}
			expoter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);
			expoter.exportReport();
		} catch (JRException e) {			
			e.printStackTrace();
		}
    	}
	}
    
    
    /**
     * 类型数字报表
     * @param firstDate
     * @param lastDate
     * @param session
     * @param response
     */
	
    @RequestMapping(value = "producttypereport")
//			, results = {
//			@Result(name = "customergird", location = "/producttypereport.jsp"),
//			@Result(name = "error", location = "/error.jsp") },interceptorRefs = { @InterceptorRef(value = "myinterceptor") })
	public void producttypereport(String firstDate,String lastDate,HttpSession session,HttpServletResponse response) {
        if(firstDate!=null&&lastDate!=null){
        	//当前时间
//        	Date date = new Date();
//        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
//        	String defaultdate = sdf.format(date); 
//        	lastDate = defaultdate;
//        	//前一天
//        	Calendar calendar = Calendar.getInstance();
//        	calendar.add(Calendar.DAY_OF_MONTH, -30);
//        	String tempdate = sdf.format(calendar.getTime());
//        	firstDate = defaultdate;
        	
        
		System.out.println("firstDate="+firstDate+"\nlastDate="+lastDate);
		
		String path = session.getServletContext().getRealPath("/");

		response.setCharacterEncoding("utf-8");
		try {			
			JasperDesign jasperDesign = JRXmlLoader.load(path
					+ "/views/producttypereport.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			
			List<ProducttypeCountModel> listProducttypeCount = saleReportService.getProducttypeCountList(firstDate,lastDate);
			JRBeanCollectionDataSource dataBeanSource = new JRBeanCollectionDataSource(
					listProducttypeCount, false);// false代表空忽略description，不然就会报错
			Map paras=new HashMap();
			paras.put("reportTitle", firstDate+"到"+lastDate+"产品类别销售统计");
			
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					paras, dataBeanSource);			
			JRAbstractExporter expoter = new JRHtmlExporter();
			expoter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			try {
				expoter.setParameter(JRExporterParameter.OUTPUT_WRITER,
						response.getWriter());
			} catch (IOException e) {				
				e.printStackTrace();
			}
			expoter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);
			expoter.exportReport();
		} catch (JRException e) {			
			e.printStackTrace();
		}
        }
        
	}
	
	
    @RequestMapping(value = "salemonthcountreportPie")
	public void salemonthcountreportPie(String year,HttpServletRequest request,HttpSession session,HttpServletResponse resp) throws IOException{
		if(year!=null){
    	try{
		resp.setCharacterEncoding("UTF-8");		
		//创建一个文件输出流，指定输出到C盘根目录下 
		OutputStream fos = resp.getOutputStream();		
		resp.setContentType("image/jpeg"); 
		PieDataset dataset = getDataSet(year); 
		//创建主题样式  
		   StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
		   //设置标题字体  
		   standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
		   //设置图例的字体  
		   standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
		   //设置轴向的字体  
		   standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
		   //应用主题样式  
		   ChartFactory.setChartTheme(standardChartTheme); 
		JFreeChart chart = ChartFactory.createPieChart3D(year+"年度各月销售额比例（饼图）", // chart title 
				dataset,// data 
				true, // include legend 
				true, false ); //设置图表属性 // 输出图片		      
				ChartUtilities.writeChartAsJPEG(fos,1f,chart,700,400,null);
				//刷新
				fos.flush();
				//手动关闭流
				fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}
    
    
    
    
	private PieDataset getDataSet(String year) {
		List<MonthCountModel> listMonthCount = saleReportService.getSaleMonthCountList(year);
		DefaultPieDataset dataset = new DefaultPieDataset();
		if(listMonthCount.size()!=0){
		   for(int i=0;i<listMonthCount.size();i++){
			   dataset.setValue(listMonthCount.get(i).getMonth(),listMonthCount.get(i).getMonthcount());
		   }
		}else{
			dataset.setValue("没有任何数据", 0);
		}				
		return dataset;
		
	

	}
	
	
	
	
	
	@RequestMapping(value = "producttypereportPie") 
//	, results = {
//			@Result(name = "producttypereportPie", location = "/producttypereport.jsp"),
//			@Result(name = "error", location = "/error.jsp") },interceptorRefs = { @InterceptorRef(value = "myinterceptor") })
	public void producttypereportPie(String firstDate,String lastDate,HttpServletRequest request,HttpSession session,HttpServletResponse resp) throws IOException{
		
		if(firstDate!=null&&lastDate!=null){
		
		try{
		resp.setCharacterEncoding("UTF-8");		
		//创建一个文件输出流，指定输出到C盘根目录下 
		OutputStream fos = resp.getOutputStream();		
		resp.setContentType("image/jpeg"); 
		PieDataset dataset = getDataSet2(firstDate,lastDate); 
		//创建主题样式  
		   StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
		   //设置标题字体  
		   standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
		   //设置图例的字体  
		   standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
		   //设置轴向的字体  
		   standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
		   //应用主题样式  
		   ChartFactory.setChartTheme(standardChartTheme); 
		JFreeChart chart = ChartFactory.createPieChart3D(firstDate+"到"+lastDate+"产品类别销售统计", // chart title 
				dataset,// data 
				true, // include legend 
				true, false ); //设置图表属性 // 输出图片		      
				ChartUtilities.writeChartAsJPEG(fos,1f,chart,700,400,null);
				//刷新
				fos.flush();
				//手动关闭流
				fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
		
		
		}
	}
	
	
	
	
	
	private PieDataset getDataSet2(String firstDate,String lastDate) {
		List<ProducttypeCountModel> listProducttypeCount = saleReportService.getProducttypeCountList(firstDate,lastDate);
		DefaultPieDataset dataset = new DefaultPieDataset();
		if(listProducttypeCount.size()!=0){
		   for(int i=0;i<listProducttypeCount.size();i++){
			   dataset.setValue(listProducttypeCount.get(i).getProducttypename(),listProducttypeCount.get(i).getProducttypesalemoney());
		   }
		}else{
			dataset.setValue("没有任何数据", 0);
		}				
		return dataset;

	}
	
	/*
	//导出excel,有点小问题
	@Action(value = "exportexcel")
	public void exportexcel() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		//创建一个文件输出流，指定输出到C盘根目录下 
		OutputStream fos = resp.getOutputStream();
		String path = ServletActionContext.getRequest().getRealPath("/");

		resp.setCharacterEncoding("utf-8");
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(path
					+ "/salemonthcountreport.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			
			List<MonthCountModel> listMonthCount = saleReportService.getSaleMonthCountList("2015");
			JRBeanCollectionDataSource dataBeanSource = new JRBeanCollectionDataSource(
					listMonthCount, false);// false代表空忽略description，不然就会报错
			Map paras=new HashMap();
			paras.put("ReportTitle", year+"年度各月销售额统计");
			//paras.put("Year", "2014年");
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					paras, dataBeanSource);			
			//JRAbstractExporter expoter = new JRHtmlExporter();
			//OutputStream os = ServletActionContext.getResponse().getOutputStream();
			resp.setHeader("Content-Disposition", "inline; filename=\""+new String("年度月份报表".getBytes("utf-8")) + ".xls"+ "\"");
			resp.setContentType("appliction/x-excel");
			
		    JRAbstractExporter exporter = new JRXlsExporter();
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		    fos.flush();
		    fos.close();
		    // 鍒犻櫎璁板綍鏈�笅闈㈢殑绌鸿
		    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		    exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		    exporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	@Action(value = "exportexcel1")
	public void exportexcel1(){
		
		String path = ServletActionContext.getRequest().getRealPath("/");
		
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");		
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(path
					+ "/salemonthcountreport.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			
			List<MonthCountModel> listMonthCount = saleReportService.getSaleMonthCountList("2015");
			JRBeanCollectionDataSource dataBeanSource = new JRBeanCollectionDataSource(
					listMonthCount, false);// false代表空忽略description，不然就会报错
			Map paras=new HashMap();
			paras.put("ReportTitle", year+"年度各月销售额统计");
			//paras.put("Year", "2014年");
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					paras, dataBeanSource);			
			//JRAbstractExporter expoter = new JRHtmlExporter();
			OutputStream os = ServletActionContext.getResponse().getOutputStream();
			ServletActionContext.getResponse().setHeader("Content-Disposition", "inline; filename=\""+new String("年度月份报表".getBytes("utf-8")) + ".xls"+ "\"");
			ServletActionContext.getResponse().setContentType("appliction/x-excel");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		    JRAbstractExporter exporter = new JRXlsExporter();
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		    // 鍒犻櫎璁板綍鏈�笅闈㈢殑绌鸿
		    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		    exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		    exporter.exportReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	@Action(value = "salecountreport")
	public void salecountreport() {

		String path = ServletActionContext.getRequest().getRealPath("/");

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		try {			
			JasperDesign jasperDesign = JRXmlLoader.load(path
					+ "/report4.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			List<User> list = new ArrayList<User>();
			User user1 = new User(11, "三等奖疯狂点击放大", "a", "a");
			User user2 = new User(2, "b", "b", "b");
			User user3 = new User(1, "c", "c", "c");
			list.add(user1);
			list.add(user2);
			list.add(user3);
			JRBeanCollectionDataSource dataBeanSource = new JRBeanCollectionDataSource(
					list, false);// false代表空忽略description，不然就会报错
			Map paras=new HashMap();
			paras.put("ReportTitle", "年度各月销售额统计");
			paras.put("Year", "2014年");
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					paras, dataBeanSource);			
			JRAbstractExporter expoter = new JRHtmlExporter();
			expoter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			try {
				expoter.setParameter(JRExporterParameter.OUTPUT_WRITER,
						ServletActionContext.getResponse().getWriter());
			} catch (IOException e) {				
				e.printStackTrace();
			}
			expoter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);
			expoter.exportReport();
		} catch (JRException e) {			
			e.printStackTrace();
		}
	}
	@Action(value = "salecountreportTest")
	public void salecountreportTest() {

		String path = ServletActionContext.getRequest().getRealPath("/");

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		try {
			// JasperCompileManager.compileReportToFile("report4.jrxml",
			// "report4.jasper");
			// String sourceFilename = "report4.jasper";
			JasperDesign jasperDesign = JRXmlLoader.load(path
					+ "/report4.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			List<User> list = new ArrayList<User>();
			User user1 = new User(1, "三等奖疯狂点击放大", "a", "a");
			User user2 = new User(2, "b", "b", "b");
			User user3 = new User(3, "c", "c", "c");
			list.add(user1);
			list.add(user2);
			list.add(user3);
			JRBeanCollectionDataSource dataBeanSource = new JRBeanCollectionDataSource(
					list, false);// false代表空忽略description，不然就会报错

			// JasperFillManager.fillReportToFile(sourceFilename, null,
			// dataBeanSource);
			// String file = JasperFillManager.fillReportToFile(jasperReport,
			// null, dataBeanSource);
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					null, dataBeanSource);
			// response.setHeader("Content-Disposition",
			// "inline; filename=aaa.jsp");//+new
			// String("aa".getBytes("utf-8"))+".html"+"\"");
			JRAbstractExporter expoter = new JRHtmlExporter();
			expoter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			try {
				expoter.setParameter(JRExporterParameter.OUTPUT_WRITER,
						ServletActionContext.getResponse().getWriter());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			expoter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);
			expoter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "imagereport.action?image=");
			expoter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
