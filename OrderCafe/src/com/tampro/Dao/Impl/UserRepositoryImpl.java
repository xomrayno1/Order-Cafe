package com.tampro.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tampro.Connection.MyConnection;
import com.tampro.Dao.UserRepository;
import com.tampro.Model.User;

public class UserRepositoryImpl  implements UserRepository{
	
	private static String  sqlLogin = "SELECT * FROM USER WHERE USERNAME  = ? AND PASSWORD = ?";
	private static String  sqlAllUser = "SELECT * FROM USER";
	private static String  sqlAddUser = "INSERT INTO USER(USERNAME,PASSWORD,ROLE,NAME)VALUES(?,?,?,?)";
	private static String sqlUpdateUser = "UPDATE USER SET USERNAME = ? , PASSWORD = ? , ROLE = ? , NAME = ? WHERE ID = ?";
	private static String sqlGetUser = "SELECT * FROM USER WHERE ID = ?";
	private final static  String sqlDelete = "DELETE FROM USER WHERE ID = ? ";

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlGetUser);
			statement.setInt(1, id);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()) {
				User user = new User();
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				user.setName(set.getString("name"));
				user.setId(set.getInt("id"));
				user.setRole(set.getString("role"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlAllUser);

			ResultSet set = statement.executeQuery();
			
			while(set.next()) {
				User user = new User();
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				user.setName(set.getString("name"));
				user.setId(set.getInt("id"));
				user.setRole(set.getString("role"));
				list.add(user);				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getUserByLogin(String username, String password) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		try {
			PreparedStatement statement  = connection.prepareStatement(sqlLogin);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet set = statement.executeQuery();
			
			while(set.next()) {
				User user = new User();
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				user.setName(set.getString("name"));
				user.setId(set.getInt("id"));
				user.setRole(set.getString("role"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sqlAddUser);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole());
			statement.setString(4, user.getName());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateUserById(User user) {
		Connection connection = MyConnection.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sqlUpdateUser);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole());
			statement.setString(4, user.getName());
			statement.setInt(5, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		Connection connection = MyConnection.getConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sqlDelete);		
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
