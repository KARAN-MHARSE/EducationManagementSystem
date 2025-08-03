package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.EmsApplication;
import com.aurionpro.ems.dao.implementation.CourseDaoImplementation;
import com.aurionpro.ems.services.CourseService;
import com.aurionpro.ems.services.FeesServices;
import com.aurionpro.ems.services.StudentService;
import com.aurionpro.ems.services.TeacherService;

public class AdminController {
	private StudentController studentController;
	private TeacherController teacherController;
	private CourseController courseController;
	private FeesController feesController;
	private DashboardController dashboardController;

	public AdminController(StudentController studentController, TeacherController teacherController,
			CourseController courseController, FeesController feesController,DashboardController dashboardController) {
		this.studentController = studentController;
		this.teacherController = teacherController;
		this.courseController = courseController;
		this.feesController = feesController;
		this.dashboardController = dashboardController;
		
	}

	public void showMenu(Scanner scanner) {
		System.out
				.println("👋 Welcome, Admin! You now have full access to manage users, courses, and system settings.");

		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " + "\n1. DashBoard " + "\n2. Student Management "
					+ "\n3. Teacher Management " + "\n4. Course Management" + "\n5. Fee Management \n6. Exit");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					dashboardController.printDashBoard();
					break;
				case 2:
					studentController.printStudentManageMenu(scanner);
					;
					break;
				case 3:
					teacherController.printTeacherManagementMenu(scanner);
					;
					break;
				case 4:
					courseController.printCourseManagementMenu(scanner);
					break;
				case 5:
					feesController.printFeesManagementMenu(scanner);
					break;
				case 6:
					isContinue = false;
					EmsApplication.currentUser = null;
					System.out.println("Successfully logged out,Thank you!");
					break;
				default:
					System.out.println("Enter valid choice code");
					break;
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
				scanner.nextLine();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

	}
}
