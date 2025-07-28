package com.aurionpro.ems.model;

import java.sql.Timestamp;

public class Course {

	private int courseId;
	private String name;
	private String description;
	private int courseYear;
	private Timestamp createdAt;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(int courseYear) {
		this.courseYear = courseYear;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Course(int courseId, String name, String description, int courseYear, Timestamp createdAt) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.description = description;
		this.courseYear = courseYear;
		this.createdAt = createdAt;
	}

	public Course() {
		super();
	}

	
}