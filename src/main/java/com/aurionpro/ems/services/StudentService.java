package com.aurionpro.ems.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.exceptions.StudentNotFoundException;
import com.aurionpro.ems.models.Student;
import com.aurionpro.ems.utils.UserUtil;

public class StudentService {
	private final IStudentDao studentDao;

	public StudentService(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void printAllStudents() throws SQLException {
		List<Student> students = studentDao.getAllStudents();
		if (students.isEmpty())
			throw new StudentNotFoundException();

		for (Student student : students) {
			if (student.getIsActive()) {
				System.out.println(student);

			}
		}

	}

	public void deleteStudent(Scanner scanner) throws SQLException {
		printAllStudents();

		System.out.println("Enter the student id to delete");
		int studentId = scanner.nextInt();
		scanner.nextLine();

		boolean isDeleted = UserUtil.deleteUserById(studentId);
		if (!isDeleted) {
			throw new StudentNotFoundException();
		}
		System.out.println("Student deleted successfully.");
	}

	public void getStudent(Scanner scanner) throws SQLException {

		showStudentSearchMenu(scanner);

	}

	public void showStudentSearchMenu(Scanner scanner) throws SQLException {
		while (true) {
			System.out.println("\n--- Search Student Menu ---");
			System.out.println("1. Search by Student ID");
			System.out.println("2. Search by Roll Number");
			System.out.println("3. Search by Mobile Number");
			System.out.println("4. Search by Email");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {
			case 1: {
				System.out.print("Enter Student ID: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				Student studentById = studentDao.getStudentByID(id);
				printStudentIfExists(studentById);
				break;
			}
			case 2: {
				System.out.print("Enter Roll Number: ");
				int roll = scanner.nextInt();
				scanner.nextLine();
				Student studentByRoll = studentDao.getStudentByRollNumber(roll);
				printStudentIfExists(studentByRoll);
				break;
			}
			case 3: {
				System.out.print("Enter Mobile Number: ");
				long mobile = scanner.nextLong();
				scanner.nextLine();
				Student studentByMobile = studentDao.getStudentByMobileNumber(mobile);
				printStudentIfExists(studentByMobile);
				break;
			}
			case 4: {
				System.out.print("Enter Email: ");
				String email = scanner.nextLine();
				Student studentByEmail = studentDao.getStudentByEmail(email);
				printStudentIfExists(studentByEmail);
				break;
			}
			case 5:
				return;
			default:
				System.out.println("Invalid choice! Please select a valid option.");
			}
		}
	}

	private void printStudentIfExists(Student student) {
		if (student != null) {
			System.out.println("\nStudent Found:");
			System.out.println(student);
		} else {
			System.out.println("No student found with the given detail.");
		}
	}

}
