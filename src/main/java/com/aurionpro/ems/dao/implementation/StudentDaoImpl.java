package com.aurionpro.ems.dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.model.*;
import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;
import com.aurionpro.ems.dao.IStudentDao;

public class StudentDaoImpl implements IStudentDao {

	private Connection conn = Database.getConnection();

	@Override
	public boolean addStudent(Student student) {
		User user = student.getUser();
		
		
		
		String query = "CALL ems.AddNewStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (CallableStatement stmt = conn.prepareCall(query)) {
			

			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setLong(3, user.getMobileNumber());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getGender().name());
			stmt.setString(6, user.getCity());
			stmt.setString(7, user.getRole().name());
			stmt.setInt(8, student.getRollNumber());
			stmt.setBigDecimal(9, student.getAveragePercentage());
			stmt.setInt(10, student.getYearOfStudy());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("Error adding student: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		String query = "select u.*,s.* from ems.user u join ems.student s on u.user_id = s.user_id";

		try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setMobileNumber(rs.getLong("mobile_number"));
				user.setEmail(rs.getString("email"));
				user.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
				user.setCity(rs.getString("city"));
				user.setRole(Role.valueOf(rs.getString("role")));
				user.setCreatedAt(rs.getDate("created_at"));
				user.setFirstLogin(rs.getBoolean("is_first_login"));

				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setRollNumber(rs.getInt("roll_number"));
				student.setAveragePercentage(rs.getBigDecimal("average_percentage"));
				student.setYearOfStudy(rs.getInt("year_of_study"));
				student.setUser(user);

				students.add(student);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching students: " + e.getMessage());
		}

		return students;
	}

	public boolean isStudentExists(String email, int rollNumber) {

		String sql = "select count(*) from ems.user u join ems.student s on u.user_id= s.user_id where email =? or roll_number=?";
		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, rollNumber);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1) > 0;

			}

		} catch (SQLException exception) {
			System.out.println("Error checking existing student: " + exception.getMessage());
		}
		return false;
	}
}
