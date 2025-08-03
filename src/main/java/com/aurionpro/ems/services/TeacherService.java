package com.aurionpro.ems.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.builder.UserBuilder;
import com.aurionpro.ems.dao.ITeacherDao;
import com.aurionpro.ems.dao.implementation.TeacherDaoImpl;
import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;
import com.aurionpro.ems.exceptions.CustomException;
import com.aurionpro.ems.exceptions.DataValidationException;
import com.aurionpro.ems.exceptions.SubjectNotFoundException;
import com.aurionpro.ems.exceptions.TeacherNotFoundException;
import com.aurionpro.ems.models.Subject;
import com.aurionpro.ems.models.Teacher;
import com.aurionpro.ems.models.Teacher_Subject;
import com.aurionpro.ems.utils.DataValidationUtil;
import com.aurionpro.ems.utils.PrintDataInFormat;

public class TeacherService {

	private ITeacherDao teacherDao;

	public TeacherService() {
		this.teacherDao = new TeacherDaoImpl();
	}

	public void addNewTeacher(Scanner scanner) {
		int count = DataValidationUtil.checkFormatInt(scanner, "Enter number of teachers to add:");

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
				if (!DataValidationUtil.isValidString(firstName))
					throw new CustomException("Enter valid first name");

				System.out.println("Enter teacher's last name:");
				lastName = scanner.nextLine();
				if (!DataValidationUtil.isValidString(lastName))
					throw new CustomException("Enter valid last name");

				System.out.println("Enter mobile number:");
				mobileNumber = Long.parseLong(scanner.nextLine());
				if (!DataValidationUtil.isValidMobileNumber(mobileNumber))
					throw new CustomException("Enter valid mobile number");

				System.out.println("Enter email:");
				email = scanner.nextLine();
				if (!DataValidationUtil.isValidEmail(email))
					throw new CustomException("Enter valid email");

				System.out.println("1. Male");
				System.out.println("2. Female");
				int genderChoice = DataValidationUtil.checkFormatInt(scanner, "Select gender:");
				gender = (genderChoice == 2) ? Gender.Female : Gender.Male;

				System.out.println("Enter city:");
				city = scanner.nextLine();

				System.out.println("Enter qualification:");
				qualification = scanner.nextLine();

				System.out.println("Enter experience (in years):");
				experience = Integer.parseInt(scanner.nextLine());

				UserBuilder builder = new UserBuilder();
				Teacher teacher = builder.setFirstName(firstName).setLastName(lastName).setMobileNumber(mobileNumber)
						.setEmail(email).setGender(gender).setCity(city).setRole(role).setCreatedAt(null)
						.setFirstLogin(true).setQualification(qualification).setExperience(experience).getTeacher();

				boolean success = teacherDao.addNewTeacher(teacher);
				System.out.println(success ? "Teacher added successfully." : "Failed to add teacher.");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void printAllTeacherDetails(Scanner scanner) {
		List<Teacher> teacherDetails = teacherDao.getAllTeacherDetails();
		if (teacherDetails.isEmpty()) {
			System.out.println("No teachers present in the system");
			return;
		}
		
			PrintDataInFormat.printTeachers(teacherDetails);
		
	}

	public void assignSubject(Scanner scanner) {
		if(!printAllTeachers()) throw new TeacherNotFoundException();
		
		int teacherId = DataValidationUtil.checkFormatInt(scanner, "Enter Teacher ID to assign:");
		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		if(!printAllSubjects()) throw new SubjectNotFoundException();

		int subjectId = DataValidationUtil.checkFormatInt(scanner, "Enter subject ID to assign:");
		try {
			if (!checkSubjectId(subjectId))
				throw new SubjectNotFoundException(subjectId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		boolean success = teacherDao.assignSubjectToTeacher(teacherId, subjectId);
		System.out.println(success ? "Subject assigned successfully." : "Failed to assign subject.");
	}

//	public List<Teacher> printAllTeacherDetails(Scanner scanner) {
//		return teacherDao.printAllTeacherDetails(teacherDetails);
//	}

//	public List<Subject> printAllSubjectDetails(Scanner scanner) {
//		return teacherDao.printAllSubjectDetails(subjectDetails);
//	}

	public void showSubjectsOfTeacher(Scanner scanner) {
		int teacherId = DataValidationUtil.checkFormatInt(scanner, "Enter Teacher ID:");
		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		List<Subject> subjectDetails =teacherDao.getSubjectsByTeacherId( teacherId);

		if (subjectDetails.isEmpty()) {
			System.out.println("No subjects assigned to this teacher.");
			return;
		}
		System.out.println("=======================================================");
		System.out.println("                 Subject Details");
		System.out.println("=======================================================");
		System.out.printf("| %-12s | %-30s | %-10s |\n", "Subject ID", "Subject Name", "Course ID");
		System.out.println("-------------------------------------------------------");

		for (Subject subject : subjectDetails) {
			System.out.printf("| %-12d | %-30s | %-10d |\n", subject.getSubjectId(), subject.getSubjectName(),
					subject.getCourseId());
		}

		System.out.println("=======================================================");
	
	}

	public void searchATeacher(Scanner scanner) {
		int teacherId = DataValidationUtil.checkFormatInt(scanner, "Enter Teacher ID to view details:");
		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		Teacher detail = teacherDao.printTeacherDetailsById(teacherId);
		if (detail == null)
			throw new TeacherNotFoundException();

		PrintDataInFormat.printSingleTeacher(detail);
	}

	public void DeleteATeacher(Scanner scanner) {
		int teacherId = DataValidationUtil.checkFormatInt(scanner, "Enter Teacher ID to delete:");
		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		boolean success = teacherDao.deleteATeacher(teacherId);
		System.out.println(success ? "Teacher Deleted successfully." : "Failed to delete teacher.");

	}

	public void RemoveASubject(Scanner scanner) {
		int teacherId = DataValidationUtil.checkFormatInt(scanner, "Enter Teacher ID to assign:");

		try {
			if (!checkTeacherId(teacherId))
				throw new TeacherNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		int subjectId = DataValidationUtil.checkFormatInt(scanner, "Enter subject ID to assign:");

		try {
			if (!checkSubjectId(subjectId))
				throw new SubjectNotFoundException(subjectId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		boolean success = teacherDao.removeSubject(teacherId, subjectId);
		System.out.println(success ? "Subject Removed successfully." : "Failed to remove.");

	}

//	public List<Teacher_Subject> getTeacherSubjectDetails(List<Teacher_Subject> teacher_subject_Details) {
//		List<Teacher_Subject> teacher_subject_Details = new ArrayList<>();
//		teacher_subject_Details = teacherService.getTeacherSubjectDetails(teacher_subject_Details);
//		if (teacher_subject_Details.isEmpty()) {
//			System.out.println("No teacher has been assigned any subject yet.");
//			return false;
//		}
//		System.out.println("======================================================================");
//		System.out.println("                    Teacher - Subject Assignments");
//		System.out.println("======================================================================");
//		System.out.printf("| %-12s | %-25s | %-12s | %-25s |\n", "Teacher ID", "Teacher Name", "Subject ID",
//				"Subject Name");
//		System.out.println("----------------------------------------------------------------------");
//
//		for (Teacher_Subject detail : teacher_subject_Details) {
//			System.out.printf("| %-12d | %-25s | %-12d | %-25s |\n", detail.getTeacherID(), detail.getTeacherName(),
//					detail.getSubjectID(), detail.getSubjectName());
//		}
//
//		System.out.println("======================================================================");
//
//		return true;
//	}

	public boolean checkTeacherId(int teacherId) {
		return teacherDao.checkTeacherId(teacherId);
	}

	public boolean checkSubjectId(int subjectId) {
		return teacherDao.checkSubjectId(subjectId);
	}
	

	private boolean printAllTeachers() {
		List<Teacher> teacherDetails = new ArrayList<>();
		if(teacherDetails.isEmpty()) return false;
		
		for(Teacher t : teacherDetails) {
			PrintDataInFormat.printSingleTeacher(t);
		}
		return true;
	}

	private boolean printAllSubjects() {
		List<Subject> subjectDetails = new ArrayList<>();
		subjectDetails = teacherDao.getAllSubjectDetails();
		if (subjectDetails.isEmpty()) {
			System.out.println("No subjects present in the system");
			return false;
		}
		System.out.println("==============================================================");
		System.out.println("                      Subject Details");
		System.out.println("==============================================================");
		System.out.printf("| %-12s | %-30s | %-10s |\n", "Subject ID", "Subject Name", "Course ID");
		System.out.println("--------------------------------------------------------------");

		for (Subject subject : subjectDetails) {
			System.out.printf("| %-12d | %-30s | %-10d |\n", subject.getSubjectId(), subject.getSubjectName(),
					subject.getCourseId());
		}
		return true;
	}



}