package com.aurionpro.ems.dao;

public interface IStudentLoginDao {
	boolean login(String username, String password);
	boolean isFirstLogin(String userName);
	boolean updatePasswordOnFirstLogin(String username, String newPassword);
}
