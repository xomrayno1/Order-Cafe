package com.tampro.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tampro.Connection.MyConnection;
import com.tampro.Dao.CartItemRepository;
import com.tampro.Model.CartItem;

public class CartItemRepositoryImpl  implements CartItemRepository{
	private static final  String sqlAdd = "INSERT INTO CARTITEM(idproduct,totalPrice,quantity,idorder,createdate)VALUES(?,?,?,?,?)";
	private static final String sqlGet = "xx";
	private static final String sqlDelete = "xx";
	
	
	@Override
	public void addCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		Connection connection  = MyConnection.getConnection();
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlAdd);
			statement.setInt(1, cartItem.getIdProduct());
			statement.setBigDecimal(2, cartItem.getTotalPrice());
			statement.setInt(3, cartItem.getQuantity());
			statement.setInt(4, cartItem.getIdOrder());
			statement.setDate(5, cartItem.getCreateDate());
			statement.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
