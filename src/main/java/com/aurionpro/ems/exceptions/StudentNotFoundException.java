package com.aurionpro.ems.exceptions;

public class StudentNotFoundException extends RuntimeException{
	public StudentNotFoundException(int student_id) {
		super("Student not found with student id "+student_id);
	}
	
	public StudentNotFoundException() {
		super("Students not found");
	}

}
