package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.EmsApplication;
import com.aurionpro.ems.services.CourseService;
import com.aurionpro.ems.services.StudentService;
import com.aurionpro.ems.services.TeacherService;

public class AdminController {
	private StudentService studentService;
	private TeacherService teacherService;
	private CourseService courseService;

	
	public AdminController(StudentService studentService,TeacherService teacherService) {
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.courseService = new CourseService();
	}

	public  void showMenu(Scanner scanner) {
		System.out
				.println("👋 Welcome, Admin! You now have full access to manage users, courses, and system settings.");

		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " 
							+ "\n1. DashBoard " 
							+ "\n2. Student Management "
							+ "\n3. Teacher Management "
							+ "\n4. Course Management" 
							+ "\n5. Log out");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					System.out.println("Dashboard");
					break;
				case 2:
					printStudentManageMenu(scanner);
					break;
				case 3:
					printTeacherManageMenu(scanner);
					break;
				case 4:
					printCourseManagementMenu(scanner);
					break;
				case 5:
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
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

	}

	private void printCourseManagementMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " 
					+ "\n1. View All Courses " 
					+ "\n2. Add New Course "
					+ "\n3. Add Subjects in A Course  "
					+ "\n4. View Subjects of A Course" 
					+ "\n5. Search A Course "
					+ "\n6. Delete A Course "
					+ "\n7. View Subjects of A Course "
					+ "\n8. Go Back "
					+ "\n9. Go Back");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					System.out.println("AddNewSTudent");
					break;
				case 2:
					System.out.println("Show all STudent");
					studentService.printAllStudents();
					break;
				case 3:
					System.out.println("Search student");
					studentService.getStudent(scanner);
					break;
				case 4:
					studentService.deleteStudent(scanner);
					break;
				case 5:
					System.out.println("Assign course to student");
					break;
				case 6:
					System.out.println("View assigned course to a student");
					break;
				case 9:
					isContinue = false;
					break;
				default:
					System.out.println("Enter valid choice code");
					break;
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
	}

	private  void printTeacherManageMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " 
							+ "\n1. Add new teacher " 
							+ "\n2. Show all teachers "
							+ "\n3. Assign subject to teacher " 
							+ "\n4. Show all subjects" 
							+ "\n5. Search a teacher "
							+ "\n6. Go Back"
			);
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					System.out.println("AddNewTeacher");
					break;
				case 2:
					System.out.println("Show all Teacher");
					break;
				case 3:
					System.out.println("Assign subject to teacher");
					break;
				case 4:
					System.out.println("Show subjects");
					break;
				case 5:
					System.out.println("Search teacher by id or name");
					break;
				case 6:
					isContinue = false;
					break;
				default:
					System.out.println("Enter valid choice code");
					break;
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

	}

	private  void printStudentManageMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " + "\n1. Add new student " + "\n2. Show all students "
					+ "\n3. Search a student  " + "\n4. Delete a student" + "\n5. Assign a course to student \n6. View Assigned courses of a student "
					+ "\n7. Go Back");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					System.out.println("AddNewSTudent");
					break;
				case 2:
					System.out.println("Show all STudent");
					studentService.printAllStudents();
					break;
				case 3:
					System.out.println("Search student");
					studentService.getStudent(scanner);
					break;
				case 4:
					studentService.deleteStudent(scanner);
					break;
				case 5:
					System.out.println("Assign course to student");
					break;
				case 6:
					System.out.println("View assigned course to a student");
					break;
				case 7:
					isContinue = false;
					break;
				default:
					System.out.println("Enter valid choice code");
					break;
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
