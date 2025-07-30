package com.aurionpro.ems.exceptions;

public class InvalidMobileNumber extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidMobileNumber() {
		super();
	}
	public String getMessage() {
		return "Mobile Number should be of 10 digits";
	}
}
