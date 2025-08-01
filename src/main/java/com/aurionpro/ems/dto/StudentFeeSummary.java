package com.aurionpro.ems.dto;


public class StudentFeeSummary {
    private int studentId;
    private String fullName;
    private double totalFee;
    private double totalPaidFee;
    private double pendingFee;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

	public double getTotalPaidFee() {
		return totalPaidFee;
	}

	public void setTotalPaidFee(double totalPaidFee) {
		this.totalPaidFee = totalPaidFee;
	}

	public double getPendingFee() {
		return pendingFee;
	}

	public void setPendingFee(double pendingFee) {
		this.pendingFee = pendingFee;
	}
}