package com.tampro.Service;

import java.math.BigDecimal;
import java.sql.Date;

import com.tampro.Model.Order;

public interface OrderService {
	
    public int addOrder(Order order);
	
	public Order getOrder();
	
	public BigDecimal TotalPrice(Date date);
	public BigDecimal TotalPriceByMonth(int month,int years);

}
