package com.aurionpro.ems.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
//	Singletone class to established the jdbc connection
	private static Connection connection;
	private static String databaseURL = "jdbc:mysql://2RqhxCH2xFBML5G.root:gxETaI26hEoylgjr@gateway01.ap-southeast-1.prod.aws.tidbcloud.com:4000/ems";
	
	private Database() {		
	}
	
	public static Connection getConnection() {
		if(connection ==  null) {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(databaseURL);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return connection;
		
	}

}
