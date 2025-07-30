package com.aurionpro.ems.utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.ems.builder.UserBuilder;
import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Student;
import com.google.protobuf.Timestamp;

public class ResultSetConversion {
	public static List<Student> convertStudentresultSetSetToStudentList(ResultSet resultSet) throws SQLException {
		List<Student> students = new ArrayList<Student>();

		while(resultSet.next()) {
			Student student = null;
						
			UserBuilder userBuilder = new UserBuilder();
			student = userBuilder.setUserId(resultSet.getInt("user_id"))
					.setFirstName(resultSet.getString("first_name")).setLastName(resultSet.getString("last_name"))
					.setMobileNumber(resultSet.getLong("mobile_number")).setEmail(resultSet.getString("email"))
					.setGender(Gender.valueOf(resultSet.getString("gender"))).setCity(resultSet.getString("city"))
					.setRole(Role.valueOf(resultSet.getString("role")))
					.setFirstLogin(resultSet.getBoolean("is_first_login"))
					.setStudentId(resultSet.getInt("student_id")).setRollNumber(resultSet.getInt("roll_number"))
					.setAveragePercentage(resultSet.getBigDecimal("average_percentage"))
					.setYearOfStudy(resultSet.getInt("year_of_study"))
					.setActive(resultSet.getBoolean("isActive"))
					.getStudent();
			
			students.add(student);		
		}
		

		return students;
	}
	
	
	public static List<Course> convertCourseResultSetSetToStudentList(ResultSet resultSet) throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		
		while (resultSet.next()) {
			Course course = new Course();
			course.setCourseId(resultSet.getInt("course_id"));
			course.setName(resultSet.getString("name"));
		
			courses.add(course);
	}
		return courses;
	}

	
}
