package com.aurionpro.ems.controllers;

import java.util.Scanner;

import com.aurionpro.ems.services.AuthenticationService;

public class AuthenticationController {
	
	public static void showAuthenticationMenu(Scanner scanner) {
		boolean isContinue = true;
		
		while(isContinue) {
			System.out.println("Choose "
					+ "\n1. Login"
					+ " \n2. Change Password");
			
			try {
				int choice =  scanner.nextInt();
				scanner.nextLine();
				
				switch (choice) {
				case 1:
					AuthenticationService.login(scanner);
					
					break;

				default:
					break;
				}
				
			} 
			catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
			catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
	}

}
