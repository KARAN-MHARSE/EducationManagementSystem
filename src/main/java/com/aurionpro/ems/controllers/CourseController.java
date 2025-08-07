package com.aurionpro.ems.controllers;

import java.sql.SQLException;
import java.util.Scanner;

import com.aurionpro.ems.services.CourseService;
import com.aurionpro.ems.utils.DataValidationUtil;

public class CourseController {
	CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	public void printCourseManagementMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {

			try {
				int choice = DataValidationUtil.checkFormatInt(scanner, "Enter choice code " + "\n1. View All Courses " + "\n2. Add New Course "
						+ "\n3. Add Subjects in A Course  " + "\n4. View Subjects of A Course" + "\n5. Search A Course "
						+ "\n6. Delete A Course "  + "\n7. Reactivate A Course" + "\n8. Go Back");

				switch (choice) {
				case 1:
					courseService.viewAllCourses();
					break;
				case 2:
					courseService.addNewCourse(scanner);
					break;
				case 3:
					courseService.addSubjectsInCourse(scanner);
					break;
				case 4:
					courseService.viewSubjectsOfCourse(scanner);
					break;
				case 5:
					courseService.viewACoursesById(scanner);
					break;
				case 6:
					courseService.deleteCourseById(scanner);
					break;
				case 7:
					courseService.reactivateACourse(scanner);
					break;
				case 8:
					isContinue = false;
					break;
				default:
					System.out.println("Enter valid choice code");
					break;
				}
			} catch (NumberFormatException e) {
//				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());			}
			catch (Exception e) {
//				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		
		}

	}

}
