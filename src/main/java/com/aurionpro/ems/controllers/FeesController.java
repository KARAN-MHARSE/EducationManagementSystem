package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.services.FeesServices;

public class FeesController {
	FeesServices feesServices;

	public FeesController(FeesServices feesServices) {
		this.feesServices = feesServices;
	}

	public void printFeesManagementMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
			System.out.println("\nEnter choice code " + "\n1. View Total Paid Fees" + "\n2. View Total Pending Fees "
					+ "\n3. View Fees By Student  " + "\n4. View Fees By Course ID" + "\n5. View Fees By Course"
					+ "\n6. Update fees of a course \n" + "7. Total Earning " + "\n8. Go Back");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					feesServices.getStudentsWithTotalPaidFees();
					break;
				case 2:
					feesServices.getStudentsWithPendingFees();
					break;
				case 3:
					feesServices.viewFeeByStudentId(scanner);
					break;
				case 4:
					feesServices.viewCourseFeeById(scanner);
					break;
				case 5:
					feesServices.viewCourseFees();
					break;
				case 6:
					feesServices.updateFeeById(scanner);
					break;
				case 7:
					feesServices.getTotalEarning();
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
//				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}

	}

}
