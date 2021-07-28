package org.evoke.assignment.employee.handler;

public class EmployeeNotFound extends RuntimeException {

	private static final long serialVersionUID = 3711443818187668616L;
	public EmployeeNotFound() {
		
	}
	public EmployeeNotFound(String message) {
		super(message);
	}

	
}
