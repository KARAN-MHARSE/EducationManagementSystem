package com.aurionpro.ems.controllers;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.dto.PendingFeeStudentDto;
import com.aurionpro.ems.dto.StudentFeeSummary;
import com.aurionpro.ems.model.Course;
import com.aurionpro.ems.services.IFeeService;
import com.aurionpro.ems.services.implementation.FeeServiceImpl;
import com.aurionpro.ems.utils.DataValidator;

public class FeeController {

	private IFeeService feeService;
	Scanner scanner = new Scanner(System.in);

	public FeeController() {
		this.feeService = new FeeServiceImpl();
	}
	
	public void start() {
		while (true) {
			System.out.println("\nFee Management Menu");
			System.out.println("1. View Total Paid Fees (All Students)");
			System.out.println("2. View Total Pending Fees (All Students)");
			System.out.println("3. View Fees By Student ID");
			System.out.println("4. View Fees By Course ID");
			System.out.println("5. Update Fee of a Course");
			System.out.println("6. View Total Earnings");
			System.out.println("7. Exit");

			int choice = DataValidator.checkFormatInt(scanner, "Enter your choice: ");

			switch (choice) {
				case 1:
					getStudentsWithTotalPaidFees();
					break;
				case 2:
					getStudentsWithPendingFees();
					break;
				case 3:
					viewFeeByStudentId();
					break;
				case 4:
					viewCourseFeeById();
					break;
				case 5:
					updateFeeById();
					break;
				case 6:
					getTotalEarning();
					break;
				case 7:
					System.out.println(" Exiting Fee Management. Thank you!");
					return;
				default:
					System.out.println(" Invalid choice. Please try again.");
			}
		}
	}

	public void viewFeeByStudentId() {

		int studentId = DataValidator.checkFormatInt(scanner, "enter student id :");

		List<StudentFeeSummary> summaries = feeService.getFeesByStudentId(studentId);

		if (summaries.isEmpty()) {
			System.out.println(" No student found with ID: " + studentId);
		} else {
			System.out.println(" Total fee fetched successfully.");
			for (StudentFeeSummary summary : summaries) {
				System.out.println("Student ID: " + summary.getStudentId());
				System.out.println("Full Name : " + summary.getFullName());
				System.out.println("Total Fee : ₹" + summary.getTotalFee());
				System.out.println("Total Paid Fee : ₹" + summary.getTotalPaidFee());
				System.out.println("Total Pending Fee : ₹" + summary.getPendingFee());
				
			}
		}

	}

	public void viewCourseFeeById() {
		int courseId = DataValidator.checkFormatInt(scanner, "enter course id :");

		List<Course> courses = feeService.getFeesByCourseId(courseId);
		if (courses.isEmpty()) {
			System.out.println(" No course  found with ID: " + courseId);
			return;
		}
		for (Course course : courses) {
			System.out.println("Course Name: " + course.getName());
			System.out.println("Course fee : " + course.getCourseFee());

		}
	}

	public void updateFeeById() {
		int courseId = DataValidator.checkFormatInt(scanner, "Enter course id to update fees :");
		double newFee = DataValidator.checkFormatDouble(scanner, "Enter new Fee : ");

		feeService.updateFeeByCourseId(courseId, newFee);

	}
	
	public void getTotalEarning() {
		double totalEarning=feeService.getTotalEarnings();
		System.out.println("Total earning is :"+totalEarning);
	}
	
	public void getStudentsWithPendingFees() {
	    List<PendingFeeStudentDto> pendingFeeStudentDtos = feeService.getStudentsWithPendingFees();

	    if (pendingFeeStudentDtos.isEmpty()) {
	        System.out.println("No students with pending fees.");
	        return;
	    }

	    System.out.println("Students with Pending Fees:\n");


	    System.out.printf("%-10s %-25s %-30s %-15s %-10s\n",
	            "StudentID", "Full Name", "Email", "Mobile", "Pending ₹");
	    System.out.println("------------------------------------------------------------------------------------------------------");

	   
	    for (PendingFeeStudentDto dto : pendingFeeStudentDtos) {
	        System.out.printf("%-10d %-25s %-30s %-15s %-10.2f\n",
	                dto.getStudentId(),
	                dto.getFullName(),
	                dto.getEmail(),
	                dto.getMobileNumber(),
	                dto.getPendingFee());
	    }
	}
	
	public void getStudentsWithTotalPaidFees() {
	    List<PendingFeeStudentDto> totalFeeStudentDtos = feeService.getStudentsWithTotalPaidFees();

	    if (totalFeeStudentDtos.isEmpty()) {
	        System.out.println(" No students with pending fees.");
	        return;
	    }

	    System.out.println("Students with Pending Fees:\n");


	    System.out.printf("%-10s %-25s %-30s %-15s %-10s\n",
	            "StudentID", "Full Name", "Email", "Mobile", "Pending ₹");
	    System.out.println("------------------------------------------------------------------------------------------------------");

	   
	    for (PendingFeeStudentDto dto : totalFeeStudentDtos) {
	        System.out.printf("%-10d %-25s %-30s %-15s %-10.2f\n",
	                dto.getStudentId(),
	                dto.getFullName(),
	                dto.getEmail(),
	                dto.getMobileNumber(),
	                dto.getPendingFee());
	    }
	}



}
