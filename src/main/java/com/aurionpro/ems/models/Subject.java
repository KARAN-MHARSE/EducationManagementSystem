package com.aurionpro.ems.models;

public class Subject {
	private int subjectId;
	private String subjectName;
	private int courseId;
	
	
	
	
	public Subject() {
		super();
	}
	public Subject(int subjectId, String subjectName,int courseId) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.courseId = courseId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", courseId=" + courseId + "]";
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	

}
