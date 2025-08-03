package com.aurionpro.ems.controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.dto.Dashboard;

public class DashboardController {

	Connection connection;
	public DashboardController() {
		connection = Database.getConnection();
	}
	
	 public List<Dashboard> getDashboardData() {
	        List<Dashboard> dashboardList = new ArrayList<>();
	        String procedureCall = "{CALL ems.dashboard()}";

	        try ( CallableStatement stmt = connection.prepareCall(procedureCall);
	            ResultSet rs = stmt.executeQuery();
	        ) {
	            while (rs.next()) {
	                Dashboard entry = new Dashboard(
	                    rs.getInt("sr_no"),
	                    rs.getInt("student_id"),
	                    rs.getString("student_name"),
	                    rs.getString("course_enrolled"),
	                    rs.getString("subjects_assigned"),
	                    rs.getString("teacher"),
	                    rs.getDouble("total_fee"),
	                    rs.getDouble("total_paid"),
	                    rs.getDouble("pending_fee")
	                );

	                dashboardList.add(entry);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); 
	            System.out.println("unable to dispay dash board");
	        }

	        return dashboardList;
	    } 
}
