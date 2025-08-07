package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.services.AuthenticationService;
import com.aurionpro.ems.utils.DataValidationUtil;

public class AuthenticationController {
	private final AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public void showAuthenticationMenu(Scanner scanner) {
		boolean isContinue = true;

		while (isContinue) {
		

			try {
				int choice =DataValidationUtil.checkFormatInt(scanner, "Choose " + "\n1. Login" + " \n2. Change Password");

				switch (choice) {
				case 1:
					authenticationService.login(scanner);
					break;

				default:
					authenticationService.changePassword(scanner);
					break;
				}

			} catch (NumberFormatException e) {
//				e.printStackTrace();
				System.err.println(e.getMessage());
				scanner.nextLine();
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
	}

}
