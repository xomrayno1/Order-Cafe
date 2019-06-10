package com.tampro.Dao;

import java.math.BigDecimal;
import java.sql.Date;

import com.tampro.Model.Order;

public interface OrderRepository {
	
	public int addOrder(Order order);
	
	public Order getOrder();
	
	public BigDecimal TotalPrice(Date date);
	
	public BigDecimal TotalPriceByMonth(int month,int years);
	
	
	

}
