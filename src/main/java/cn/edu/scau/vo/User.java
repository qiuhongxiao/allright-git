package cn.edu.scau.vo;

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
    
	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserrole_rolename() {
		return userrole_rolename;
	}

	public void setUserrole_rolename(String userrole_rolename) {
		this.userrole_rolename = userrole_rolename;
	}
	
	
	
	
	

}
