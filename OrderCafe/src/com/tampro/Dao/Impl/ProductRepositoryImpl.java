package com.tampro.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tampro.Connection.MyConnection;
import com.tampro.Dao.ProductRepository;
import com.tampro.Model.Product;

public class ProductRepositoryImpl implements ProductRepository {
	private final static  String SqlGetAll = "SELECT * FROM PRODUCT";
	private final static  String sqlAddProduct = "INSERT INTO PRODUCT(NAME,PRICE,ACTIVE,CREATEDATE) VALUES (?,?,?,?)";
	private final static  String sqlGetProduct = "SELECT * FROM PRODUCT WHERE ID = ? ";
	private final static  String sqlUpdate = "UPDATE PRODUCT SET NAME = ? ,PRICE = ?, ACTIVE = ? ,CREATEDATE = ? WHERE ID = ?";
	private final static  String sqlDelete = "DELETE FROM PRODUCT WHERE ID = ? ";
	private final static  String SqlGetAllByName = "SELECT * FROM PRODUCT WHERE NAME = ?";

	@Override
	public List<Product> getAllProduct() {
		Connection connection = MyConnection.getConnection();
		List<Product> list = new ArrayList<Product>();
		
		try {
			PreparedStatement statement  = connection.prepareStatement(SqlGetAll);
			ResultSet resultSet  = statement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setActive(resultSet.getInt("active"));
				product.setCreateDate(resultSet.getDate("createdate"));
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				list.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	@Override
	public Product getProductById(int id) {
		Connection connection = MyConnection.getConnection();
		
		
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlGetProduct);
			statement.setInt(1, id);
			ResultSet resultSet  = statement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setActive(resultSet.getInt("active"));
				product.setCreateDate(resultSet.getDate("createdate"));
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				return product;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlAddProduct);
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setInt(3, product.getActive());
			statement.setDate(4,product.getCreateDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateProductById(Product product) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlUpdate);
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setInt(3, product.getActive());
			statement.setDate(4,product.getCreateDate());
			statement.setInt(5, product.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteProductById(Product product) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlDelete);
			statement.setInt(1,product.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getProductByName(String name) {
		Connection connection = MyConnection.getConnection();
		List<Product> list = new ArrayList<Product>();
		
		try {
			PreparedStatement statement  = connection.prepareStatement(SqlGetAllByName);
			statement.setString(1, name);
			ResultSet resultSet  = statement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setActive(resultSet.getInt("active"));
				product.setCreateDate(resultSet.getDate("createdate"));
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				list.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}

}
