package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.EmsApplication;
import com.aurionpro.ems.dao.implementation.CourseDaoImplementation;
import com.aurionpro.ems.services.CourseService;
import com.aurionpro.ems.services.FeesServices;
import com.aurionpro.ems.services.StudentService;
import com.aurionpro.ems.services.TeacherService;

public class AdminController {
	private StudentService studentService;
	private TeacherService teacherService;
	private CourseService courseService;
	private FeesServices feesServices;

	public AdminController(StudentService studentService, TeacherService teacherService) {
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.courseService = new CourseService(new CourseDaoImplementation());
		this.feesServices = new FeesServices();
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
					printFeesManagementMenu(scanner);
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
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

	}

	public void printCourseManagementMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " + "\n1. View All Courses " + "\n2. Add New Course "
					+ "\n3. Add Subjects in A Course  " + "\n4. View Subjects of A Course" + "\n5. Search A Course "
					+ "\n6. Delete A Course " + "\n7. View Subjects of A Course " + "\n9. Go Back");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

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
					courseService.viewSubjectsOfCourse(scanner);
					break;
				case 8:
					isContinue = false;
					break;
				default:
					System.out.println("Enter valid choice code");
					break;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}

	}

	public void printTeacherManageMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " + "\n1. Add new teacher " + "\n2. Show all teachers "
					+ "\n3. Assign subject to teacher " + "\n4. Show all subjects assign to a teacher"
					+ "\n5. Search a teacher " + "\n6. Go Back");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					teacherService.addNewTeacher(scanner);
					break;
				case 2:
					teacherService.printAllTeacherDetails(scanner);
					break;
				case 3:
					teacherService.assignSubject(scanner);
					break;
				case 4:
					teacherService.showSubjectsOfTeacher(scanner);
					break;
				case 5:
					teacherService.searchATeacher(scanner);
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

	public void printStudentManageMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " + "\n1. Add new student " + "\n2. Show all students "
					+ "\n3. Search a student  " + "\n4. Delete a student"
					+ "\n5. Assign a course to student \n6. View Assigned courses of a student " + "\n7. Go Back");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					studentService.addNewStudent(scanner);
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
					studentService.assignCourseToStudent(scanner);
					break;
				case 6:
					studentService.viewCourseBYId(scanner);
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
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
	}

	public void printFeesManagementMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("Enter choice code " + "\n1. View Total Paid Fees" + "\n2. View Total Pending Fees "
					+ "\n3. View Fees By Student  " + "\n4. View Fees By Course"
					+ "\n5. Update fees of a course \n6. Total Earning " + "\n7. Go Back");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					feesServices.getStudentsWithTotalPaidFees();
					break;
				case 2:
					feesServices.getStudentsWithPendingFees();
					break;
				case 3:
					feesServices.viewFeeByStudentId(scanner);
					break;
				case 4:
					feesServices.viewCourseFeeById(scanner);
					break;
				case 5:
					feesServices.updateFeeById(scanner);
					break;
				case 6:
					feesServices.getTotalEarning();
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
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}

	}

}
