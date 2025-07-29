package com.aurionpro.ems.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;
import com.aurionpro.ems.model.Student;
//import com.aurionpro.ems.model.User;



public class DataValidator {

	Scanner scanner = new Scanner(System.in);

	public static boolean isValidName(String name) {
		if (name == null || name.isBlank()) {
			return false;
		}

		Pattern pattern = Pattern.compile("^[a-zA-z ]+$");
		Matcher matcher = pattern.matcher(name);

		return matcher.matches();
	}

	public static boolean isValidEmail(String email) {
		if (email == null || email.isBlank())
			return false;

		Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidMobileNumber(long mobile) {
		String mobileStr = String.valueOf(mobile);

		Pattern pattern = Pattern.compile("^[6-9]\\d{9}$");
		Matcher matcher = pattern.matcher(mobileStr);
		return matcher.matches();
	}

	public static int checkFormatInt(Scanner scanner, String message) {
		while (true) {
			System.out.println(message);
			String input = scanner.nextLine();

			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException exception) {
				System.out.println("only integer ");
			}
		}
	}

	public static double checkFormatDouble(Scanner scanner, String message) {
		while (true) {
			System.out.println(message);
			String input = scanner.nextLine();

			try {
				return Double.parseDouble(input);
			} catch (NumberFormatException exception) {
				System.out.println("only double allowed ");
			}
		}
	}

	public static boolean isValidPassword(String password) {
		if (password == null || password.isBlank())
			return false;

		/*
		 * Has at least one digit
		 * 
		 * Has at least one lowercase letter
		 * 
		 * Has at least one uppercase letter
		 * 
		 * Has at least one special character
		 * 
		 * Has no spaces
		 * 
		 * Is between 8 and 20 characters long
		 * 
		 * 
		 */
		Pattern pattern = Pattern.compile("\"^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#$%^&+=])(?=\\\\S+$).{8,20}$\"");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public static List<Student> convertResultSetToStudentList(ResultSet rs) throws SQLException {
	    List<Student> students = new ArrayList<>();

	    while (rs.next()) {
	        Student student = new Student();

	        //User user = student.getUser();

	        student.setFirstName(rs.getString("first_name"));
	        student.setLastName(rs.getString("last_name"));
	        student.setMobileNumber(rs.getLong("mobile_number"));
	        student.setEmail(rs.getString("email"));
	        student.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
	        student.setCity(rs.getString("city"));
	        student.setRole(Role.valueOf(rs.getString("role").toUpperCase()));

	        
	        try {
	        	student.setCreatedAt(rs.getTimestamp("created_at"));
	        	student.setFirstLogin(rs.getBoolean("is_first_login")); 
	        } catch (SQLException e) {
	           
	        }

	        student.setStudentId(rs.getInt("student_id"));
	        student.setRollNumber(rs.getInt("roll_number"));
	        student.setAveragePercentage(rs.getBigDecimal("average_percentage"));
	        student.setYearOfStudy(rs.getInt("year_of_study"));

	        students.add(student);
	    }

	    return students;
	}



	
	
	

}
