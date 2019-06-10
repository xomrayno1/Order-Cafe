package com.tampro.Dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tampro.Connection.MyConnection;
import com.tampro.Dao.OrderRepository;
import com.tampro.Model.Order;

public class OrderRepositoryImpl  implements OrderRepository{
	//SELECT sum(supertotalPrice) FROM ordercafe.`order` where month(createdate) = '06' and year(createdate) = '2019';

	private static final String sqlAdd = "INSERT INTO `ORDER`(`supertotalPrice`,`createDate`) VALUES(?,?)";
	private static final String sqlTotal = "SELECT sum(supertotalPrice) FROM ordercafe.order where  createDate =?";
	private static final String sqlTotalByMonth = "SELECT sum(supertotalPrice) FROM ordercafe.`order` where month(createdate) = ? and year(createdate) = ? ";

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		PreparedStatement preparedStatement = null;
		int key = 0 ;
		try {
			 preparedStatement = connection.prepareStatement(sqlAdd,preparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setBigDecimal(1, order.getTotalPrice());
			preparedStatement.setDate(2, order.getCreateDate());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			 
			while(rs.next()) {
			key = rs.getInt(1);	
			}
			return key;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Order getOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal TotalPrice(Date date) {
		Connection connection = MyConnection.getConnection();
		PreparedStatement preparedStatement = null;
		BigDecimal bigDecimal = new BigDecimal(0);
	
		try {
			preparedStatement = connection.prepareStatement(sqlTotal);			
			preparedStatement.setDate(1, date);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				bigDecimal = rs.getBigDecimal(1);
			}
			 
			return bigDecimal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bigDecimal;
	}

	@Override
	public BigDecimal TotalPriceByMonth(int month, int years) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		PreparedStatement preparedStatement = null;
		BigDecimal bigDecimal = new BigDecimal(0);
	
		try {
			preparedStatement = connection.prepareStatement(sqlTotalByMonth);			
			preparedStatement.setInt(1, month);
			preparedStatement.setInt(2, years);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				bigDecimal = rs.getBigDecimal(1);
			}
			 
			return bigDecimal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bigDecimal;
	}

	
}
