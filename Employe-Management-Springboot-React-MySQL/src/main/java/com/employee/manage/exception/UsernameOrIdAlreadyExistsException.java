package com.employee.manage.exception;

public class UsernameOrIdAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UsernameOrIdAlreadyExistsException(String message) {
		super(message);
	}

}
