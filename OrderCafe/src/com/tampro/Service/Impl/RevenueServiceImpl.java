package com.tampro.Service.Impl;

import java.sql.Date;
import java.util.List;

import com.tampro.Dao.RevenueRepository;
import com.tampro.Dao.Impl.RevenueRepositoryImpl;
import com.tampro.Model.Revenue;
import com.tampro.Service.RevenueService;

public class RevenueServiceImpl  implements RevenueService{
	
	RevenueRepository revenueRepository = new RevenueRepositoryImpl();

	@Override
	public void addRevenue(Revenue revenue) {
		// TODO Auto-generated method stub
		revenueRepository.addRevenue(revenue);
	}

	@Override
	public void updateRevenue(Revenue revenue) {
		// TODO Auto-generated method stub
		revenueRepository.updateRevenue(revenue);
		
	}

	@Override
	public List<Revenue> getAllByDate(Date date) {
		// TODO Auto-generated method stub
		return revenueRepository.getAllByDate(date);
	}

	@Override
	public List<Revenue> getAllByMonth(int month , int year) {
		// TODO Auto-generated method stub
		return revenueRepository.getAllByMonth( month ,  year);
	}

	


}
