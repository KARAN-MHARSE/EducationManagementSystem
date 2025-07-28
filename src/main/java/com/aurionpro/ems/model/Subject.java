package com.aurionpro.ems.model;

import java.sql.Timestamp;

public class Subject extends Course{

	private int subjectId;
    private String name;
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subject(int courseId, String name, String description, int courseYear, Timestamp createdAt, int subjectId,
			String name2) {
		super(courseId, name, description, courseYear, createdAt);
		this.subjectId = subjectId;
		name = name2;
	}
	public Subject(int courseId, String name, String description, int courseYear, Timestamp createdAt) {
		super(courseId, name, description, courseYear, createdAt);
	}
    
    

}
