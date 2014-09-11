package com.nrift.banking.exception;

/**
 * The Class CustomException.
 */
public class CustomException extends Exception{
    private static final long serialVersionUID = 4664456874499611218L;
    private String errorCode="Unknown_Exception";

    /**
     * Instantiates a new custom exception.
     *
     * @param message the message
     * @param errorCode the error code
     */
    public CustomException(String message, String errorCode){
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
