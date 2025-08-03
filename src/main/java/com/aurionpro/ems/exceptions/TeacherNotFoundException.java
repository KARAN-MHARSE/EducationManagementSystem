package com.aurionpro.ems.exceptions;

public class TeacherNotFoundException extends RuntimeException {
	public TeacherNotFoundException() {
		super("Teacher not found");
	}
}
