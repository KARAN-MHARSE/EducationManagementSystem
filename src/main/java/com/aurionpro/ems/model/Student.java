package com.aurionpro.ems.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.management.relation.Role;

import com.aurionpro.ems.Enum.Gender;

public class Student extends User {

	private int studentId;
	private int rollNumber;
	private BigDecimal averagePercentage;
	private int yearOfStudy;
	private Course course_ID;

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

	public Course getCourse_ID() {
		return course_ID;
	}

	public void setCourse_ID(Course course_ID) {
		this.course_ID = course_ID;
	}

	public Student(String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Timestamp createdAt, boolean isFirstLogin, int studentId, int rollNumber,
			BigDecimal averagePercentage, int yearOfStudy, Course course_ID) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
		this.studentId = studentId;
		this.rollNumber = rollNumber;
		this.averagePercentage = averagePercentage;
		this.yearOfStudy = yearOfStudy;
		this.setCourse_ID(null);
	}

	public Student(String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Timestamp createdAt, boolean isFirstLogin) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
	}

}
