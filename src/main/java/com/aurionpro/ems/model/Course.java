package com.aurionpro.ems.model;

import java.util.Date;

public class Course {

	private String name;
	private String description;
	private int courseYear;
	private Date createdAt;
	
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Course(String name, String description, int courseYear, Date createdAt) {
		super();
		this.name = name;
		this.description = description;
		this.courseYear = courseYear;
		this.createdAt = createdAt;
	}

	public Course() {
		super();
	}

	
}
