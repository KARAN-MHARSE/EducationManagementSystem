package com.aurionpro.ems.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.ems.dao.ITeacherDao;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.models.Subject;
import com.aurionpro.ems.models.Teacher;
import com.aurionpro.ems.models.Teacher_Subject;
import com.mysql.cj.jdbc.CallableStatement;

public  class TeacherDaoImpl implements ITeacherDao{


	Connection connection;

	public TeacherDaoImpl() {
		connection = Database.getConnection();
	}

	public boolean addNewTeacher(Teacher teacher) {
		try (CallableStatement callableStatement = (CallableStatement) connection.prepareCall("{call ems.AddNewTeacher(?,?,?,?,?,?,?,?,?,?)}")) {

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
	

	public List<Teacher> getAllTeachers() {
		List<Teacher> teacherDetails = new ArrayList<Teacher>();

		String query = "SELECT t.teacher_id, u.first_name, u.last_name, u.email, t.qualification, t.experience FROM ems.teacher t "
				+ "JOIN ems.user u ON t.user_id = u.user_id where u.isActive=true";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				int teacherId = resultSet.getInt("teacher_id");
				String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String qualification = resultSet.getString("qualification");
				int experience = resultSet.getInt("experience");

				teacher.setTeacherId(teacherId);
				teacher.setEmail(email);
				teacher.setFirstName(fullName);
				teacher.setQualification(qualification);
				teacher.setExperience(experience);
				teacherDetails.add(teacher);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacherDetails;
	}

	public List<Teacher> getAllTeacherDetails() {
		List<Teacher> teacherDetails = new ArrayList<Teacher>();
		String query = "SELECT t.teacher_id, u.first_name, u.last_name " + "FROM ems.teacher t "
				+ "JOIN ems.user u ON t.user_id = u.user_id where u.isActive=true";

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Teacher teacher = new Teacher();
				int teacherId = rs.getInt("teacher_id");

				teacher.setFirstName(rs.getString("first_name"));
				teacher.setLastName(rs.getString("last_name"));
				teacher.setTeacherId(teacherId);

				teacherDetails.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherDetails;
	}

	public List<Subject> getAllSubjectDetails() {
		List< Subject> subjectDetails = new ArrayList<Subject>();
		String query = "SELECT s.*\r\n" + "FROM ems.subject s\r\n"
				+ "JOIN ems.course c ON s.course_id = c.course_id\r\n" + "WHERE c.is_deleted = 0;\r\n";

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Subject details = new Subject();
				int subjectId = rs.getInt("subject_id");
				String subjectName = rs.getString("name");
				int courseId = rs.getInt("course_id");

				details.setSubjectId(subjectId);
				details.setSubjectName(subjectName);
				details.setCourseId(courseId);

				subjectDetails.add(details);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectDetails;
	}

	public boolean assignSubjectToTeacher(int teacherId, int subjectId) {
		String checkQuery = "SELECT 1 FROM ems.teacher_subject_assignment WHERE teacher_id = ? AND subject_id = ?";
		String insertQuery = "INSERT INTO ems.teacher_subject_assignment (teacher_id, subject_id) VALUES (?, ?)";

		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
				PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
			checkStmt.setInt(1, teacherId);
			checkStmt.setInt(2, subjectId);
			ResultSet rs = checkStmt.executeQuery();

			if (rs.next()) {
				System.out.println("This subject is already assigned to the teacher.");
				return false;
			}

			insertStmt.setInt(1, teacherId);
			insertStmt.setInt(2, subjectId);
			int rows = insertStmt.executeUpdate();

			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//	public void showAllSubjects() {
//		String query = "SELECT subject_id, name, course_id FROM ems.subject";
//
//		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
//
//			System.out.println("==============================================================");
//			System.out.println("                     All Subject Details");
//			System.out.println("==============================================================");
//			System.out.printf("%-15s%-30s%-15s%n", "Subject ID", "Subject Name", "Course ID");
//			System.out.println("--------------------------------------------------------------");
//
//			while (rs.next()) {
//				int subjectId = rs.getInt("subject_id");
//				String subjectName = rs.getString("name");
//				int courseId = rs.getInt("course_id");
//				System.out.printf("%-15d%-30s%-15d%n", subjectId, subjectName, courseId);
//			}
//
//			System.out.println("==============================================");
//
//		} catch (SQLException e) {
//			System.out.println("Failed to load subject details: " + e.getMessage());
//		}
//	}

	public List<Subject> getSubjectsByTeacherId(int teacherId) {
		List<Subject> subjectDetails = new ArrayList<Subject>();
		String query = "SELECT s.subject_id, s.name, s.course_id " + "FROM ems.subject s "
				+ "JOIN ems.teacher_subject_assignment tsa ON s.subject_id = tsa.subject_id "
				+ "WHERE tsa.teacher_id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Subject details = new Subject();

				int subjectId = rs.getInt("subject_id");
				String name = rs.getString("name");
				int courseId = rs.getInt("course_id");

				details.setSubjectId(subjectId);
				details.setSubjectName(name);
				details.setCourseId(courseId);

				subjectDetails.add(details);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectDetails;

	}

	public Teacher printTeacherDetailsById(int teacherId) {
		Teacher detail = new Teacher();
		
		
		String query = "SELECT t.teacher_id, u.first_name, u.last_name, u.email, t.qualification, t.experience FROM ems.teacher t "
				+ "JOIN ems.user u ON t.user_id = u.user_id where u.isActive=true and t.teacher_id=?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			ResultSet resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				int teacherID = resultSet.getInt("teacher_id");
				String email = resultSet.getString("email");
				String qualification = resultSet.getString("qualification");
				int experience = resultSet.getInt("experience");

				detail.setTeacherId(teacherId);
				detail.setEmail(email);
				detail.setFirstName(resultSet.getString("first_name"));
				detail.setLastName(resultSet.getString("last_name"));
				detail.setQualification(qualification);
				detail.setExperience(experience);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detail;
	}

	public boolean deleteATeacher(int teacherId) {
		String query = "UPDATE ems.user u SET u.isActive = false "
				+ "WHERE u.user_id = (SELECT user_id FROM ems.teacher WHERE teacher_id = ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Teacher_Subject> getTeacherSubjectDetails(List<Teacher_Subject> teacher_subject_Details) {
		String query = "SELECT ts.teacher_id, u.first_name, u.last_name, ts.subject_id, s.name\r\n"
				+ "FROM ems.teacher_subject_assignment ts\r\n"
				+ "JOIN ems.subject s ON ts.subject_id = s.subject_id\r\n"
				+ "JOIN ems.course c ON s.course_id = c.course_id AND c.is_deleted = false\r\n"
				+ "JOIN ems.teacher t ON t.teacher_id = ts.teacher_id\r\n"
				+ "JOIN ems.user u ON t.user_id = u.user_id\r\n" + "WHERE u.isActive = true;\r\n";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				Teacher_Subject details = new Teacher_Subject();

				int teacherID = resultSet.getInt("teacher_id");
				String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
				int subjectID = resultSet.getInt("subject_id");
				String subjectName = resultSet.getString("name");

				details.setSubjectID(subjectID);
				details.setSubjectName(subjectName);
				details.setTeacherID(teacherID);
				details.setTeacherName(fullName);

				teacher_subject_Details.add(details);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher_subject_Details;
	}

	public boolean removeSubject(int teacherId, int subjectId) {
		String query = "DELETE FROM ems.teacher_subject_assignment \r\n"
				+ "WHERE teacher_id = ? AND subject_id = ?;\r\n";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			pstmt.setInt(2, subjectId);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkTeacherId(int teacherId) {
		String query = "SELECT 1 FROM ems.teacher t " + "JOIN ems.user u ON t.user_id = u.user_id "
				+ "WHERE t.teacher_id = ? AND u.isActive = true";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, teacherId);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean checkSubjectId(int subjectId) {
		String query = "SELECT 1 FROM ems.subject s " + "JOIN ems.course c ON s.course_id = c.course_id "
				+ "WHERE s.subject_id = ? AND c.is_deleted = false";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, subjectId);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	

	

	

	

	

}