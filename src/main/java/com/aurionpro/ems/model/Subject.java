package com.aurionpro.ems.model;

import java.util.Date;

public class Subject extends Course {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject(String name, String description, int courseYear, Date createdAt, String name2, Double courseFee) {
		super(name, description, courseYear, createdAt, courseFee);
		name = name2;
	}

	public Subject(String name, String description, int courseYear, Date createdAt, Double courseFee) {
		super(name, description, courseYear, createdAt, courseFee);
	}
	
	public Subject() {
		super();
	}
}
