package com.aurionpro.ems.dao.implementations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.ems.dao.ICourseDao;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.model.Course;

public class CourseDaoImpl implements ICourseDao {

	Connection connection;

	public CourseDaoImpl() {
		connection = Database.getConnection();
	}

	@Override
	public boolean addNewCourse(Course course) {
		try (CallableStatement callableStatement = connection.prepareCall("{call ems.AddNewCourse(?,?,?,?)}")) {

			callableStatement.setString(1, course.getName());
			callableStatement.setString(2, course.getDescription());
			callableStatement.setInt(3, course.getCourseYear());
			if (course.getCreatedAt() != null) {
				callableStatement.setDate(4, new java.sql.Date(course.getCreatedAt().getTime()));
			} else {
				callableStatement.setNull(4, java.sql.Types.DATE);
			}
			int updates = callableStatement.executeUpdate();
			return updates > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void showAllCourses() {
		String query = "SELECT * FROM ems.course";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			System.out.println("===========================================================================");
			System.out.println("                             Course Details");
			System.out.println("===========================================================================");
			System.out.printf("%-12s%-25s%-30s%-15s%n", "Course ID", "Course Name", "Description", "Course Year");
			System.out.println("---------------------------------------------------------------------------");

			while (resultSet.next()) {
				int courseId = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				int courseYear = resultSet.getInt(4);

				// Wrap description if it's too long
				String[] wrappedDescription = wrapText(description, 30);

				// Print first line with all columns
				System.out.printf("%-12d%-25s%-30s%-15d%n", courseId, name, wrappedDescription[0], courseYear);

				// Print remaining lines of the wrapped description
				for (int i = 1; i < wrappedDescription.length; i++) {
					System.out.printf("%-12s%-25s%-30s%-15s%n", "", "", wrappedDescription[i], "");
				}
			}

			System.out.println("===========================================================================");

		} catch (SQLException e) {
			System.out.println("Failed to load course details: " + e.getMessage());
		}
	}

	// Helper method to wrap text into lines of a given width
	private String[] wrapText(String text, int width) {
		List<String> lines = new ArrayList<>();
		for (int i = 0; i < text.length(); i += width) {
			lines.add(text.substring(i, Math.min(i + width, text.length())));
		}
		return lines.toArray(new String[0]);
	}

	public void printAllCourseDetails() {
		String query = "SELECT course_id, name FROM ems.course";

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			System.out.println("======================================");
			System.out.println("           Course Details");
			System.out.println("======================================");
			System.out.printf("%-15s%-30s%n", "Course ID", "Course Name");
			System.out.println("--------------------------------------");

			while (rs.next()) {
				int courseId = rs.getInt("course_id");
				String courseName = rs.getString("name");
				System.out.printf("%-15d%-30s%n", courseId, courseName);
			}

			System.out.println("======================================");

		} catch (SQLException e) {
			System.out.println("Failed to load course details: " + e.getMessage());
		}
	}

	public boolean addSubjectToCourse(String subjectName, int courseId) {
		try (CallableStatement callableStatement = connection.prepareCall("{call ems.AddNewSubject(?,?)}")) {

			callableStatement.setString(1, subjectName);
			callableStatement.setInt(2, courseId);
			int updates = callableStatement.executeUpdate();
			return updates > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void deleteCourse(int courseId) {
		try (CallableStatement callableStatement = connection.prepareCall("{call ems.DeleteCourse(?,?)}")) {
			callableStatement.setInt(1, courseId); // Input parameter
			callableStatement.registerOutParameter(2, Types.VARCHAR); // Output parameter
			callableStatement.execute();

			String message = callableStatement.getString(2);
			System.out.println("Result: " + message);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewStudentsEnrolled(int courseId) {
	    String query = "SELECT s.student_id, s.user_id, CONCAT(u.first_name, ' ', u.last_name) AS full_name, " +
	                   "u.email " +
	                   "FROM ems.student_course sc " +
	                   "JOIN ems.student s ON sc.student_id = s.student_id " +
	                   "JOIN ems.user u ON s.user_id = u.user_id " +
	                   "WHERE sc.course_id = ?";

	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, courseId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            boolean hasData = false;

	            System.out.println("===============================================================");
	            System.out.println("         Students Enrolled in Course ID: " + courseId);
	            System.out.println("===============================================================");
	            System.out.printf("%-12s%-12s%-25s%-30s%n", "Student ID", "User ID", "Full Name", "Email");
	            System.out.println("---------------------------------------------------------------");

	            while (rs.next()) {
	                hasData = true;
	                int studentId = rs.getInt("student_id");
	                int userId = rs.getInt("user_id");
	                String fullName = rs.getString("full_name");
	                String email = rs.getString("email");

	                System.out.printf("%-12d%-12d%-25s%-30s%n", studentId, userId, fullName, email);
	            }

	            if (!hasData) {
	                System.out.println("No students enrolled in this course.");
	            } else {
	                System.out.println("===============================================================");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Failed to retrieve enrolled students: " + e.getMessage());
	    }
	}


	public void viewSubjectsInCourse(int courseId) {
		String query = "SELECT s.subject_id, s.name FROM ems.subject s JOIN ems.course c ON s.course_id = c.course_id WHERE s.course_id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, courseId);

			try (ResultSet rs = stmt.executeQuery()) {
				System.out.println("+------------+----------------------+");
				System.out.println("| Subject ID | Subject Name         |");
				System.out.println("+------------+----------------------+");

				while (rs.next()) {
					int subjectId = rs.getInt("subject_id");
					String subjectName = rs.getString("name");

					System.out.printf("| %-10d | %-20s |\n", subjectId, subjectName);
				}

				System.out.println("+------------+----------------------+");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
