package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.dao.UserValidationDAO;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.utility.UserInstantiation;

/**
 * The Class UserValidationService.
 */
public class UserValidationService {

    /**
     * Validate the user
     *
     * @param connection the connection
     * @param username the username
     * @param password the password
     * @return the user details
     * @throws SQLException the SQL exception
     */
    public UserDTO validate(Connection connection, String username,String password) throws SQLException {
        UserDTO user= new UserValidationDAO(connection).validate(username, password);

        if(user!=null)
        {
            if(user.isAdmin()== true)
            {
                user.setAdminDetails(UserInstantiation.getAdminDetails(connection, user.getUserId()));
            }
            else
            {
                user.setCustomerDetails(UserInstantiation.getCustomerDetails(connection, user.getUserId()));
            }
        }
        return user;

    }

    /**
     * Check user name.
     *
     * @param connection the connection
     * @param username the username
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean checkUserName(Connection connection, String username) throws SQLException
    {
        return(new UserValidationDAO(connection).validateUserName(username));
    }


}