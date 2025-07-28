package com.aurionpro.ems.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.aurionpro.ems.builder.UserBuilder;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;
import com.aurionpro.ems.models.Student;
import com.aurionpro.ems.utils.ResultSetConversion;

public class StudentDao {
	Connection connection;

	public StudentDao() {
		 connection = Database.getConnection();
	}

	public Student getStudentByEmail(String email) {
		Student student = null;
		if (email.isBlank()) return null;

		try {
			String sql = "select * from student st" + "join user u on st.user_id = u.user_id" + "where u.email = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);

			ResultSet result = statement.executeQuery();
			if (!result.next()) {
				return null;
			}

//			UserBuilder userBuilder = new UserBuilder();
//
//			student=  userBuilder
//					.setUserId(result.getInt("user_id"))
//					.setFirstName(result.getString("firesultt_name"))
//					.setLastName(result.getString("last_name"))
//					.setMobileNumber(result.getLong("mobile_number"))
//					.setEmail(result.getString("email"))
//					.setGender(Gender.valueOf(result.getString("gender")))
//					.setCity(result.getString("city"))
//					.setRole(Role.valueOf(result.getString("role")))
//					.setCreatedAt(new Date(result.getTimestamp("created_at").getTime()))
//					.setFirstLogin(result.getBoolean("is_firesultt_login"))
//					.setStudentId(result.getInt("student_id"))
//					.setRollNumber(result.getInt("roll_number"))
//					.setAveragePercentage(result.getBigDecimal("average_percentage"))
//					.setYearOfStudy(result.getInt("year_of_study"))
//					.getStudent();
			List<Student> students = ResultSetConversion.convertStudentresultSetSetToStudent(result);
			if(students.isEmpty()) return null;
			student = students.get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return student;
	}

}
