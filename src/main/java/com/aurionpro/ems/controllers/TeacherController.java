package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.services.TeacherService;
import com.aurionpro.ems.utils.DataValidationUtil;

public class TeacherController {
	
	private TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void printTeacherManagementMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			
			try {
				int choice = DataValidationUtil.checkFormatInt(scanner, "Enter choice code " + "\n1. Add new teacher " + "\n2. Show all teachers "
						+ "\n3. Assign subject to teacher " + "\n4. Show all subjects assign to a teacher"
						+ "\n5. Search a teacher " + "\n6. Remove A Subject"+ "\n7. Delete A Teacher"+ "\n8. Go Back");

				switch (choice) {
				case 1:
					teacherService.addNewTeacher(scanner);
					break;
				case 2:
					teacherService.printAllTeacherDetails();
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
					teacherService.RemoveASubject(scanner);
					break;
				case 7:
					teacherService.DeleteATeacher(scanner);
					break;
				case 8:
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
