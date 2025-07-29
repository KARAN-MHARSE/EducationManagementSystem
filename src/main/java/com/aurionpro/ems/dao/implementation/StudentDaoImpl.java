
package com.aurionpro.ems.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.exceptions.DataValidationException;
import com.aurionpro.ems.exceptions.StudentNotFoundException;
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
		String query = "CALL ems.AddNewStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (CallableStatement stmt = connection.prepareCall(query)) {

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

}
