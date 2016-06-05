package cn.edu.scau.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 创建时间：2015年5月26日 下午9:32:25
 * 项目名称：Post
 * @author pnz
 * @version 1.0
 * 类说明：
 */

@Entity
@Table(name="userrole")
public class Userrole implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="id",length=11,nullable = false,unique=true)
	private int id;
	
	@Column(name="rolename",nullable=false,unique=true,length=36)
	private String rolename;
	
	@Column(name="rolecreatetime",nullable=false,unique=true)
	private Timestamp rolecreatetime;
	
	@Column(name="rolecreator",nullable=false,unique=false,length=36)
	private String rolecreator;
	
	@Column(name="reportmanage",nullable=false,unique=false,length=2)
	private String reportmanage;
	
	@Column(name="basedatamanage",nullable=false,unique=false,length=2)
	private String basedatamanage;
	
	@Column(name="salereturn",nullable=false,unique=false,length=2)
	private String salereturn;
	
	@Column(name="hangcredit",nullable=false,unique=false,length=2)
	private String hangcredit;
	
	@Column(name="hangpay",nullable=false,unique=false,length=2)
	private String hangpay;
	
	@Column(name="permissionmanage",nullable=false,unique=false,length=2)
	private String permissionmanage;
	
	@Column(name="sale",nullable=false,unique=false,length=2)
	private String sale;
	
	
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
