package com.aurionpro.ems.exceptions;

public class SubjectNotFoundException extends RuntimeException {
	public SubjectNotFoundException(int subjectId) {
		super("Subject not found with id" + subjectId);
	}

	public SubjectNotFoundException() {
		super("Subjects not found");
	}
}
