package com.nrift.banking.exception;

/**
 * The Class CustomException.
 */
public class InsufficientPrivilegeException extends BankingException{
    private String errorCode="Unknown_Exception";

    /**
     * Instantiates a new custom exception.
     *
     * @param message the message
     * @param errorCode the error code
     */
    public InsufficientPrivilegeException(String message){
        super(message);
        //this.errorCode=errorCode;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode(){
        return this.errorCode;
    }
}
