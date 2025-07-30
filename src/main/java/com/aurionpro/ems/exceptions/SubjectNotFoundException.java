package com.aurionpro.ems.exceptions;

public class SubjectNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int subjectId;

	public SubjectNotFoundException(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getMessage() {
		return "Subject with subject ID " + subjectId + " is not present";
	}
}
