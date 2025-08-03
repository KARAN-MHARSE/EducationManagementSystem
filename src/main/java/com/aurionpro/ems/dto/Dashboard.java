package com.aurionpro.ems.dto;

public class Dashboard {
    private int srNo;
    private int studentId;
    private String studentName;
    private String courses;
    private String subjects;
    private String teachers;
    private double totalFee;
    private double totalPaid;
    private double pendingFee;

    
    public Dashboard(int srNo, int studentId, String studentName, String courses,
                     String subjects, String teachers, double totalFee,
                     double totalPaid, double pendingFee) {
        this.srNo = srNo;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courses = courses;
        this.subjects = subjects;
        this.teachers = teachers;
        this.totalFee = totalFee;
        this.totalPaid = totalPaid;
        this.pendingFee = pendingFee;
    }

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public double getPendingFee() {
		return pendingFee;
	}

	public void setPendingFee(double pendingFee) {
		this.pendingFee = pendingFee;
	}

    
}

