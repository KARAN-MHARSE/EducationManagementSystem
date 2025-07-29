package com.aurionpro.ems.model;

import java.util.Date;

public class Subject extends Course{

    private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subject(String name, String description, int courseYear, Date createdAt,
			String name2) {
		super(name, description, courseYear, createdAt);
		name = name2;
	}
	public Subject(String name, String description, int courseYear, Date createdAt) {
		super(name, description, courseYear, createdAt);
	} 

}
