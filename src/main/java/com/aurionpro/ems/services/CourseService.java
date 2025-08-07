package com.aurionpro.ems.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.dao.ICourseDao;
import com.aurionpro.ems.dao.implementation.CourseDaoImplementation;
import com.aurionpro.ems.exceptions.CustomException;
import com.aurionpro.ems.exceptions.DataValidationException;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Subject;
import com.aurionpro.ems.utils.DataValidationUtil;
import com.aurionpro.ems.utils.PrintDataInFormat;

public class CourseService {
	private ICourseDao courseDao;

	public CourseService(ICourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public void addNewCourse(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter the course name:");
		String courseName = scanner.nextLine();

		if (!DataValidationUtil.isValidString(courseName))
			throw new DataValidationException("Enter valid course name");

		System.out.println("Enter the description:");
		String description = scanner.nextLine();

		double courseFee = DataValidationUtil.checkFormatFee(scanner, "Enter Course Fee");

		int duration = DataValidationUtil.checkFormatInt(scanner, "Enter duration of course :");

//		if(yearOfBatch < 1 || yearOfBatch > 5) throw new DataValidationException("Year of study must be between 1 and 5");

		Course course = new Course(courseName, description, duration, null, false, courseFee);
		int newCourseId = courseDao.addNewCourse(course);

		viewAllCourses();

		if (newCourseId != -1) {
			System.out.println("Course " + course.getName() + " is added with ID: " + newCourseId);
		}

		System.out.println("\nEnter the subject in that cousre ");
		String subjectName = scanner.nextLine();

		if (subjectName == null || subjectName.isEmpty())
			throw new DataValidationException("Enter valid subject name");

		boolean isSubjectAdded = courseDao.addSubjectToCourse(subjectName, newCourseId);

		if (isSubjectAdded)
			System.out.println("Subject successfully added");
		else
			System.out.println("Something went wrong, try again");

		if (newCourseId != -1)
			System.out.println("Course successfully added");
		else
			throw new CustomException("Something went wrong, try again!");

	}

	public void viewAllCourses() {
		courseDao.printAllCourseDetails();

	}

	public void addSubjectsInCourse(Scanner scanner) {
//		System.out.println("Enter the course id");
//		int courseId = scanner.nextInt();
//		scanner.nextLine();

		viewAllCourses();
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter the course id");

		if (courseId < 0)
			throw new DataValidationException("Enter valid course id");

		System.out.println("Enter the subject name");
		String subjectName = scanner.nextLine();

		if (subjectName == null || subjectName.isEmpty())
			throw new DataValidationException("Enter valid subject name");

		boolean isSubjectAdded = courseDao.addSubjectToCourse(subjectName, courseId);

		if (isSubjectAdded)
			System.out.println("Subject successfully added");
		else
			System.out.println("Something went wrong, try again");

	}

	public void viewSubjectsOfCourse(Scanner scanner) throws SQLException {
//		System.out.println("Enter the course id");
//		int courseId = scanner.nextInt();
//		scanner.nextLine();
		viewAllCourses();
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter the course id");

		if (courseId < 0)
			throw new DataValidationException("Enter valid course id");

		List<Subject> subjects = courseDao.getSubjectsInCourse(courseId);

		if (subjects.isEmpty()) {
			System.err.println("Subjects not found");
			return;
		}

		System.out.println("+------------+----------------------+");
		System.out.println("| Subject ID | Subject Name         |");
		System.out.println("+------------+----------------------+");

		for (Subject subject : subjects) {
			int subjectId = subject.getSubjectId();
			String subjectName = subject.getSubjectName();

			System.out.printf("| %-10d | %-20s |\n", subjectId, subjectName);
		}

		System.out.println("+------------+----------------------+");

	}

	public void viewACoursesById(Scanner scanner) {
//		System.out.println("Enter the course id");
//		int courseId = scanner.nextInt();
//		scanner.nextLine();
		viewAllCourses();
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter the course id");

		if (courseId < 0)
			throw new DataValidationException("Enter  valid course id");

		Course course = courseDao.getCourseById(courseId);
		List<Course> courses = Arrays.asList(course);

		PrintDataInFormat.printCourses(courses);

	}

	public void deleteCourseById(Scanner scanner) {
//		System.out.println("Enter the course id");
//		int courseId = scanner.nextInt();
//		scanner.nextLine();
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter the course id");

		if (courseId < 0)
			throw new DataValidationException("Enter  valid course id");

		boolean isDeleted = courseDao.deleteCourse(courseId);

		if (isDeleted)
			System.out.println("Course successfully deleted");
		else
			System.out.println("Course already deleted");

	}

	public void reactivateACourse(Scanner scanner) {
		if (!courseDao.viewAllDeleted()) {
			System.out.println("No deleted courses present.\n");
			return;
		}
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter the course id");
		boolean isDeleted = courseDao.reactivateACourse(courseId);

		if (isDeleted)
			System.out.println("Course successfully reactivated");
		else
			System.out.println("Course already active");
	}

}
