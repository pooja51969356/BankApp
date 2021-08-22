package com.bankingapi.bank.exception;

public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String message) {
		super(message);

	}

	public InsufficientBalanceException(String message, Throwable t) {
		super(message, t);

	}
}
