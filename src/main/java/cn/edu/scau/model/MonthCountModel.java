package cn.edu.scau.model;

/**
 * @author wxj
 *
 */
public class MonthCountModel implements java.io.Serializable {

	private String month;
	private double monthcount;
	
	public MonthCountModel() {
		super();
	}
	public MonthCountModel(String month, double monthcount) {
		super();
		this.month = month;
		this.monthcount = monthcount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getMonthcount() {
		return monthcount;
	}
	public void setMonthcount(double monthcount) {
		this.monthcount = monthcount;
	}
	
}
