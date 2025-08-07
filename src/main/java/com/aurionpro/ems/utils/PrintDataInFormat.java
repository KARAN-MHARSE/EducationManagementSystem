package com.aurionpro.ems.utils;

import java.util.List;

import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Student;
import com.aurionpro.ems.models.Teacher;

public class PrintDataInFormat {

	public static void printStudents(List<Student> students) {
		String format = "| %-10s | %-10s | %-20s | %-15s | %-12s | %-6s | %-8s | %-8s | %-5s |%n";

//		System.out.println("=".repeat(115));
//		System.out.printf(format, "StudentID", "Roll No.", "Name", "Email", "Mobile No.", "Gender", "City", "Avg %",
//				"Year");
//		System.out.println("-".repeat(115));
		
		System.out.println(repeat("=", 115));
		System.out.printf(format, "StudentID", "Roll No.", "Name", "Email", "Mobile No.", "Gender", "City", "Avg %", "Year");
		System.out.println(repeat("-", 115));


		for (Student s : students) {
			if (s.getIsActive()) {
				String fullName = (s.getFirstName() + " " + s.getLastName()).trim();
				System.out.printf(format, s.getStudentId(), s.getRollNumber(), truncate(fullName, 20),
						truncate(s.getEmail(), 15), s.getMobileNumber(), s.getGender(), truncate(s.getCity(), 8),
						s.getAveragePercentage(), s.getYearOfStudy());
			}
		}

//		System.out.println("=".repeat(115));
		System.out.println(repeat("=",115));
	}

	public static void printStudent(Student s) {
		String format = "| %-10s | %-10s | %-20s | %-15s | %-12s | %-6s | %-8s | %-8s | %-5s |%n";

//		System.out.println("=".repeat(115));
//		System.out.printf(format, "StudentID", "Roll No.", "Name", "Email", "Mobile No.", "Gender", "City", "Avg %",
//				"Year");
//		System.out.println("-".repeat(115));
		System.out.println(repeat("=", 115));
		System.out.printf(format, "StudentID", "Roll No.", "Name", "Email", "Mobile No.", "Gender", "City", "Avg %", "Year");
		System.out.println(repeat("-", 115));

		if (s.getIsActive()) {
			String fullName = (s.getFirstName() + " " + s.getLastName()).trim();
			System.out.printf(format, s.getStudentId(), s.getRollNumber(), truncate(fullName, 20),
					truncate(s.getEmail(), 15), s.getMobileNumber(), s.getGender(), truncate(s.getCity(), 8),
					s.getAveragePercentage(), s.getYearOfStudy());

		}

//		System.out.println("=".repeat(115));
		System.out.println(repeat("=",115));
	}

	public static void printTeachers(List<Teacher> teachers) {
		String format = "| %-10s | %-20s | %-20s | %-12s | %-6s | %-10s | %-15s | %-10s |%n";

//		System.out.println("=".repeat(120));
//		System.out.printf(format, "TeacherID", "Name", "Email", "Mobile No.", "Gender", "City", "Qualification",
//				"Experience");
//		System.out.println("-".repeat(120));
		System.out.println(repeat("=",120));
		System.out.printf(format, "TeacherID", "Name", "Email", "Mobile No.", "Gender", "City", "Qualification",
				"Experience");
		System.out.println(repeat("-",120));
		

		for (Teacher t : teachers) {
			String fullName = (t.getFirstName() + " " + t.getLastName()).trim();
			System.out.printf(format, t.getTeacherId(), truncate(fullName, 20), truncate(t.getEmail(), 20),
					String.valueOf(t.getMobileNumber()), t.getGender(), truncate(t.getCity(), 10),
					truncate(t.getQualification(), 15), t.getExperience() + " yrs");
		}

//		System.out.println("=".repeat(120));
		System.out.println(repeat("=",120));
	}

	public static void printSingleTeacher(Teacher t) {
		String format = "| %-10s | %-20s | %-20s | %-12s | %-6s | %-10s | %-15s | %-10s |%n";

//		System.out.println("=".repeat(120));
//		System.out.printf(format, "TeacherID", "Name", "Email", "Mobile No.", "Gender", "City", "Qualification",
//				"Experience");
//		System.out.println("-".repeat(120));
		
		System.out.println(repeat("=",120));
		System.out.printf(format, "TeacherID", "Name", "Email", "Mobile No.", "Gender", "City", "Qualification",
				"Experience");
		System.out.println(repeat("-",120));

		String fullName = (t.getFirstName() + " " + t.getLastName()).trim();
		System.out.printf(format, t.getTeacherId(), truncate(fullName, 20), truncate(t.getEmail(), 20),
				String.valueOf(t.getMobileNumber()), t.getGender(), truncate(t.getCity(), 10),
				truncate(t.getQualification(), 15), t.getExperience() + " yrs");

//		System.out.println("=".repeat(120));
		System.out.println(repeat("=",120));
	}

	public static void printCourses(List<Course> courses) {
		String leftAlignFormat = "| %-8d | %-30s | %-30s | %-10d | %-10.2f |\n";

		System.out.format(
				"+----------+--------------------------------+--------------------------------+------------+------------+\n");
		System.out.format(
				"| CourseID | Course Name                    | Description                    | CourseDuration | Course Fee |\n");
		System.out.format(
				"+----------+--------------------------------+--------------------------------+------------+------------+\n");

		for (Course course : courses) {
			if (!course.isDeleted()) {
				System.out.format(leftAlignFormat, course.getCourseId(), course.getName(),
						truncate(course.getDescription(), 30), course.getcourseDuration(), course.getCourseFee());
			}
		}

		System.out.format(
				"+----------+--------------------------------+--------------------------------+------------+------------+\n");
	}

	private static String truncate(String str, int maxLength) {
		if (str == null)
			return "";
		return str.length() <= maxLength ? str : str.substring(0, maxLength - 3) + "...";
	}

	public static String repeat(String str, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

}
