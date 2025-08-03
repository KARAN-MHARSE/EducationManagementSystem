package com.aurionpro.ems.models;



import java.util.Date;

public class Fee {
    private int feesId;
    private int studentId;
    private double paidFees;
    private Date paidDate;
    private boolean isDeleted;

    // Getters and Setters

    public int getFeesId() {
        return feesId;
    }

    public void setFeesId(int feesId) {
        this.feesId = feesId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getPaidFees() {
        return paidFees;
    }

    public void setPaidFees(double paidFees) {
        this.paidFees = paidFees;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}