package org.evoke.assignment.employee.model;

import java.util.Date;

public class ResponseMessage {

	private String message;
	private boolean status;
	private Date responsedate;
	
	public ResponseMessage() {
	}

	public ResponseMessage(String message, boolean status, Date responsedate) {
		super();
		this.message = message;
		this.status = status;
		this.responsedate = responsedate;
	}

	public Date getResponsedate() {
		return responsedate;
	}

	public void setResponsedate(Date responsedate) {
		this.responsedate = responsedate;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
