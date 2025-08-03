package com.aurionpro.ems.model;

public class Authentication {
	private int authenticationID;
	private String userName;
	private String password;
	private int userId;

	public Authentication(String userName, String password, int userId) {
		super();
		this.userName = userName;
		this.password = password;
		this.userId = userId;
	}

	public int getAuthenticationID() {
		return authenticationID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}