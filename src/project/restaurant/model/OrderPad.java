package project.restaurant.model;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderpad")
public class OrderPad {
	
	@Id @GeneratedValue
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "paid", nullable = false, unique = false)
	private Boolean paid = false;
	
	@Column(name = "total", nullable = false, unique = false)
	private Double total;
	
	@Column(name = "created_at", nullable = false, unique = false)
	private Date createdAt;
	
    @ManyToOne
	@JoinColumn(name="user_id")
	private User user;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFormattedCreatedAt() {
		DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return createdAt.toLocalDate().format(formattedDate).toString();
	}
	
	
}
