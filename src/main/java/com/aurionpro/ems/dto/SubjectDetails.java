package com.aurionpro.ems.dto;

public class SubjectDetails {

	private int subjectID;
	private String subjectName;
	private int courseID;

	public SubjectDetails() {
		super();
	}

	public SubjectDetails(int subjectID, String subjectName, int courseID) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.courseID = courseID;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

}
