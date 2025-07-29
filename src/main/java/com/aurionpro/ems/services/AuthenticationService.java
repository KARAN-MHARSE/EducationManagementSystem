package com.aurionpro.ems.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.aurionpro.ems.controllers.AdminController;
import com.aurionpro.ems.controllers.StudentController;
import com.aurionpro.ems.controllers.TeacherController;
import com.aurionpro.ems.dao.implementation.StudentDaoImpl;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.enums.Role;
import com.aurionpro.ems.exceptions.AuthenticationException;
import com.aurionpro.ems.exceptions.DataValidationException;

public class AuthenticationService {
	private final Connection connection = Database.getConnection();
	private AdminController adminController;
	private StudentController studentController;
	private TeacherController teacherController;
	
	public AuthenticationService() {
		StudentService studentService = new StudentService(new StudentDaoImpl());
		TeacherService teacherService = new TeacherService();
		
		adminController = new AdminController(studentService,teacherService);
	}

	public  void login(Scanner scanner) throws SQLException {
		System.out.println("Enter the email");
		String email = scanner.nextLine();
		
		System.out.println("Enter the password");
		String password = scanner.nextLine();
		
		if(email.isBlank() || password.isBlank()) {
			throw new DataValidationException("Enter valid credentials");
		}
		
//		Get user by email
		String sql = "select * from ems.user u join ems.authentication a on u.user_id= a.user_id where u.email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();
		if(!resultSet.next()) {
			throw new AuthenticationException("User not found");
		}
		
//		Validate password
		if(!password.equals(resultSet.getString("password"))) {
			throw new AuthenticationException("Wrong credentials");
		}
		
		Role role = Role.valueOf(resultSet.getString("role"));
		switch (role) {
		case Student:
			StudentController.showMenu(scanner);
			break;
		case Teacher:
			TeacherController.showMenu(scanner);
			break;
		case Admin:
			adminController.showMenu(scanner);
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}		
		
	}
	

}
