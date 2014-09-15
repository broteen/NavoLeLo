package com.nrift.banking.exception;

/**
 * The Class CustomException.
 */
public class InsufficientPriviledgeException extends BankingException{
    private static final long serialVersionUID = 4664456874499611218L;
    private String errorCode="Unknown_Exception";

    /**
     * Instantiates a new custom exception.
     *
     * @param message the message
     * @param errorCode the error code
     */
    public InsufficientPriviledgeException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
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
