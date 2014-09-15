package com.nrift.banking.exception;

@SuppressWarnings("serial")
public class BankingException extends Exception {
	public BankingException() {
		super();
	}

	public BankingException(String message) {
		super(message);
	}
	
	public BankingException(Throwable exception) {
		// TODO Auto-generated constructor stub
	}
	
	public BankingException(String message, Throwable exception) {
		super(message,exception);
	}
}