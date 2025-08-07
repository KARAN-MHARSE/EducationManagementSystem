package com.aurionpro.ems.dto;



public class Teacher_Subject {
	private String subjectID;
	private String subjectName;
	private int teacherID;
	private String teacherName;
	public String getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Teacher_Subject(String subjectID, String subjectName, int teacherID, String teacherName) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.teacherID = teacherID;
		this.teacherName = teacherName;
	}
	public Teacher_Subject() {
		super();
	}
	
}