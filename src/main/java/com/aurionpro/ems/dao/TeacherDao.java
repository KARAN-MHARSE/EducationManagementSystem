package com.aurionpro.ems.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		try {
//			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from ems.teacher");
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				System.out.println(resultset.getInt(1) + "\t" + resultset.getInt(2) + "\t" + resultset.getString(3)
						+ "\t" + resultset.getInt(4));
			}
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

	public List<Integer> getAllTeacherIds() {
		List<Integer> ids = new ArrayList<>();
		String query = "SELECT teacher_id FROM ems.teacher";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ids;
	}

	public List<Integer> getAllSubjectIds() {
		List<Integer> ids = new ArrayList<>();
		String query = "SELECT subject_id FROM ems.subject";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ids;
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

			System.out.println("Subject ID\tName\tCourse ID");
			while (rs.next()) {
				System.out
						.println(rs.getInt("subject_id") + "\t" + rs.getString("name") + "\t" + rs.getInt("course_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getSubjectsByTeacherId(int teacherId) {
		String query = "SELECT s.subject_id, s.name, s.course_id FROM ems.subject s JOIN ems.teacher_subject_assignment tsa ON s.subject_id = tsa.subject_id WHERE tsa.teacher_id = ?";
		int count = 0;

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("Subject ID\tName\tCourse ID");
			while (rs.next()) {
				int subjectId = rs.getInt("subject_id");
				String name = rs.getString("name");
				int courseId = rs.getInt("course_id");

				System.out.println(subjectId + "\t" + name + "\t" + courseId);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;

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
