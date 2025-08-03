
package com.aurionpro.ems.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.exceptions.CustomException;
import com.aurionpro.ems.exceptions.DataValidationException;
import com.aurionpro.ems.exceptions.StudentNotFoundException;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Student;
import com.aurionpro.ems.utils.ResultSetConversion;
import com.aurionpro.ems.utils.UserUtil;

public class StudentDaoImpl implements IStudentDao {
	private final Connection connection;

	public StudentDaoImpl() {
		connection = Database.getConnection();
	}

	@Override
	public Student getStudentByID(int id) {
		String sql = "SELECT * FROM ems.student st JOIN ems.user u ON st.user_id = u.user_id WHERE st.student_id = ?;";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			List<Student> students = ResultSetConversion.convertStudentresultSetSetToStudentList(result);
			return students.isEmpty() ? null : students.get(0);

		} catch (SQLException e) {
			System.err.println("Error fetching student by ID: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Student getStudentByRollNumber(int rollNumber) {
		String sql = "SELECT * FROM ems.student st JOIN ems.user u ON st.user_id = u.user_id WHERE st.roll_number = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, rollNumber);
			ResultSet result = statement.executeQuery();

			List<Student> students = ResultSetConversion.convertStudentresultSetSetToStudentList(result);
			return students.isEmpty() ? null : students.get(0);

		} catch (SQLException e) {
			System.out.println("Error fetching student by roll number: " + e.getMessage());
			return null;
		}
	}

	public Student getStudentByMobileNumber(Long mobileNumber) {
		if (mobileNumber == null || String.valueOf(mobileNumber).length() != 10)
			throw new DataValidationException("Enter valid mobile number");

		String sql = "select * from ems.student st join ems.user u on st.user_id = u.user_id where u.mobile_number = ?;";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, mobileNumber);
			ResultSet result = statement.executeQuery();

			List<Student> students = ResultSetConversion.convertStudentresultSetSetToStudentList(result);
			if (students.isEmpty())
				return null;
			return students.get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Student getStudentByEmail(String email) {
		if (email == null || email.isBlank())
			return null;

		String sql = "select * from ems.student st join ems.user u on st.user_id = u.user_id where u.email = ?;";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();

			List<Student> students = ResultSetConversion.convertStudentresultSetSetToStudentList(result);
			if (students.isEmpty())
				return null;
			return students.get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Student> getAllStudents() {
		String sql = "select * from ems.student st join ems.user u on u.user_id=st.user_id";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			List<Student> students = ResultSetConversion.convertStudentresultSetSetToStudentList(result);

			if (students.isEmpty())
				throw new StudentNotFoundException();

			return students;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean addNewStudent(Student student) {
		 String query = "CALL ems.AddNewStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		    try (CallableStatement stmt = connection.prepareCall(query)) {

		        stmt.setString(1, student.getFirstName());
		        stmt.setString(2, student.getLastName());
		        stmt.setLong(3, student.getMobileNumber());
		        stmt.setString(4, student.getEmail());	        
		        stmt.setString(5, student.getGender().name());
		        stmt.setString(6, student.getCity());
		        stmt.setString(7,student.getRole().name());
		        stmt.setInt(8, student.getRollNumber());
		        stmt.setBigDecimal(9, student.getAveragePercentage());
		        stmt.setInt(10, student.getYearOfStudy());

		        // Register OUT parameter
		        stmt.registerOutParameter(11, Types.VARCHAR);

		        stmt.execute();

		        String resultMessage = stmt.getString(11);

		        boolean success = "SUCCESS".equalsIgnoreCase(resultMessage);
		        if(!success) {
		        	throw new CustomException(resultMessage);
		        	
		        }
		         return true;

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
			return false;
	}

	public boolean isStudentExists(String email, int rollNumber) {
		String sql = "select count(*) from ems.user u join ems.student s on u.user_id= s.user_id where email =? or roll_number=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
	public boolean assignCourse(int studentId, int courseId) {

	  
	    if (!checkIsActive(studentId)) {
	        System.out.println(" Student not found or may be inactive.");
	        return false;
	    }

	    String insertSql = "insert into ems.student_course (student_id, course_id) values (?, ?)";

	    try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
	        insertStmt.setInt(1, studentId);
	        insertStmt.setInt(2, courseId);
	        insertStmt.executeUpdate();

	       // System.out.println("Course assigned successfully.");
	        return true;

	    } catch (SQLIntegrityConstraintViolationException e) {
	        
	        System.out.println("Course is already assigned to the student.");
	        return false;

	    } catch (SQLException e) {
	        System.out.println(" Error assigning course: " + e.getMessage());
	        return false;
	    }
	}


	public boolean checkIsActive(int studentId) {
		String sql ="Select isActive as status from ems.user u join ems.student s on u.user_id=s.user_id where s.student_id = ?";

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, studentId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getBoolean("status");
	            } else {
	                System.out.println("No student found with ID: " + studentId);
	                return false; 
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println(" Error checking isActive: " + e.getMessage());
	        return false;
	    }
	}

	@Override
	public List<Course> viewCoursesByStudentId(int studentId) {
	    List<Course> courses = new ArrayList<Course>();
	    

	    if (!checkIsActive(studentId)) {
	        System.out.println(" Student not found or may be inactive.");
	        return courses; 
	    }

	    String sql = "select c.course_id, c.name, c.description, c.course_duration, c.created_at " +
	                 "from ems.student_course sc " +
	                 "join ems.course c ON sc.course_id = c.course_id " +
	                 "where sc.student_id = ? ";

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, studentId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Course course = new Course();
	            course.setCourseId(rs.getInt("course_id"));
	            course.setName(rs.getString("name"));
	            course.setDescription(rs.getString("description"));
	            course.setCourseYear(rs.getInt("course_duration"));

	            courses.add(course);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error fetching courses for student ID: " + studentId);
	    }

	    return courses;
	}
	
}
