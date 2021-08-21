package com.bankingapi.bank.exception;

public class InavalidAccountException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;
	public InavalidAccountException(String message) {
		super();
		this.message = message;
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
	public InavalidAccountException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InavalidAccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public InavalidAccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
