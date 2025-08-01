package com.aurionpro.ems.controllers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;
import com.aurionpro.ems.dto.OperationResult;
import com.aurionpro.ems.model.Course;
import com.aurionpro.ems.model.Student;
import com.aurionpro.ems.model.User;
import com.aurionpro.ems.services.IStudentService;
import com.aurionpro.ems.services.implementation.*;
import com.aurionpro.ems.utils.DataValidator;

public class StudentController {

	private IStudentService studentService;
	private Scanner scanner;

	public StudentController() {
		this.studentService = new StudentServiceImpl();
		this.scanner = new Scanner(System.in);
	}

	public void addStudent() {
		System.out.println("\n=========== Add Student ===========");

		String firstName;
		do {
			System.out.print("Enter first name: ");
			firstName = scanner.nextLine();
			if (!DataValidator.isValidName(firstName)) {
				System.out.println(" Invalid first name! Only alphabets and spaces allowed.");
			}
		} while (!DataValidator.isValidName(firstName));

		String lastName;
		do {
			System.out.print("Enter last name: ");
			lastName = scanner.nextLine();
			if (!DataValidator.isValidName(lastName)) {
				System.out.println(" Invalid last name! Only alphabets and spaces allowed.");
			}
		} while (!DataValidator.isValidName(lastName));

		long mobile;
		do {
			System.out.print("Enter mobile number: ");
			try {
				mobile = Long.parseLong(scanner.nextLine());
				if (!DataValidator.isValidMobileNumber(mobile)) {
					System.out.println(" Invalid mobile number! It should  be 10 digits.");
				}
			} catch (NumberFormatException e) {
				System.out.println(" Invalid input. Please enter numeric digits only.");
				mobile = 0;
			}
		} while (!DataValidator.isValidMobileNumber(mobile));

		String email;
		do {
			System.out.print("Enter email: ");
			email = scanner.nextLine();
			if (!DataValidator.isValidEmail(email)) {
				System.out.println(" Invalid email format!");
			}
		} while (!DataValidator.isValidEmail(email));

		System.out.print("Enter city: ");
		String city = scanner.nextLine();

		// Gender selection
		System.out.println("Select gender:");
		System.out.println("1. Male");
		System.out.println("2. Female");

		Gender gender;
		while (true) {
			int genderChoice = DataValidator.checkFormatInt(scanner, "Enter choice (1 or 2): ");
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

		int rollNumber = DataValidator.checkFormatInt(scanner, "Enter roll number: ");
		int yearOfStudy = DataValidator.checkFormatInt(scanner, "Enter year of study: ");
		double percentage = DataValidator.checkFormatDouble(scanner, "Enter average percentage: ");

		// Create and populate Student object (inherits from User)
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setMobileNumber(mobile);
		student.setEmail(email);
		student.setCity(city);
		student.setGender(gender);
		student.setRole(Role.Student);
		student.setCreatedAt(new Timestamp(new Date().getTime()));
		student.setFirstLogin(true);
		student.setActive(true);

		student.setRollNumber(rollNumber);
		student.setYearOfStudy(yearOfStudy);
		student.setAveragePercentage(BigDecimal.valueOf(percentage));

		OperationResult result = studentService.addStudent(student);
		

		System.out.println(result.getMessage());

	}

//	
//	public void displayAllStudents() {
//	    List<Student> students =studentService.getAllStudents();
//
//	    if (students.isEmpty()) {
//	        System.out.println(" No students found.");
//	        return;
//	    }
//
//	    System.out.println("==== Student List ====");
//	    for (Student student : students) {
//	        //User user = student.getUser();
//	        System.out.println("Student ID     : " + student.getStudentId());
//	        System.out.println("Name           : " + student.getFirstName() + " " + student.getLastName());
//	        System.out.println("Email          : " + student.getEmail());
//	        System.out.println("Mobile         : " + student.getMobileNumber());
//	        System.out.println("City           : " + student.getCity());
//	        System.out.println("Gender         : " + student.getGender());
//	        System.out.println("Roll Number    : " + student.getRollNumber());
//	        System.out.println("Year of Study  : " + student.getYearOfStudy());
//	        System.out.println("Percentage     : " + student.getAveragePercentage());
//	        System.out.println("Created At     : " + student.getCreatedAt());
//	        System.out.println("First Login?   : " + student.isFirstLogin());
//	        System.out.println("--------------------------");
//	    }
//	}
//	
	public void displayAllStudents() {
	    List<Student> students = studentService.getAllStudents();

	    if (students.isEmpty()) {
	        System.out.println("No students found.");
	        return;
	    }

	    System.out.println("===============================================================================================");
	    System.out.printf("%-5s %-15s %-25s %-15s %-10s %-6s %-10s %-5s %-10s %-12s %-10s%n",
	            "ID", "Name", "Email", "Mobile", "City", "Gender", "Roll No", "Year", "Percentage", "Created At", "FirstLogin");
	    System.out.println("===============================================================================================");

	    for (Student student : students) {
	        String name = student.getFirstName() + " " + student.getLastName();
	        System.out.printf("%-5d %-15s %-25s %-15s %-10s %-6s %-10d %-5d %-10.2f %-12s %-10s%n",
	                student.getStudentId(),
	                name,
	                student.getEmail(),
	                student.getMobileNumber(),
	                student.getCity(),
	                student.getGender(),
	                student.getRollNumber(),
	                student.getYearOfStudy(),
	                student.getAveragePercentage(),
	                student.getCreatedAt(),
	                student.isFirstLogin());
	    }

	    System.out.println("===============================================================================================");
	}

	
	public void assignCourseToStudent() {
	    System.out.println("\n=========== Assign Course to Student ===========");

	    int studentId = DataValidator.checkFormatInt(scanner, "Enter Student ID: ");
	    int courseId = DataValidator.checkFormatInt(scanner, "Enter Course ID: ");

	    boolean isAssigned = studentService.assignCourse(studentId, courseId);

	    if (isAssigned) {
	        System.out.println("Course assigned successfully to student.");
	    } else {
	        System.out.println(" Failed to assign course.Please check if Student ID and Course ID are valid.");
	    }
	}

	public void viewCourseBYId() {
	    int studentId = DataValidator.checkFormatInt(scanner, "Enter Student ID: ");
	    List<Course> courses = studentService.viewCoursesByStudentId(studentId);

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
