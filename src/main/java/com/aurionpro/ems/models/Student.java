package com.aurionpro.ems.models;

import java.math.BigDecimal;
import java.sql.Date;

import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;
import com.google.protobuf.Timestamp;

public class Student extends User {

	private int studentId;
	private int rollNumber;
	private BigDecimal averagePercentage;
	private int yearOfStudy;
	private int course_ID;
	public Student() {
		
	}

	public Student(int userId, String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Date createdAt, boolean isFirstLogin, int rollNumber,
			BigDecimal averagePercentage, int yearOfStudy, int course_ID,boolean isActive) {
		super(userId, firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin,isActive);
		this.rollNumber = rollNumber;
		this.averagePercentage = averagePercentage;
		this.yearOfStudy = yearOfStudy;
		this.course_ID = course_ID;
	}

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

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", rollNumber=" + rollNumber + ", averagePercentage="
				+ averagePercentage + ", yearOfStudy=" + yearOfStudy + ", course_ID=" + course_ID + "]";
	}

	

}