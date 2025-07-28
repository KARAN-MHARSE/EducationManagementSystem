package com.aurionpro.ems;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.crypto.Data;

import com.aurionpro.ems.controllers.AuthenticationController;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.models.User;

public class EmsApplication {
	public static User currentUser = null;

	public static void main(String args[]) throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to College Management System");

		AuthenticationController.showAuthenticationMenu(scanner);

	}
}