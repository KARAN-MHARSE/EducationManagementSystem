package com.aurionpro.ems.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.aurionpro.ems.utils.DatabaseUrl;

public class Database {
//	Singletone class to established the jdbc connection
	private static Connection connection;
	private static String databaseURL = DatabaseUrl.getDatabaseUrl();
	
	private Database() {		
	}
	
	public static Connection getConnection() {
		if(connection ==  null) {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(databaseURL);
				System.out.println("Connected");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return connection;
		
	}

}