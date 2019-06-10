package com.tampro.Dao;

import java.sql.Date;
import java.util.List;

import com.tampro.Model.Revenue;

public interface RevenueRepository {
	
	public void addRevenue(Revenue revenue);
	
	public void updateRevenue(Revenue revenue);
	
	public List<Revenue> getAllByDate( Date date);
	
	public List<Revenue> getAllByMonth(int month , int year);
	
	

}
