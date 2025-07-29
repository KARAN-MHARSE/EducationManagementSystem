package com.aurionpro.ems.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.dao.implementations.CourseDaoImpl;
import com.aurionpro.ems.model.Course;

public class CourseServiceImpl implements ICourseService {

	Scanner scanner = new Scanner(System.in);

	private CourseDaoImpl courseDao;
	private List<Course> courses = new ArrayList<>();

	public CourseServiceImpl() {
		super();
		this.courseDao = new CourseDaoImpl();
	}

	@Override
	public void addNewCourse() {
		System.out.print("Enter course name: ");
		String name = scanner.nextLine();

		System.out.print("Enter course description: ");
		String description = scanner.nextLine();

		System.out.print("Enter course year: ");
		int courseYear = scanner.nextInt();

		Date createdAt = new Date();

		Course course = new Course(name, description, courseYear, createdAt);
		courses.add(course);

		boolean success = courseDao.addNewCourse(course);
		if (success) {
			System.out.println("Course added successfully.");
		} else {
			System.out.println("Failed to add course.");
		}
		showAllCourses();
	}

	@Override
	public void showAllCourses() {
		courseDao.showAllCourses();
	}

	@Override
	public void addSubjectsToCourse() {

		courseDao.printAllCourseDetails();
		
		System.out.print("Enter course ID to assign the subject to: ");
		int courseId = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter subject name: ");
		String subjectName = scanner.nextLine();

		boolean success = courseDao.addSubjectToCourse(subjectName, courseId);
		if (success) {
			System.out.println("Subject added to Course successfully.");
		} else {
			System.out.println("Failed to add Subject.");
		}

	}

	@Override
	public void deleteCourse() {
		courseDao.printAllCourseDetails();
		System.out.print("Enter course ID you would like to delete: ");
		int courseId = scanner.nextInt();
		courseDao.deleteCourse(courseId);
	}

	@Override
	public void viewCourse() {
		courseDao.printAllCourseDetails();
		System.out.print("Enter course ID of the course you would like to view: ");
		int courseId = scanner.nextInt();
		System.out.println("Choose an option:");
		System.out.println("1. View All Students Enrolled in the Course");
		System.out.println("2. View All Subjects in the Course");

		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			courseDao.viewStudentsEnrolled(courseId);
			break;
		case 2:
			courseDao.viewSubjectsInCourse(courseId);
			break;
		default:
			System.out.println("Invalid Choice");
			break;
		}
	}

}
