package com.aurionpro.ems.controllers;



import java.util.Scanner;

import com.aurionpro.ems.dao.implementation.StudentLoginDao;
import com.aurionpro.ems.exceptions.AuthenticationException;

public class StudentLoginController {

    private StudentLoginDao loginDao;
    private Scanner scanner;

    public StudentLoginController() {
        loginDao = new StudentLoginDao();
        scanner = new Scanner(System.in);
    }

    public void startLogin() {
        try {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            boolean loginSuccess = loginDao.login(username, password);

            if (loginSuccess) {
                System.out.println("Login successful!");

                if (loginDao.isFirstLogin(username)) {
                    System.out.println("It looks like this is your first login.");
                    System.out.print("Please set your new password: ");
                    String newPassword = scanner.nextLine();

                    boolean updateSuccess = loginDao.updatePasswordOnFirstLogin(username, newPassword);
                    if (updateSuccess) {
                        System.out.println("Password updated successfully.");
                    } else {
                        System.out.println("Failed to update password.");
                    }
                } else {
                    System.out.println("Welcome back!");
                }
            }

        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

