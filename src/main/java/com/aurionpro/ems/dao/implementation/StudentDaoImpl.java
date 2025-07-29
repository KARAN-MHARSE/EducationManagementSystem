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

	private Connection conn ;
	
	public StudentDaoImpl() {
		conn = Database.getConnection();
	}

	@Override
	public boolean addStudent(Student student) {
		//User user = student.getUser();
		
		
		
		String query = "CALL ems.AddNewStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (CallableStatement stmt = conn.prepareCall(query)) {
			

			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setLong(3, student.getMobileNumber());
			stmt.setString(4, student.getEmail());
			stmt.setString(5, student.getGender().name());
			stmt.setString(6, student.getCity());
			stmt.setString(7, student.getRole().name());
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



	@Override
	public boolean assignCourse(int studentId, int courseId) {
	    String checkSql = "select count(*) from ems.student_course where student_id = ? and  course_id = ?";
	    String insertSql = "insert into ems.student_course (student_id, course_id) values (?, ?)";

	    try (
	        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	        PreparedStatement insertStmt = conn.prepareStatement(insertSql)
	    ) {
	        checkStmt.setInt(1, studentId);
	        checkStmt.setInt(2, courseId);
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            System.out.println(" Course is already assigned to the student.");
	            return false;
	        }

	        insertStmt.setInt(1, studentId);
	        insertStmt.setInt(2, courseId);
	        return insertStmt.executeUpdate() > 0;

	    } catch (SQLException e) {
	        System.out.println("Error assigning course: " + e.getMessage());
	        return false;
	    }
	}

	
	


	@Override
	public void viewCoursesByStudentId(int studentId) {
	    String sql = "select sc.student_id,sc.course_id, c.name " +
	                 "from ems.student_course sc " +
	                 "join ems.course c ON sc.course_id = c.course_id " +
	                 "where sc.student_id = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, studentId);
	        ResultSet rs = stmt.executeQuery();

	        boolean found = false;

	        System.out.println("\n Courses assigned to Student ID: " + studentId);
	        System.out.println("-----------------------------------------");

	        while (rs.next()) {
	            int courseId = rs.getInt("course_id");
	            String courseName = rs.getString("name");

	            System.out.println("Course ID   : " + courseId);
	            System.out.println("Course Name : " + courseName);
	            System.out.println("-----------------------------------------");
	            found = true;
	        }

	        if (!found) {
	            System.out.println(" No courses found for this student.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(" Error occurred while fetching courses.");
	    }
	}





	
	
}
