package com.aurionpro.ems.model;

import java.util.Date;

public class Course {
	private int courseId;
	private String name;
	private String description;
	private int courseYear;
	private Date createdAt;
	private Double courseFee;

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

	public Course(String name, String description, int courseYear, Date createdAt, Double courseFee) {
		super();
		this.name = name;
		this.description = description;
		this.courseYear = courseYear;
		this.createdAt = createdAt;
		this.courseFee = courseFee;
	}

	public Course() {
		super();
	}

	public Double getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(Double courseFee) {
		this.courseFee = courseFee;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int course_id) {
		this.courseId = course_id;
	}

}
