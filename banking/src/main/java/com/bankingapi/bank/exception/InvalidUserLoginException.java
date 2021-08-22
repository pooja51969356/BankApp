package com.bankingapi.bank.exception;

public class InvalidUserLoginException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;
	String message;

	
	public InvalidUserLoginException(String message) {
		super();
		this.message = message;
	}
	
	public InvalidUserLoginException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvalidUserLoginException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public InvalidUserLoginException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public InvalidUserLoginException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	


}
