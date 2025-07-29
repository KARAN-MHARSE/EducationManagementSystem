package com.aurionpro.ems.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.model.Teacher;

public class TeacherDao {
//	private PreparedStatement preparedStatement = null;
//	private CallableStatement callableStatement = null;
//	private ResultSet resultset=null;

	Connection connection;

	public TeacherDao() {
		connection = Database.getConnection();
	}

	public boolean addNewTeacher(Teacher teacher) {
		try (CallableStatement callableStatement = connection
				.prepareCall("{call ems.AddNewTeacher(?,?,?,?,?,?,?,?,?,?)}")) {

			callableStatement.setString(1, teacher.getFirstName());
			callableStatement.setString(2, teacher.getLastName());
			callableStatement.setLong(3, teacher.getMobileNumber());
			callableStatement.setString(4, teacher.getEmail());
			callableStatement.setString(5, teacher.getGender().name());
			callableStatement.setString(6, teacher.getCity());
			callableStatement.setString(7, teacher.getRole().name());

			if (teacher.getCreatedAt() != null) {
				callableStatement.setDate(8, new java.sql.Date(teacher.getCreatedAt().getTime()));
			} else {
				callableStatement.setNull(8, java.sql.Types.DATE);
			}

			callableStatement.setString(9, teacher.getQualification());
			callableStatement.setInt(10, teacher.getExperience());

			int updates = callableStatement.executeUpdate();
			return updates > 0;

		} catch (SQLException e) {
//            return false;
			e.printStackTrace();
		}
		return false;
	}

	public void showAllTeachers() {

		String query = "SELECT t.teacher_id, u.first_name, u.last_name, u.email, t.qualification, t.experience FROM ems.teacher t JOIN ems.user u ON t.user_id = u.user_id";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			System.out.println(
					"+------------+----------------------+---------------------------+----------------------+------------+");
			System.out.println(
					"| Teacher ID | Name                 | Email                     | Qualification        | Experience |");
			System.out.println(
					"+------------+----------------------+---------------------------+----------------------+------------+");

			while (resultSet.next()) {
				int teacherId = resultSet.getInt("teacher_id");
				String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String qualification = resultSet.getString("qualification");
				int experience = resultSet.getInt("experience");

				System.out.printf("| %-10d | %-20s | %-25s | %-20s | %-10d |\n", teacherId, fullName, email,
						qualification, experience);
			}

			System.out.println(
					"+------------+----------------------+---------------------------+----------------------+------------+");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getMetaData() {
		try {
			Connection connection = Database.getConnection();
			DatabaseMetaData metaData = connection.getMetaData();

			ResultSet resultSet = metaData.getTables(null, null, "%", new String[] { "TABLE" });

			System.out.println("\nTables in the database:");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("TABLE_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void printAllTeacherDetails() {
		String query = "SELECT t.teacher_id, u.first_name, u.last_name " + "FROM ems.teacher t "
				+ "JOIN ems.user u ON t.user_id = u.user_id";

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			System.out.println("======================================");
			System.out.println("         Teacher Details");
			System.out.println("======================================");
			System.out.printf("%-15s%-30s%n", "Teacher ID", "Full Name");
			System.out.println("--------------------------------------");

			while (rs.next()) {
				int teacherId = rs.getInt("teacher_id");
				String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
				System.out.printf("%-15d%-30s%n", teacherId, fullName);
			}

			System.out.println("======================================");

		} catch (SQLException e) {
			System.out.println("Failed to load teacher details: " + e.getMessage());
		}
	}

	public void printAllSubjectDetails() {
		String query = "SELECT subject_id, name FROM ems.subject";

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			System.out.println("======================================");
			System.out.println("         Subject Details");
			System.out.println("======================================");
			System.out.printf("%-15s%-30s%n", "Subject ID", "Subject Name");
			System.out.println("--------------------------------------");

			while (rs.next()) {
				int subjectId = rs.getInt("subject_id");
				String subjectName = rs.getString("name");
				System.out.printf("%-15d%-30s%n", subjectId, subjectName);
			}

			System.out.println("======================================");

		} catch (SQLException e) {
			System.out.println("Failed to load subject details: " + e.getMessage());
		}
	}

	public boolean assignSubjectToTeacher(int teacherId, int subjectId) {
		String query = "INSERT INTO ems.teacher_subject_assignment (teacher_id, subject_id) VALUES (?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			pstmt.setInt(2, subjectId);

			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showAllSubjects() {
		String query = "SELECT subject_id, name, course_id FROM ems.subject";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			System.out.println("==============================================================");
			System.out.println("                     All Subject Details");
			System.out.println("==============================================================");
			System.out.printf("%-15s%-30s%-15s%n", "Subject ID", "Subject Name", "Course ID");
			System.out.println("--------------------------------------------------------------");

			while (rs.next()) {
				int subjectId = rs.getInt("subject_id");
				String subjectName = rs.getString("name");
				int courseId = rs.getInt("course_id");
				System.out.printf("%-15d%-30s%-15d%n", subjectId, subjectName, courseId);
			}

			System.out.println("==============================================");

		} catch (SQLException e) {
			System.out.println("Failed to load subject details: " + e.getMessage());
		}
	}

	public void getSubjectsByTeacherId(int teacherId) {
		String query = "SELECT s.subject_id, s.name, s.course_id " + "FROM ems.subject s "
				+ "JOIN ems.teacher_subject_assignment tsa ON s.subject_id = tsa.subject_id "
				+ "WHERE tsa.teacher_id = ?";
		int count = 0;

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if (count == 0) {
					System.out.println("==============================================");
					System.out.println("         Subjects Assigned to Teacher");
					System.out.println("==============================================");
					System.out.printf("%-15s%-30s%-15s%n", "Subject ID", "Subject Name", "Course ID");
					System.out.println("--------------------------------------------------------------");
				}

				int subjectId = rs.getInt("subject_id");
				String name = rs.getString("name");
				int courseId = rs.getInt("course_id");

				System.out.printf("%-15d%-30s%-15d%n", subjectId, name, courseId);
				count++;
			}

			if (count > 0) {
				System.out.println("==============================================");
			} else {
				System.out.println("No subjects assigned to this teacher.");
			}

		} catch (SQLException e) {
			System.out.println("Failed to retrieve subjects: " + e.getMessage());
		}

	}

	public void printTeacherDetailsById(int teacherId) {
		String query = "SELECT teacher_id, user_id, qualification, experience FROM ems.teacher WHERE teacher_id = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("Teacher Details:");
				System.out.println("Teacher ID: " + rs.getInt("teacher_id"));
				System.out.println("User ID: " + rs.getInt("user_id"));
				System.out.println("Qualification: " + rs.getString("qualification"));
				System.out.println("Experience: " + rs.getInt("experience") + " years");
			} else {
				System.out.println("No teacher found with ID: " + teacherId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
