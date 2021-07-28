package org.evoke.assignment.employee.handler;

import java.time.LocalDateTime;
import java.util.Date;

import org.evoke.assignment.employee.model.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomeEmployeeExceptionHandler {

	ResponseMessage respmessage = null;
	Date date = null;
	
	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<ResponseMessage> handleEmployeeNotFoundException(EmployeeNotFound enf){
		date = new Date(System.currentTimeMillis());
		
		respmessage = new ResponseMessage(enf.getMessage(),false,date);		
		return new ResponseEntity<>(respmessage,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseMessage> nullCheckingException(NullPointerException nullcheck){
		date = new Date(System.currentTimeMillis());
		
		respmessage = new ResponseMessage(nullcheck.getMessage(),false,date);		
		return new ResponseEntity<>(respmessage,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseMessage> handleGlobalException(){
		date = new Date(System.currentTimeMillis());
		
		respmessage = new ResponseMessage("Website Undermaintance",false,date);	
		return new ResponseEntity<>(respmessage,HttpStatus.NOT_FOUND);
	}
		
}
