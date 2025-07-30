package com.aurionpro.ems.model;

import java.math.BigDecimal;

public class Student extends User{

    private int studentId;
  
    private int rollNumber;
    private BigDecimal averagePercentage;
    private int yearOfStudy;
    private int courseId;
    

    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Student() {
    	//this.user = new User();
    }

    public Student(User user, int rollNumber, BigDecimal averagePercentage, int yearOfStudy) {
       // this.user = user;
        this.rollNumber = rollNumber;
        this.averagePercentage = averagePercentage;
        this.yearOfStudy = yearOfStudy;
    }

    public Student(int studentId, User user, int rollNumber, BigDecimal averagePercentage, int yearOfStudy) {
        this.studentId = studentId;
       // this.user = user;
        this.rollNumber = rollNumber;
        this.averagePercentage = averagePercentage;
        this.yearOfStudy = yearOfStudy;
    }

    // Getters and setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

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
}
