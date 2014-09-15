package com.nrift.banking.exception;

@SuppressWarnings("serial")
public class OptimisticLockException extends BankingException {
	public OptimisticLockException(){
		super();
	}
	
	public OptimisticLockException(String message){
		super(message);
	}
	
	public OptimisticLockException(Throwable exception){
		super(exception);
	}
	
	public OptimisticLockException(String message,Throwable exception){
		super(message,exception);
	}
}
