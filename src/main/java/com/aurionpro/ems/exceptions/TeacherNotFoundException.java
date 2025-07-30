package com.aurionpro.ems.exceptions;

public class TeacherNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int teacherId;

	public TeacherNotFoundException(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getMessage() {
		return "Teacher with Teacher ID " + teacherId + " is not present";
	}
}
