package com.aurionpro.ems.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import com.aurionpro.ems.dao.ICourseDao;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.exceptions.CourseNotFoundException;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Subject;
import com.aurionpro.ems.utils.ResultSetConversion;

public class CourseDaoImplementation implements ICourseDao {
	Connection connection;

	public CourseDaoImplementation() {
		connection = Database.getConnection();
	}

	public boolean addNewCourse(Course course) {
		String sql = "{call ems.AddNewCourse(?,?)}";
		course.setDescription(course.getDescription() == null ? "" : course.getDescription());

		try {
			CallableStatement callableStatement = connection.prepareCall(sql);

			callableStatement.setString(1, course.getName());
			callableStatement.setString(2, course.getDescription());

			int updates = callableStatement.executeUpdate();
			return updates > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Course> getAllCourses() {
		String query = "SELECT * FROM ems.course";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

//			System.out.println("Karan:" + resultSet.next());

			List<Course> courses = new ArrayList<Course>();

			while (resultSet.next()) {
				int courseId = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				int courseYear = resultSet.getInt(4);
				boolean isCourseDeleted = resultSet.getBoolean("is_deleted");

				Course course = new Course();
				course.setCourseId(courseId);
				course.setName(name);
				course.setDescription((description == null || !description.isBlank()) ? description : "");
				course.setCourseYear(courseYear);
				course.setDeleted(isCourseDeleted);

				courses.add(course);

			}
			if (courses.isEmpty())
				throw new CourseNotFoundException();

			return courses;

		} catch (SQLException e) {
			System.out.println("Failed to load course details: " + e.getMessage());
		}
		return null;
	}

	private String[] wrapText(String text, int width) {
		List<String> lines = new ArrayList();
		for (int i = 0; i < text.length(); i += width) {
			lines.add(text.substring(i, Math.min(i + width, text.length())));
		}
		return lines.toArray(new String[0]);
	}

	public void printAllCourseDetails() {
		List<Course> courses = getAllCourses();

		System.out.println("======================================");
		System.out.println("           Course Details");
		System.out.println("======================================");
		System.out.printf("%-15s%-30s%n", "Course ID", "Course Name");
		System.out.println("--------------------------------------");

		for (Course course : courses)
			if (!course.isDeleted()) {
				System.out.printf("| %-10d | %-20s |\n", course.getCourseId(), course.getName());
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

	public boolean deleteCourse(int courseId) {
		String sql = "update ems.course set is_deleted = true where course_id =? and is_deleted = false";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, courseId);

			int deletedRowsCount = statement.executeUpdate();

			return deletedRowsCount > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void viewStudentsEnrolled(int courseId) {
		String query = "SELECT s.student_id, s.user_id, CONCAT(u.first_name, ' ', u.last_name) AS full_name, "
				+ "u.email " + "FROM ems.student_course sc " + "JOIN ems.student s ON sc.student_id = s.student_id "
				+ "JOIN ems.user u ON s.user_id = u.user_id " + "WHERE sc.course_id = ?";

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

	public List<Subject> getSubjectsInCourse(int courseId) {
		String query = "SELECT s.subject_id, s.name FROM ems.subject s JOIN ems.course c ON s.course_id = c.course_id WHERE s.course_id = ? and c.is_deleted = false;";
		List<Subject> subjectList = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, courseId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Subject subject = new Subject();
					subject.setSubjectId(rs.getInt("subject_id"));
					subject.setSubjectName(rs.getString("name"));

					subjectList.add(subject);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjectList;
	}

	@Override
	public Course getCourseById(int courseId) {
		String sql = "select * from ems.course where course_id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, courseId);

			ResultSet set = statement.executeQuery();
			List<Course> courses = ResultSetConversion.convertCourseResultSetSetToStudentList(set);
			if (courses.isEmpty())
				throw new CourseNotFoundException();
			return courses.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
