package com.aurionpro.ems.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.builder.UserBuilder;
import com.aurionpro.ems.dao.ICourseDao;
import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.dao.implementation.CourseDaoImplementation;
import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;
import com.aurionpro.ems.exceptions.StudentNotFoundException;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Student;
import com.aurionpro.ems.models.User;
import com.aurionpro.ems.utils.DataValidationUtil;
import com.aurionpro.ems.utils.UserUtil;

public class StudentService {
	private final IStudentDao studentDao;
	private ICourseDao courseDao;

	public StudentService(IStudentDao studentDao) {
		this.studentDao = studentDao;
		this.courseDao = new CourseDaoImplementation();
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

	public void addNewStudent(Scanner scanner) {
		System.out.println("\n=========== Add Student ===========");

		String firstName;
		do {
			System.out.print("Enter first name: ");
			firstName = scanner.nextLine();
			if (!DataValidationUtil.isValidString(firstName)) {
				System.out.println(" Invalid first name! Only alphabets and spaces allowed.");
			}
		} while (!DataValidationUtil.isValidString(firstName));

		String lastName;
		do {
			System.out.print("Enter last name: ");
			lastName = scanner.nextLine();
			if (!DataValidationUtil.isValidString(lastName)) {
				System.out.println(" Invalid last name! Only alphabets and spaces allowed.");
			}
		} while (!DataValidationUtil.isValidString(lastName));

		long mobile;
		do {
			System.out.print("Enter mobile number: ");
			try {
				mobile = Long.parseLong(scanner.nextLine());
				if (!DataValidationUtil.isValidMobileNumber(mobile)) {
					System.out.println(" Invalid mobile number! It should start with 6-9 and be 10 digits.");
				}
			} catch (NumberFormatException e) {
				System.out.println(" Invalid input. Please enter numeric digits only.");
				mobile = 0;
			}
		} while (!DataValidationUtil.isValidMobileNumber(mobile));

		String email;
		do {
			System.out.print("Enter email: ");
			email = scanner.nextLine();
			if (!DataValidationUtil.isValidEmail(email)) {
				System.out.println("❌ Invalid email format!");
			}
		} while (!DataValidationUtil.isValidEmail(email));

		System.out.print("Enter city: ");
		String city = scanner.nextLine();

		// Gender selection
		System.out.println("Select gender:");
		System.out.println("1. Male");
		System.out.println("2. Female");

		Gender gender;
		while (true) {
			int genderChoice = DataValidationUtil.checkFormatInt(scanner, "Enter choice (1 or 2): ");
			if (genderChoice == 1) {
				gender = Gender.Male;
				break;
			} else if (genderChoice == 2) {
				gender = Gender.Female;
				break;
			} else {
				System.out.println("Invalid choice. Please select 1 for Male or 2 for Female.");
			}
		}

		int rollNumber = DataValidationUtil.checkFormatInt(scanner, "Enter roll number: ");
		int yearOfStudy = DataValidationUtil.checkFormatInt(scanner, "Enter year of study: ");
		double percentage = DataValidationUtil.checkFormatDouble(scanner, "Enter average percentage: ");

		// Set User object
		UserBuilder userBuilder = new UserBuilder();
		Student student = userBuilder.setFirstName(firstName).setLastName(lastName).setMobileNumber(mobile)
				.setEmail(email).setRollNumber(rollNumber).setRole(Role.Student).setCity(city).setGender(gender)
				.setYearOfStudy(yearOfStudy).setActive(true).setFirstLogin(true).getStudent();

		boolean success = studentDao.addNewStudent(student);
		if (success) {
			System.out.println(" Student added successfully!");
		} else {
			System.out.println(" Failed to add student.");
		}
	}

	public void getStudent(Scanner scanner) throws SQLException {

		showStudentSearchMenu(scanner);

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

	public void assignCourseToStudent(Scanner scanner) throws SQLException {
		System.out.println("\n=========== Assign Course to Student ===========");

		printAllStudents();
		int studentId = DataValidationUtil.checkFormatInt(scanner, "Enter Student ID: ");

		courseDao.printAllCourseDetails();
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter Course ID: ");

		boolean isAssigned = studentDao.assignCourse(studentId, courseId);

		if (isAssigned) {
			System.out.println("Course assigned successfully to student.");
		} else {
			System.out.println(" Failed to assign course.Please check if Student ID and Course ID are valid.");
		}
	}

	public void viewCourseBYId(Scanner scanner) {
		int studentId = DataValidationUtil.checkFormatInt(scanner, "Enter Student ID: ");
		List<Course> courses = studentDao.viewCoursesByStudentId(studentId);

		if (courses == null || courses.isEmpty()) {
			System.out.println(" No courses found for this student.");
			return;
		}

		System.out.println("\n=========== Courses for Student ID: " + studentId + " ===========");
		for (Course course : courses) {
			System.out.println("Course ID     : " + course.getCourseId());
			System.out.println("Course Name   : " + course.getName());
			System.out.println("Description   : " + course.getDescription());
			System.out.println("Course Year   : " + course.getCourseYear());
			System.out.println("Created At    : " + course.getCreatedAt());
			System.out.println("-----------------------------");
		}
	}

}
