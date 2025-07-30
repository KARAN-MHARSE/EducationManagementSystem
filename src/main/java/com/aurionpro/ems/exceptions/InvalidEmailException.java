package com.aurionpro.ems.exceptions;

public class InvalidEmailException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidEmailException() {
		super();
	}
	public String getMessage() {
		return "Invalid Email";
	}
}
