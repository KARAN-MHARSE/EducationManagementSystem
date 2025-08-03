package com.aurionpro.ems.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidationUtil {

	public static boolean isValidString(String name) {
		if (name == null || name.isBlank()) {
			return false;
		}

		Pattern pattern = Pattern.compile("^[a-zA-z ]+$");
		Matcher matcher = pattern.matcher(name);

		return matcher.matches();
	}

	public static boolean isValidEmail(String email) {
		if (email == null || email.isBlank())
			return false;

		Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidMobileNumber(long mobile) {
	    String mobileStr = String.valueOf(mobile);
	    return mobileStr.length() == 10;
	}

	public static boolean isValidPassword(String password) {
		if (password == null || password.isBlank() || password.length() < 8)
			return false;		
		Pattern pattern = Pattern.compile("\"^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#$%^&+=])(?=\\\\S+$).{8,20}$\"");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public static int checkFormatInt(Scanner scanner, String message) {
		while (true) {
			System.out.println(message);
			String input = scanner.nextLine();

			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException exception) {
				System.out.println("only integer ");
			}
		}
	}

	public static double checkFormatDouble(Scanner scanner, String message) {
		while (true) {
			System.out.println(message);
			String input = scanner.nextLine();

			try {
				return Double.parseDouble(input);
			} catch (NumberFormatException exception) {
				System.out.println("only double allowed ");
			}
		}
	}



}