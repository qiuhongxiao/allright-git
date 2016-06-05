package cn.edu.scau.vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "producttype")
public class Producttype implements java.io.Serializable {

	private int id;

	private String producttypename;

	private String description;

	private Set<Product> products = new HashSet<Product>();

	public Producttype() {
		super();
	}
	
	
	
	
	
	public Producttype(int id, String producttypename, String description, Set<Product> products) {
		super();
		this.id = id;
		this.producttypename = producttypename;
		this.description = description;
		this.products = products;
	}





	@Id
	@Column(name = "id", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "producttypename", length = 36, nullable = false, unique = true)
	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 只有OneToOne,OneToMany,ManyToMany有mappedBy属性，ManyToOne是没有这个属性的
	 * mappedBy的值为维护端所声明的被维护端类型的变量名称 cascade=CascadeType.ALL需要两边都设置，
	 * 是单向的，不是双向的，意思就是说，我们在设置两个类的对象之间关系的时候，总是在一方设置的很具体， 在另外一方设置一个mappedBy即可，
	 * 但是如果想要两边都能删除的时候，或者在生成的时候，必须在两边都设置cascade=CascadeType.All才有效果
	 * fetch有两个属性EAGER和LAZY，分别是马上加载和延迟加载
	 * 
	 * @return
	 */
	// fetch=FetchType.LAZY,
	@OneToMany(mappedBy = "producttype", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
