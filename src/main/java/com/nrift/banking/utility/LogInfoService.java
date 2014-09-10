package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

/**
 * The Class LogInfoService.
 */
public class LogInfoService {

    /**
     * Validate username.
     *
     * @param connection the connection
     * @param username the username
     * @return true, if successful
     * @throws ServletException the servlet exception
     */
    public boolean validateUsername(Connection connection, String username) throws ServletException{
        return(new UserValidationService().checkUserName(connection, username));

    }

}