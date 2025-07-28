package com.aurionpro.ems.models;

import java.sql.Date;
import java.sql.Timestamp;

import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;

public class Admin extends User {
	private int adminId;
	private int experience;
	
	
	


	public Admin(int userId, String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Date createdAt, boolean isFirstLogin) {
		super(userId, firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
		this.experience=6;
	}
	public Admin(int userId, String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Date createdAt, boolean isFirstLogin, int experience) {
		super(userId, firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
		this.experience = experience;
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	

}
