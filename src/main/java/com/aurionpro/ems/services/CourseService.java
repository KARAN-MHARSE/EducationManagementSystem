package com.aurionpro.ems.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.dao.ICourseDao;
import com.aurionpro.ems.dao.implementation.CourseDaoImplementation;
import com.aurionpro.ems.exceptions.CustomException;
import com.aurionpro.ems.exceptions.DataValidationException;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Subject;
import com.aurionpro.ems.utils.DataValidationUtil;

public class CourseService {
	private ICourseDao courseDao;

	public CourseService(ICourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public void addNewCourse(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter the course name:");
		String courseName = scanner.nextLine();
		
		if(!DataValidationUtil.isValidString(courseName)) throw new DataValidationException("Enter valid course name");
		
		System.out.println("Enter the description:");
		String description = scanner.nextLine();
	
		
//		System.out.println("Enter the year of study");
//		int yearOfBatch = scanner.nextInt();
//		scanner.nextLine();
		
//		if(yearOfBatch < 1 || yearOfBatch > 5) throw new DataValidationException("Year of study must be between 1 and 5");
		
		Course course = new Course(courseName, description, 0, null, false);
		boolean isAdded = courseDao.addNewCourse(course);
		
		if(isAdded) System.out.println("Course successfully added");
		else throw new CustomException("Something went wrong, try again!");
		
		
		
	}

	public void viewAllCourses() {
		courseDao.printAllCourseDetails();
		
	}

	public void addSubjectsInCourse(Scanner scanner) {
		System.out.println("Enter the course id");
		int courseId = scanner.nextInt();
		scanner.nextLine();
		
		if(courseId < 0 ) throw new DataValidationException("Enter valid course id");
		
		System.out.println("Enter the subject name");
		String subjectName = scanner.nextLine();
		
		if(subjectName == null ||subjectName.isBlank()) throw new DataValidationException("Enter valid subject name");		
		
		boolean isSubjectAdded = courseDao.addSubjectToCourse(subjectName, courseId);
		
		if(isSubjectAdded) System.out.println("Subject successfully added");
		else System.out.println("Something went wrong, try again");
		
	}


	public void viewSubjectsOfCourse(Scanner scanner) throws SQLException {
		System.out.println("Enter the course id");
		int courseId = scanner.nextInt();
		scanner.nextLine();
		
		if(courseId < 0) throw new DataValidationException("Enter valid course id");
		
		List<Subject> subjects=courseDao.getSubjectsInCourse(courseId);
		
		if(subjects.isEmpty()) {
			System.err.println("Subjects not found");
			return;
		}
		
		
		
		System.out.println("+------------+----------------------+");
		System.out.println("| Subject ID | Subject Name         |");
		System.out.println("+------------+----------------------+");
		
		

		for(Subject subject:subjects) {
			int subjectId = subject.getSubjectId();
			String subjectName = subject.getSubjectName();

			System.out.printf("| %-10d | %-20s |\n", subjectId, subjectName);
		}

		System.out.println("+------------+----------------------+");

	}

	public void viewACoursesById(Scanner scanner) {
		System.out.println("Enter the course id");
		int courseId = scanner.nextInt();
		scanner.nextLine();
		
		if(courseId < 0) throw new DataValidationException("Enter  valid course id");
		
		Course course = courseDao.getCourseById(courseId);
		
		System.out.println(course);
		
	}

	
	public void deleteCourseById(Scanner scanner) {
		System.out.println("Enter the course id");
		int courseId = scanner.nextInt();
		scanner.nextLine();
		
		if(courseId < 0) throw new DataValidationException("Enter  valid course id");
		
		boolean isDeleted =  courseDao.deleteCourse(courseId);
		
		if(isDeleted) System.out.println("Course successfully deleted");
		else System.out.println("Course already deleted");
		
	}

}
