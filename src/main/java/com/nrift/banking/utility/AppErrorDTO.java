package com.nrift.banking.utility;

import java.io.Serializable;

/**
 * The Class AppError-Data Transfer Object.
 */
public class AppErrorDTO implements Serializable {
    private static final long serialVersionUID = 6297385302078200513L;

    private int statusCode;
    private String requestedUri;
    private String name;
    private String message;
    private String customText;

    /**
     * @return the errorCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the errorText
     */
    public String getCustomText() {
        return customText;
    }

    /**
     * @param errorText the errorText to set
     */
    public void setCustomText(String customText) {
        this.customText = customText;
    }

    /**
     * @return the requestedUri
     */
    public String getRequestedUri() {
        return requestedUri;
    }

    /**
     * @param requestedUri the requestedUri to set
     */
    public void setRequestedUri(String requestedUri) {
        this.requestedUri = requestedUri;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
