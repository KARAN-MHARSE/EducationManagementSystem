package com.aurionpro.ems.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.aurionpro.ems.controllers.AdminController;
import com.aurionpro.ems.controllers.CourseController;
import com.aurionpro.ems.controllers.DashboardController;
import com.aurionpro.ems.controllers.FeesController;
import com.aurionpro.ems.controllers.StudentController;
import com.aurionpro.ems.controllers.TeacherController;
import com.aurionpro.ems.dao.implementation.CourseDaoImplementation;
import com.aurionpro.ems.dao.implementation.StudentDaoImpl;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.enums.Role;
import com.aurionpro.ems.exceptions.AuthenticationException;
import com.aurionpro.ems.exceptions.DataValidationException;
import com.aurionpro.ems.utils.DataValidationUtil;

public class AuthenticationService {
	private final Connection connection = Database.getConnection();
	private AdminController adminController;
	private StudentController studentController;
	private TeacherController teacherController;

	public AuthenticationService() {

		StudentController studentController = new StudentController(new StudentService(new StudentDaoImpl()));
		TeacherController teacherController = new TeacherController(new TeacherService());
		CourseController courseController = new CourseController(new CourseService(new CourseDaoImplementation()));
		FeesController feesController = new FeesController(new FeesServices());
		DashboardController dashboardController = new DashboardController(new DashboardService());

		adminController = new AdminController(studentController, teacherController, courseController, feesController,
				dashboardController);
	}

	public void login(Scanner scanner) throws SQLException {
		System.out.println("Enter the email");
		String email = scanner.nextLine();

		System.out.println("Enter the password");
		String password = scanner.nextLine();

		if (email.isBlank() || password.isBlank()) {
			throw new DataValidationException("Enter valid credentials");
		}

//		Get user by email
		String sql = "select * from ems.user u join ems.authentication a on u.user_id= a.user_id where u.email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();
		if (!resultSet.next()) {
			throw new AuthenticationException("User not found");
		}

//		Validate password
		if (!password.equals(resultSet.getString("password"))) {
			throw new AuthenticationException("Wrong credentials");
		}

		Role role = Role.valueOf(resultSet.getString("role"));
		switch (role) {
//		case Student:
//			StudentController.showMenu(scanner);
//			break;
//		case Teacher:
//			TeacherController.showMenu(scanner);
//			break;
		case Admin:
			adminController.showMenu(scanner);
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}

	}

	public void changePassword(Scanner scanner) throws SQLException {
		System.out.println("Enter the email id");
		String email = scanner.nextLine();

		if (!DataValidationUtil.isValidEmail(email))
			throw new DataValidationException("Enter valid email address");

		System.out.println("Enter old password");
		String oldPassword = scanner.nextLine();

		System.out.println("Enter new Password");
		String newPassword = scanner.nextLine();

		if (oldPassword == null || newPassword == null || oldPassword.isBlank() || newPassword.isBlank()) {
			throw new DataValidationException("Enter valid credentials");
		}

		String sql = "Call ems.change_password(?,?,?);";
		CallableStatement statement = connection.prepareCall(sql);
		statement.setString(1, email);
		statement.setString(2, oldPassword);
		statement.setString(3, newPassword);

		int isChanged = statement.executeUpdate();
		if (isChanged > 0) {
			System.out.println("Password changes successfully");
			return;
		}
		System.out.println("Something went wrong");

	}
}
