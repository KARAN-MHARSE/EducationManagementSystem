package com.aurionpro.ems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aurionpro.ems.database.Database;

public class EmsApplication{
	public static void main(String args[]) {
		System.out.println("Welcome");
		System.out.println("Hello");
		
		Connection connection = Database.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("select * from user");
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				System.out.println(result.getString("first_name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}