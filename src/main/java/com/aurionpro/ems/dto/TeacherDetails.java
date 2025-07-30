package com.aurionpro.ems.dto;

public class TeacherDetails {
	private int teacherID;
	private String teacherName;
	private String teacherEmail;
	private String teacherQualification;
	private int teacherExperience;
	public TeacherDetails(int teacherID, String teacherName, String teacherEmail, String teacherQualification,
			int teacherExperience) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.teacherEmail = teacherEmail;
		this.teacherQualification = teacherQualification;
		this.teacherExperience = teacherExperience;
	}
	public TeacherDetails() {
		super();
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
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	public String getTeacherQualification() {
		return teacherQualification;
	}
	public void setTeacherQualification(String teacherQualification) {
		this.teacherQualification = teacherQualification;
	}
	public int getTeacherExperience() {
		return teacherExperience;
	}
	public void setTeacherExperience(int teacherExperience) {
		this.teacherExperience = teacherExperience;
	}
	@Override
	public String toString() {
	    StringBuilder builder = new StringBuilder();

	    builder.append(String.format("| %-10d | %-20s | %-25s | %-20s | %-10d |\n",
	            teacherID,
	            teacherName,
	            teacherEmail,
	            teacherQualification,
	            teacherExperience));

	    builder.append("+------------+----------------------+---------------------------+----------------------+------------+\n");

	    return builder.toString();
	}
	
}
