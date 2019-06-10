package com.tampro.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private final static 	String url = "jdbc:mysql://localhost/ordercafe";
	private final static 	String driver = "com.mysql.jdbc.Driver";
	private final static    String user = "root";
	private final static    String password = "";
	public static Connection getConnection() {
		Connection connection = null;
	
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			Class.forName (driver);
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(MyConnection.getConnection());
	}

}
