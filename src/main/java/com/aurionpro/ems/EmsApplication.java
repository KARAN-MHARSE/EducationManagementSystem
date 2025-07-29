package com.aurionpro.ems;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.Data;

import com.aurionpro.ems.controllers.AdminController;
import com.aurionpro.ems.controllers.AuthenticationController;
import com.aurionpro.ems.dao.implementation.CourseDaoImplementation;
import com.aurionpro.ems.dao.implementation.StudentDaoImpl;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.User;
import com.aurionpro.ems.services.AuthenticationService;
import com.aurionpro.ems.services.StudentService;
import com.aurionpro.ems.services.TeacherService;

public class EmsApplication {
	public static User currentUser = null;

	public static void main(String args[]) throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to College Management System");
		
		
		AuthenticationController authenticationController = new AuthenticationController(new AuthenticationService());

		authenticationController.showAuthenticationMenu(scanner);
		
	
	}
}