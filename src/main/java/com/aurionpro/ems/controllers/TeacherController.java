package com.aurionpro.ems.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;
import com.aurionpro.ems.dto.SubjectDetails;
import com.aurionpro.ems.dto.TeacherDetails;
import com.aurionpro.ems.dto.Teacher_Subject;
import com.aurionpro.ems.exceptions.IncorrectNameException;
import com.aurionpro.ems.exceptions.InvalidEmailException;
import com.aurionpro.ems.exceptions.InvalidMobileNumber;
import com.aurionpro.ems.exceptions.SubjectNotFoundException;
import com.aurionpro.ems.exceptions.TeacherNotFoundException;
import com.aurionpro.ems.model.Teacher;
import com.aurionpro.ems.services.TeacherService;
import com.aurionpro.ems.utils.DataValidator;

public class TeacherController {

//	private TeacherService teacherService;

	private TeacherService teacherService = new TeacherService();

	public TeacherController() {
		this.teacherService = new TeacherService();
	}

	public void showMenu(Scanner scanner) {

		while (true) {

			System.out.println("\n--- Teacher Management ---");
			System.out.println("1. View All Teachers");
			System.out.println("2. Add New Teacher");
			System.out.println("3. Assign Subjects");
			System.out.println("4. Show Subjects");
			System.out.println("5. Remove A Subject");
			System.out.println("6. Search A Teacher");
			System.out.println("7. Delete A Teacher (soft delete)");
			System.out.println("8. Go Back");

			int choice = DataValidator.checkFormatInt(scanner, "Enter your choice: ");
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1: {
				viewAllTeachers(scanner);
				break;
			}
			case 2:
				addNewTeacher(scanner);
				break;
			case 3:
				assignSubject(scanner);
				break;
			case 4:
				showSubjects(scanner);
				break;
			case 5:
				removeSubject(scanner);
				break;
			case 6:
				searchATeacher(scanner);
				break;
			case 7:
				DeleteTeacher(scanner);
				break;
			case 8:
				System.out.println("Exiting Teacher Management. Please visit again.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void showSubjects(Scanner scanner) {
		System.out.println("1. Display all subjects");
		System.out.println("2. Display subjects taught by a specific teacher");
		int choice = DataValidator.checkFormatInt(scanner, "Choose an Option:");

		switch (choice) {
		case 1:
			subjectDetails();
			break;
		case 2: {
			if (!TeacherDetails())
				return;
			int teacherId = DataValidator.checkFormatInt(scanner, "Enter Teacher ID:");
			try {
				if (!checkTeacherId(teacherId))
					throw new TeacherNotFoundException(teacherId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
			List<SubjectDetails> subjectDetails = new ArrayList<>();
			subjectDetails = teacherService.showSubjects(subjectDetails, teacherId);

			if (subjectDetails.isEmpty()) {
				System.out.println("No subjects assigned to this teacher.");
				break;
			}
			System.out.println("=======================================================");
			System.out.println("                 Subject Details");
			System.out.println("=======================================================");
			System.out.printf("| %-12s | %-30s | %-10s |\n", "Subject ID", "Subject Name", "Course ID");
			System.out.println("-------------------------------------------------------");

			for (SubjectDetails subject : subjectDetails) {
				System.out.printf("| %-12d | %-30s | %-10d |\n", subject.getSubjectID(), subject.getSubjectName(),
						subject.getCourseID());
			}

			System.out.println("=======================================================");
		}
		default:
			System.out.println("Invalid choice.");
			break;
		}
	}

	private void searchATeacher(Scanner scanner) {
		TeacherDetails();
		int teacherId = DataValidator.checkFormatInt(scanner, "Enter Teacher ID to view details:");
		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException(teacherId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		TeacherDetails detail = new TeacherDetails();
		detail = teacherService.searchATeacher(teacherId, detail);
		System.out.println("======================================");
		System.out.println("         Teacher Details");
		System.out.println("======================================");
		System.out.printf("%-20s: %d%n", "Teacher ID", detail.getTeacherID());
		System.out.printf("%-20s: %s%n", "Full Name", detail.getTeacherName());
		System.out.printf("%-20s: %s%n", "Email", detail.getTeacherEmail());
		System.out.printf("%-20s: %s%n", "Qualification", detail.getTeacherQualification());
		System.out.printf("%-20s: %d years%n", "Experience", detail.getTeacherExperience());
		System.out.println("======================================");

	}

	private void DeleteTeacher(Scanner scanner) {
		if (!TeacherDetails())
			return;
		int teacherId = DataValidator.checkFormatInt(scanner, "Enter Teacher ID to delete:");
		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException(teacherId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		boolean success = teacherService.DeleteATeacher(teacherId);
		System.out.println(success ? "Teacher Deleted successfully." : "Failed to delete teacher.");
	}

	private void removeSubject(Scanner scanner) {
		if (!Teacher_Subject_Details())
			return;
		int teacherId = DataValidator.checkFormatInt(scanner, "Enter Teacher ID to assign:");

		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException(teacherId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		int subjectId = DataValidator.checkFormatInt(scanner, "Enter subject ID to assign:");

		try {
			if (!checkSubjectId(subjectId))
				throw new SubjectNotFoundException(subjectId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		boolean success = teacherService.RemoveASubject(teacherId, subjectId);
		System.out.println(success ? "Subject Removed successfully." : "Failed to remove.");

	}

	private void assignSubject(Scanner scanner) {

		if (!TeacherDetails())
			return;

		if (!subjectDetails())
			return;

		int teacherId = DataValidator.checkFormatInt(scanner, "Enter Teacher ID to assign:");
		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException(teacherId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		
		int subjectId = DataValidator.checkFormatInt(scanner, "Enter subject ID to assign:");
		try {
			if (!checkSubjectId(subjectId))
				throw new SubjectNotFoundException(subjectId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		boolean success = teacherService.assignSubject(teacherId, subjectId);
		System.out.println(success ? "Subject assigned successfully." : "Failed to assign subject.");
	}

	private void viewAllTeachers(Scanner scanner) {
		List<TeacherDetails> teacherDetails = new ArrayList<>();
		teacherDetails = teacherService.ShowAllTeachers(teacherDetails);

		if (teacherDetails.isEmpty()) {
			System.out.println("No Teacher present in the system.");
			return;
		}
		System.out.println(
				"+------------+----------------------+---------------------------+----------------------+------------+");
		System.out.println(
				"| Teacher ID | Name                 | Email                     | Qualification        | Experience |");
		System.out.println(
				"+------------+----------------------+---------------------------+----------------------+------------+");
		for (TeacherDetails teacherDetail : teacherDetails)
			System.out.println(teacherDetail);
	}

	public void addNewTeacher(Scanner scanner) {
		int count = DataValidator.checkFormatInt(scanner, "Enter number of teachers to add:");

		for (int i = 0; i < count; i++) {
			String firstName = "", lastName = "", email = "", city = "", qualification = "";
			long mobileNumber = 0;
			int experience = 0;
			Gender gender = Gender.Male; 
			Date createdAt = new Date();
			Role role = Role.Teacher;

			try {
				System.out.println("Enter teacher's first name:");
				firstName = scanner.nextLine();
				if (!DataValidator.isValidName(firstName))
					throw new IncorrectNameException();

				System.out.println("Enter teacher's last name:");
				lastName = scanner.nextLine();
				if (!DataValidator.isValidName(lastName))
					throw new IncorrectNameException();

				System.out.println("Enter mobile number:");
				mobileNumber = Long.parseLong(scanner.nextLine());
				if (!DataValidator.isValidMobileNumber(mobileNumber))
					throw new InvalidMobileNumber();

				System.out.println("Enter email:");
				email = scanner.nextLine();
				if (!DataValidator.isValidEmail(email))
					throw new InvalidEmailException();

				System.out.println("1. Male");
				System.out.println("2. Female");
				int genderChoice = DataValidator.checkFormatInt(scanner, "Select gender:");
				gender = (genderChoice == 2) ? Gender.Female : Gender.Male;

				System.out.println("Enter city:");
				city = scanner.nextLine();

				System.out.println("Enter qualification:");
				qualification = scanner.nextLine();

				System.out.println("Enter experience (in years):");
				experience = Integer.parseInt(scanner.nextLine());

				Teacher teacher = new Teacher(firstName, lastName, mobileNumber, email, gender, city, role, createdAt,
						true, qualification, experience);

				boolean success = teacherService.addNewTeacher(teacher);
				System.out.println(success ? "Teacher added successfully." : "Failed to add teacher.");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private boolean TeacherDetails() {
		List<TeacherDetails> teacherDetails = new ArrayList<>();
		teacherDetails = teacherService.printAllTeacherDetails(teacherDetails);
		if (teacherDetails.isEmpty()) {
			System.out.println("No teachers present in the system");
			return false;
		}
		System.out.println("======================================");
		System.out.println("         Teacher Details");
		System.out.println("======================================");
		System.out.printf("%-15s%-30s%n", "Teacher ID", "Full Name");
		System.out.println("--------------------------------------");

		for (TeacherDetails teacherDetail : teacherDetails) {
			System.out.printf("%-15d%-30s%n", teacherDetail.getTeacherID(), teacherDetail.getTeacherName());
		}
		System.out.println("======================================");
		return true;
	}

	private boolean subjectDetails() {
		List<SubjectDetails> subjectDetails = new ArrayList<>();
		subjectDetails = teacherService.printAllSubjectDetails(subjectDetails);
		if (subjectDetails.isEmpty()) {
			System.out.println("No subjects present in the system");
			return false;
		}
		System.out.println("==============================================================");
		System.out.println("                      Subject Details");
		System.out.println("==============================================================");
		System.out.printf("| %-12s | %-30s | %-10s |\n", "Subject ID", "Subject Name", "Course ID");
		System.out.println("--------------------------------------------------------------");

		for (SubjectDetails subject : subjectDetails) {
			System.out.printf("| %-12d | %-30s | %-10d |\n", subject.getSubjectID(), subject.getSubjectName(),
					subject.getCourseID());
		}
		return true;
	}

	private boolean Teacher_Subject_Details() {
		List<Teacher_Subject> teacher_subject_Details = new ArrayList<>();
		teacher_subject_Details = teacherService.getTeacherSubjectDetails(teacher_subject_Details);
		if (teacher_subject_Details.isEmpty()) {
			System.out.println("No teacher has been assigned any subject yet.");
			return false;
		}
		System.out.println("======================================================================");
		System.out.println("                    Teacher - Subject Assignments");
		System.out.println("======================================================================");
		System.out.printf("| %-12s | %-25s | %-12s | %-25s |\n", "Teacher ID", "Teacher Name", "Subject ID",
				"Subject Name");
		System.out.println("----------------------------------------------------------------------");

		for (Teacher_Subject detail : teacher_subject_Details) {
			System.out.printf("| %-12d | %-25s | %-12d | %-25s |\n", detail.getTeacherID(), detail.getTeacherName(),
					detail.getSubjectID(), detail.getSubjectName());
		}

		System.out.println("======================================================================");

		return true;
	}

	private boolean checkTeacherId(int teacherId) {
		return teacherService.checkTeacherId(teacherId);
	}

	private boolean checkSubjectId(int subjectId) {
		return teacherService.checkSubjectId(subjectId);
	}
}
