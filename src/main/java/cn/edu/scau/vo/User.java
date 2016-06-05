package cn.edu.scau.vo;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="user")
public class User implements java.io.Serializable{
	
	private int id;
	
	private String username;
	
	private String password;
	
	private String userrole_rolename;
	
	public User(){};
	public User(int id,String username,String password,String userrole_rolename){
		this.id = id;
		this.username = username;
		this.password = password;
		this.userrole_rolename = userrole_rolename;
	}
    
	@Id
	@Column(name="id",nullable=false,unique=true,length=11)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
    @Column(name="username",unique=true,nullable=false,length=36)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    @Column(name="password",nullable=false,length=100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="userrole_rolename",nullable=false,unique=false,length=36)
	public String getUserrole_rolename() {
		return userrole_rolename;
	}

	public void setUserrole_rolename(String userrole_rolename) {
		this.userrole_rolename = userrole_rolename;
	}
	
	
	
	
	

}
