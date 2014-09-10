package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

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
    public UserDetails validate(Connection connection, String username,String password) throws SQLException {
        UserDetails user= new UserValidationDAO(connection).validate(username, password);

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
