package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.EmsApplication;
import com.aurionpro.ems.models.User;
import com.aurionpro.ems.services.StudentService;

public class StudentController {
	StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
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
				scanner.nextLine();
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
	}

}