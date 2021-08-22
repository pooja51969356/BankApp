package com.bankingapi.bank.exception;

public class FromAccountNumberNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public FromAccountNumberNotFoundException(String message) {
		super(message);

	}

	public FromAccountNumberNotFoundException(String message, Throwable t) {
		super(message, t);

	}
}
