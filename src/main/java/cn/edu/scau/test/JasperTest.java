package cn.edu.scau.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import cn.edu.scau.model.MonthCountModel;
import cn.edu.scau.service.SaleReportService;
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
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperTest {
	private static final long serialVersionUID = 1L;	
    @Resource(name="saleReportService")
    private SaleReportService saleReportService;
    private String year="2015";
	
	@Test
	public void testjasper() {
		String path = "/salemonthcountreport.jrxml";
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(path);
			// + "/salemonthcountreport.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

			List<MonthCountModel> listMonthCount = saleReportService.getSaleMonthCountList(year);
			JRBeanCollectionDataSource dataBeanSource = new JRBeanCollectionDataSource(listMonthCount, false);// false代表空忽略description，不然就会报错
			Map paras = new HashMap();
			paras.put("ReportTitle", year + "年度各月销售额统计");
			// paras.put("Year", "2014年");
			JasperPrint print = JasperFillManager.fillReport(jasperReport, paras, dataBeanSource);
			JRAbstractExporter expoter = new JRHtmlExporter();
			expoter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			expoter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			expoter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
