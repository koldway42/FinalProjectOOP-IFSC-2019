package project.restaurant.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {
	@Id @GeneratedValue
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "created_ad", nullable = false)
	private Date createdAt;
	
	@OneToMany(mappedBy = "user", 
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	private Set<OrderPad> orderPads;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Set<OrderPad> getOrderPads() {
		return orderPads;
	}
	public void setOrderPads(Set<OrderPad> orderPads) {
		this.orderPads = orderPads;
	}
	
}
