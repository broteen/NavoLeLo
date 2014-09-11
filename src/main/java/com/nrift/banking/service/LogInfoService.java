package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

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
     * @throws SQLException the SQL exception
     */
    public boolean validateUsername(Connection connection, String username) throws SQLException{
        return(new UserValidationService().checkUserName(connection, username));

    }

}