package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.services.TeacherService;

public class TeacherController {
	
	private TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void printTeacherManagementMenu(Scanner scanner) {
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
				scanner.nextLine();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

	}



}
