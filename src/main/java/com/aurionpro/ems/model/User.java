package com.aurionpro.ems.model;

import java.util.Date;

import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;

public class User {

    private String firstName;
    private String lastName;
    private long mobileNumber;
    private String email;
    private Gender gender;
    private String city;
    private Role role;
    private Date createdAt;
    private boolean isFirstLogin;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isFirstLogin() {
		return isFirstLogin;
	}
	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	public User(String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Date createdAt, boolean isFirstLogin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.gender = gender;
		this.city = city;
		this.role = role;
		this.createdAt = createdAt;
		this.isFirstLogin = isFirstLogin;
	}
	public User() {
		super();
	}
}
