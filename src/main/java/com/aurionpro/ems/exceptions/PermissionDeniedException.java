package com.aurionpro.ems.exceptions;

public class PermissionDeniedException extends RuntimeException {
	public PermissionDeniedException() {
		super("You are not authorized to access the data.");
	}
}
