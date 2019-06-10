package com.tampro.Model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Order {
	private int id;
	private List<CartItem> listCartItem;
	private BigDecimal totalPrice;
	private Date createDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<CartItem> getListCartItem() {
		return listCartItem;
	}
	public void setListCartItem(List<CartItem> listCartItem) {
		this.listCartItem = listCartItem;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	

}
