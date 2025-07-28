package com.aurionpro.ems.dao.implementation;

import java.net.SocketException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.model.*;
import com.aurionpro.ems.utils.DataValidator;
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



	@Override
	public List<Student> getAllStudents() {
		
		 String query = "SELECT u.*, s.* FROM ems.user u JOIN ems.student s ON u.user_id = s.user_id";
		 List<Student> students = new ArrayList();
		 
		 try(PreparedStatement preparedStatement = conn.prepareStatement(query);
				 ResultSet resultSet = preparedStatement.executeQuery()){
			 
			 
			 students= DataValidator.convertResultSetToStudentList(resultSet);
			 
		 }catch(SQLException exception) {
			 System.out.println(exception.getMessage());
		 }
		return students;
		
	}
}
