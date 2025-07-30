package com.aurionpro.ems.exceptions;

public class IncorrectNameException extends RuntimeException{

	public IncorrectNameException() {
		super();
	}
	
	public String getMessage() {
		return "Invalid name details.Name should only contain letters.";
	}
}
