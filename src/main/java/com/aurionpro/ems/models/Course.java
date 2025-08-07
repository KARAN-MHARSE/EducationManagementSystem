package com.aurionpro.ems.models;

import java.sql.Date;

import com.google.protobuf.Timestamp;

public class Course {

	private int courseId;
	private String name;
	private String description;
	private int courseDuration;
	private double courseFee;
	private Timestamp createdAt;
	private boolean isDeleted;

	public Course(String name, String description, int courseDuration, Timestamp createdAt, Boolean isDeleted,double courseFee) {
		this.name = name;
		this.description = description;
		this.courseDuration = courseDuration;
		this.createdAt = createdAt;
		this.isDeleted = isDeleted;
		this.courseFee = courseFee;
	}

	
	public double getCourseFee() {
		return courseFee;
	}


	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}


	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

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

	public int getcourseDuration() {
		return courseDuration;
	}

	public void setcourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Course() {
		super();
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", description=" + description + ", courseYear="
				+ courseDuration + ", createdAt=" + createdAt + ", isDeleted=" + isDeleted + "]";
	}

}
