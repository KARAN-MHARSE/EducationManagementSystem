package com.aurionpro.ems.model;

import java.sql.Timestamp;

import javax.management.relation.Role;

import com.aurionpro.ems.Enum.Gender;

public class Authentication extends User{

	private int authId;
    private String username;
    private String password;
	public int getAuthId() {
		return authId;
	}
	public void setAuthId(int authId) {
		this.authId = authId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Authentication(String firstName, String lastName, long mobileNumber, String email,
			Gender gender, String city, Role role, Timestamp createdAt, boolean isFirstLogin, int authId,
			String username, String password) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
		this.authId = authId;
		this.username = username;
		this.password = password;
	}
	public Authentication(String firstName, String lastName, long mobileNumber, String email,
			Gender gender, String city, Role role, Timestamp createdAt, boolean isFirstLogin) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
	}

    
}
