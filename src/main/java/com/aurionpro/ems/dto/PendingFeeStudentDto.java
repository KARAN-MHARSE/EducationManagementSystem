package com.aurionpro.ems.dto;


import java.math.BigDecimal;

public class PendingFeeStudentDto {

    private int studentId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private double pendingFee;
	public PendingFeeStudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PendingFeeStudentDto(int studentId, String fullName, String email, String mobileNumber, 
			 double pendingFee) {
		super();
		this.studentId = studentId;
		this.fullName = fullName;
		this.email = email;
		this.mobileNumber = mobileNumber;

		this.pendingFee = pendingFee;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
//	public double getTotalFee() {
//		return totalFee;
//	}
//	public void setTotalFee(double totalFee) {
//		this.totalFee = totalFee;
//	}
//	public double getTotalPaid() {
//		return totalPaid;
//	}
//	public void setTotalPaid(double totalPaid) {
//		this.totalPaid = totalPaid;
//	}
	public double getPendingFee() {
		return pendingFee;
	}
	public void setPendingFee(double pendingFee) {
		this.pendingFee = pendingFee;
	}
	@Override
	public String toString() {
		return "PendingFeeStudentDto [studentId=" + studentId + ", fullName=" + fullName + ", email=" + email
				+ ", mobileNumber=" + mobileNumber +  ", pendingFee=" + pendingFee + "]";
	}

    
}