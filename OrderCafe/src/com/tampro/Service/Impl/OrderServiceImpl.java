package com.tampro.Service.Impl;

import java.math.BigDecimal;
import java.sql.Date;

import com.tampro.Dao.OrderRepository;
import com.tampro.Dao.Impl.OrderRepositoryImpl;
import com.tampro.Model.Order;
import com.tampro.Service.OrderService;

public class OrderServiceImpl  implements OrderService{
	
	OrderRepository  orderRepo = new OrderRepositoryImpl();

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepo.addOrder(order);
	}

	@Override
	public Order getOrder() {
		// TODO Auto-generated method stub
		return orderRepo.getOrder();
	}

	@Override
	public BigDecimal TotalPrice(Date date) {
		// TODO Auto-generated method stub
		return orderRepo.TotalPrice(date);
	}

	@Override
	public BigDecimal TotalPriceByMonth(int month, int years) {
		// TODO Auto-generated method stub
		return orderRepo.TotalPriceByMonth(month, years);
	}

}
