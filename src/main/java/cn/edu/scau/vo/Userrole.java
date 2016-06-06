package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Userrole implements Serializable {
	private int id;
	
	private String rolename;
	
	private Timestamp rolecreatetime;
	
	private String rolecreator;
	
	private String grid;
	
	private String edit;
	
	private String reportmanage;
	
	private String basedatamanage;
	
	private String salereturn;
	
	private String hangcredit;
	
	private String hangpay;
	
	private String permissionmanage;
	
	private String sale;
	
	
	
	
	public String getGrid() {
		return grid;
	}
	public void setGrid(String grid) {
		this.grid = grid;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Timestamp getRolecreatetime() {
		return rolecreatetime;
	}
	public void setRolecreatetime(Timestamp rolecreatetime) {
		this.rolecreatetime = rolecreatetime;
	}
	public String getRolecreator() {
		return rolecreator;
	}
	public void setRolecreator(String rolecreator) {
		this.rolecreator = rolecreator;
	}
	public String getReportmanage() {
		return reportmanage;
	}
	public void setReportmanage(String reportmanage) {
		this.reportmanage = reportmanage;
	}
	public String getBasedatamanage() {
		return basedatamanage;
	}
	public void setBasedatamanage(String basedatamanage) {
		this.basedatamanage = basedatamanage;
	}
	public String getSalereturn() {
		return salereturn;
	}
	public void setSalereturn(String salereturn) {
		this.salereturn = salereturn;
	}
	public String getHangcredit() {
		return hangcredit;
	}
	public void setHangcredit(String hangcredit) {
		this.hangcredit = hangcredit;
	}
	public String getHangpay() {
		return hangpay;
	}
	public void setHangpay(String hangpay) {
		this.hangpay = hangpay;
	}
	public String getPermissionmanage() {
		return permissionmanage;
	}
	public void setPermissionmanage(String permissionmanage) {
		this.permissionmanage = permissionmanage;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
