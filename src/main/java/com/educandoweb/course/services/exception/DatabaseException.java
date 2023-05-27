package com.educandoweb.course.services.exception;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = -5104415919762237431L;
	
	public DatabaseException(String msg) {
		super(msg);
	}
}
