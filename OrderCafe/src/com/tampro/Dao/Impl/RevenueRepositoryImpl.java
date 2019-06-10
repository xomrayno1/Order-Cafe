package com.tampro.Dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tampro.Connection.MyConnection;
import com.tampro.Dao.ProductRepository;
import com.tampro.Dao.RevenueRepository;
import com.tampro.Model.Product;
import com.tampro.Model.Revenue;

public class RevenueRepositoryImpl implements RevenueRepository{
	
	private final static String sqlAdd = "INSERT INTO REVENUE(idproduct,quantity,createdate)VALUES(?,?,?)";
	private final static String sqlUpdate = "UPDATE REVENUE SET idproduct=?,createdate = ?,quantity =? where idproduct = ?  and createdate = ? ";
	private final static String sqlGetAll = "SELECT * FROM REVENUE WHERE createdate=?";
	private final static String sqlGetAllMonth = "SELECT * FROM ordercafe.revenue where month(createdate) = ? and year(createdate) = ?";
	private ProductRepository  productRepository = new ProductRepositoryImpl();

	@Override
	public void addRevenue(Revenue revenue) {
		// TODO Auto-generated method stub
		Connection connection  = MyConnection.getConnection();
		try {
			PreparedStatement statement =  connection.prepareStatement(sqlAdd);
			
			statement.setInt(1, revenue.getProduct().getId());
			statement.setInt(2, revenue.getQuantity());
			statement.setDate(3, revenue.getCreateDate());
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateRevenue(Revenue revenue) {
		// TODO Auto-generated method stub
		Connection connection  = MyConnection.getConnection();
		try {
			PreparedStatement statement =  connection.prepareStatement(sqlUpdate);
			statement.setInt(1, revenue.getProduct().getId());
			statement.setDate(2, revenue.getCreateDate());
			statement.setInt(3, revenue.getQuantity());
		
			statement.setInt(4, revenue.getProduct().getId());
			statement.setDate(5, revenue.getCreateDate());
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Revenue> getAllByDate(Date date) {
		// TODO Auto-generated method stub
		Connection connection  = MyConnection.getConnection();
		List<Revenue> list = new ArrayList<Revenue>();
		
		try {
			PreparedStatement statement =  connection.prepareStatement(sqlGetAll);
			statement.setDate(1, date);
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				Revenue   revenue = new Revenue();
				Product product = productRepository.getProductById(set.getInt("idproduct"));
				revenue.setCreateDate(set.getDate("createdate"));
				revenue.setId(set.getInt("id"));
				revenue.setQuantity(set.getInt("quantity"));
				revenue.setProduct(product);
				list.add(revenue);
			}
			return list;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Revenue> getAllByMonth(int month , int year) {
		Connection connection  = MyConnection.getConnection();
		List<Revenue> list = new ArrayList<Revenue>();
		
		try {
			PreparedStatement statement =  connection.prepareStatement(sqlGetAllMonth);
			statement.setInt(1, month);
			statement.setInt(2, year);
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				Revenue   revenue = new Revenue();
				Product product = productRepository.getProductById(set.getInt("idproduct"));
				revenue.setCreateDate(set.getDate("createdate"));
				revenue.setId(set.getInt("id"));
				revenue.setQuantity(set.getInt("quantity"));
				revenue.setProduct(product);
				list.add(revenue);
			}
			return list;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
