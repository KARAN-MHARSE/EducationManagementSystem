package com.aurionpro.ems.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;
import com.aurionpro.ems.dao.TeacherDao;
import com.aurionpro.ems.model.Teacher;

public class TeacherService {

	private TeacherDao teacherDao;
	private List<Teacher> teachers = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);

	public TeacherService() {
		this.teacherDao = new TeacherDao();
	}

	public void addNewTeacher() {
//		TeacherDao teacherDao = new TeacherDao(); 

		System.out.println("Enter number of teachers to add:");
		int count = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < count; i++) {
			System.out.println("Enter teacher's first name:");
			String firstName = scanner.nextLine();

			System.out.println("Enter teacher's last name:");
			String lastName = scanner.nextLine();

			System.out.println("Enter mobile number:");
			long mobileNumber = Long.parseLong(scanner.nextLine());

			System.out.println("Enter email:");
			String email = scanner.nextLine();

			System.out.println("Select gender:");
			System.out.println("1. Male");
			System.out.println("2. Female");
			int genderChoice = Integer.parseInt(scanner.nextLine());
			Gender gender = (genderChoice == 2) ? Gender.Female : Gender.Male;

			System.out.println("Enter city:");
			String city = scanner.nextLine();

			System.out.println("Enter qualification:");
			String qualification = scanner.nextLine();

			System.out.println("Enter experience (in years):");
			int experience = Integer.parseInt(scanner.nextLine());

			Date createdAt = new Date();
			Role role = Role.Teacher;

			Teacher teacher = new Teacher(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, true,
					qualification, experience);

			teachers.add(teacher);

			boolean success = teacherDao.addNewTeacher(teacher);
			if (success) {
				System.out.println("Teacher added successfully.");
			} else {
				System.out.println("Failed to add teacher.");
			}
		}

		ShowAllTeachers();
	}

	public void ShowAllTeachers() {
		teacherDao.showAllTeachers();
	}

	public void assignSubject() {
		teacherDao.printAllTeacherDetails();
		teacherDao.printAllSubjectDetails();

		System.out.println("Enter Teacher ID to assign:");
		int teacherId = Integer.parseInt(scanner.nextLine());

		System.out.println("Enter Subject ID to assign:");
		int subjectId = Integer.parseInt(scanner.nextLine());

		boolean success = teacherDao.assignSubjectToTeacher(teacherId, subjectId);
		System.out.println(success ? "Subject assigned successfully." : "Failed to assign subject.");
	}

	public void showSubjects() {
		System.out.println("Choose an option:");
		System.out.println("1. Display all subjects");
		System.out.println("2. Display subjects taught by a specific teacher");
		int choice = Integer.parseInt(scanner.nextLine());

		if (choice == 1) {
			teacherDao.showAllSubjects();
		} else if (choice == 2) {
			teacherDao.printAllTeacherDetails();
			System.out.println("Enter Teacher ID:");
			int teacherId = Integer.parseInt(scanner.nextLine());

			teacherDao.getSubjectsByTeacherId(teacherId);
		} else {
			System.out.println("Invalid choice.");
		}
	}

	public void searchATeacher() {
		teacherDao.printAllTeacherDetails();

		System.out.println("Enter the Teacher ID to view details:");
		int teacherId = Integer.parseInt(scanner.nextLine());

		teacherDao.printTeacherDetailsById(teacherId);
	}

}
