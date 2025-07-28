package com.aurionpro.ems.model;

import java.math.BigDecimal;

public class Student {

    private int studentId;
    private User user; // Composition: a Student has a User
    private int rollNumber;
    private BigDecimal averagePercentage;
    private int yearOfStudy;

    public Student() {
    }

    public Student(User user, int rollNumber, BigDecimal averagePercentage, int yearOfStudy) {
        this.user = user;
        this.rollNumber = rollNumber;
        this.averagePercentage = averagePercentage;
        this.yearOfStudy = yearOfStudy;
    }

    public Student(int studentId, User user, int rollNumber, BigDecimal averagePercentage, int yearOfStudy) {
        this.studentId = studentId;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
