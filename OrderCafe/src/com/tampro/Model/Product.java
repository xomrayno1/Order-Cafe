package com.tampro.Model;

import java.io.Serializable;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double price;
	private int active;
	private Date createDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", active=" + active + ", createDate="
				+ createDate + "]";
	}
	
	
	public void getProduct() {
		
		NumberFormat numberFormat  = NumberFormat.getCurrencyInstance(new Locale("nv","VN"));
		System.out.println("[Id]"+" "+this.id+"  "+"[Tên sản phẩm] :"+this.getName()+"   "+"[Giá Tiền]  :"+numberFormat.format(this.price));
		
	}
	public String getProductValues() {
		NumberFormat numberFormat  = NumberFormat.getCurrencyInstance(new Locale("nv","VN"));
		return "Id"+" "+this.id+" "+"Tên sản phẩm :"+this.getName()+"  "+"Giá Tiền  :"+numberFormat.format(this.price);
	}

}
