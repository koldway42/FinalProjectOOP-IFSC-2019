package project.restaurant.model;

import java.sql.Date;

public class OrderPad {
	private Integer id;
	private Boolean paid = false;
	private Double total;
	private Date createdAt;
	
	
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
	
	
}
