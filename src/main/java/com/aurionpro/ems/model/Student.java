package com.aurionpro.ems.model;

import java.math.BigDecimal;
import java.util.Date;

import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;

public class Student extends User {

	private int studentId;
	private int rollNumber;
	private BigDecimal averagePercentage;
	private int yearOfStudy;
	private int course_ID;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public BigDecimal getAveragePercentage() {
		return averagePercentage;
	}

	public void setAveragePercentage(BigDecimal averagePercentage) {
		this.averagePercentage = averagePercentage;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public int getCourse_ID() {
		return course_ID;
	}

	public void setCourse_ID(int course_ID) {
		this.course_ID = course_ID;
	}

	public Student(String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Date createdAt, boolean isFirstLogin, int studentId, int rollNumber,
			BigDecimal averagePercentage, int yearOfStudy, int course_ID) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
		this.studentId = studentId;
		this.rollNumber = rollNumber;
		this.averagePercentage = averagePercentage;
		this.yearOfStudy = yearOfStudy;
		this.course_ID=course_ID;
	}

	public Student(String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Date createdAt, boolean isFirstLogin) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
	}
	
	public Student() {
		super();
	}

}
