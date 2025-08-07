package com.aurionpro.ems.services;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.dao.IFeeDao;
import com.aurionpro.ems.dao.implementation.FeeDaoImpl;
import com.aurionpro.ems.dto.PendingFeeStudentDto;
import com.aurionpro.ems.dto.StudentFeeSummary;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.utils.DataValidationUtil;

public class FeesServices {

	private IFeeDao feeDao;

	public FeesServices() {
		this.feeDao = new FeeDaoImpl();
	}

	public void viewFeeByStudentId(Scanner scanner) {

		int studentId = DataValidationUtil.checkFormatInt(scanner, "enter student id :");

		List<StudentFeeSummary> summaries = feeDao.getFeesByStudentId(studentId);

		if (summaries.isEmpty()) {
			System.out.println(" No student found with ID: " + studentId);
		} else {
			System.out.println("Total fee fetched successfully.");

			System.out.printf("%-12s %-25s %-15s %-18s %-20s%n", "Student ID", "Full Name", "Total Fee",
					"Total Paid Fee", "Total Pending Fee");
			System.out.println(
					"---------------------------------------------------------------------------------------------");

			for (StudentFeeSummary summary : summaries) {
				System.out.printf("%-12s %-25s ₹%-14.2f ₹%-17.2f ₹%-19.2f%n", summary.getStudentId(),
						summary.getFullName(), summary.getTotalFee(), summary.getTotalPaidFee(),
						summary.getPendingFee());
			}

		}
	}

	public void viewCourseFeeById(Scanner scanner) {
		viewCourseFees();
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter course id :");

		List<Course> courses = feeDao.getFeesByCourseId(courseId);
		if (courses.isEmpty()) {
			System.out.println(" No course  found with ID: " + courseId);
			return;
		}
		System.out.printf("%-30s %-15s%n", "Course Name", "Course Fee (₹)");
		System.out.println("---------------------------------------------------");

		for (Course course : courses) {
			System.out.printf("%-30s ₹%-14.2f%n", course.getName(), course.getCourseFee());
		}

	}

	public void updateFeeById(Scanner scanner) {
		viewCourseFees();
		int courseId = DataValidationUtil.checkFormatInt(scanner, "Enter course id to update fees :");
		double newFee = DataValidationUtil.checkFormatDouble(scanner, "Enter new Fee : ");

		feeDao.updateFeeByCourseId(courseId, newFee);

	}

	public void getTotalEarning() {
		double totalEarning = feeDao.getTotalEarnings();
		System.out.println("Total earning is :" + totalEarning);
	}

	public void getStudentsWithPendingFees() {
		List<PendingFeeStudentDto> pendingFeeStudentDtos = feeDao.getStudentsWithPendingFees();

		if (pendingFeeStudentDtos.isEmpty()) {
			System.out.println("No students with pending fees.");
			return;
		}

		System.out.println("📋 Students with Pending Fees:\n");

		System.out.printf("%-10s %-25s %-30s %-15s %-10s\n", "StudentID", "Full Name", "Email", "Mobile", "Pending ₹");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		for (PendingFeeStudentDto dto : pendingFeeStudentDtos) {
			System.out.printf("%-10d %-25s %-30s %-15s %-10.2f\n", dto.getStudentId(), dto.getFullName(),
					dto.getEmail(), dto.getMobileNumber(), dto.getPendingFee());
		}
	}

	public void getStudentsWithTotalPaidFees() {
		List<PendingFeeStudentDto> totalFeeStudentDtos = feeDao.getStudentsWithTotalPaidFees();

		if (totalFeeStudentDtos.isEmpty()) {
			System.out.println(" No students with total paid fees.");
			return;
		}

		System.out.println("📋 Students with Pending Fees:\n");

		System.out.printf("%-10s %-25s %-30s %-15s %-10s\n", "StudentID", "Full Name", "Email", "Mobile", "Pending ₹");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		for (PendingFeeStudentDto dto : totalFeeStudentDtos) {
			System.out.printf("%-10d %-25s %-30s %-15s %-10.2f\n", dto.getStudentId(), dto.getFullName(),
					dto.getEmail(), dto.getMobileNumber(), dto.getPendingFee());
		}
	}

	public void viewCourseFees() {

		List<Course> courses = feeDao.getFeesByCourse();
//		System.out.printf("%-30s %-15s%n", "Course Name", "Course Fee (₹)");
//		System.out.println("---------------------------------------------------");
//
//		for (Course course : courses) {
//		    System.out.printf("%-30s ₹%-14.2f%n",
//		            course.getName(),
//		            course.getCourseFee());
//		}
		System.out.printf("%-10s %-30s %-15s%n", "Course ID", "Course Name", "Course Fee (₹)");
		System.out.println("---------------------------------------------------------------");

		for (Course course : courses) {
			System.out.printf("%-10s %-30s ₹%-14.2f%n", course.getCourseId(), course.getName(), course.getCourseFee());
		}

	}

}
