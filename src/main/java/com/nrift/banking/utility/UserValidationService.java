package com.nrift.banking.utility;

import java.sql.Connection;

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
     * @throws ServletException the servlet exception
     */
    public UserDetails validate(Connection connection, String username,String password) throws ServletException {
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

    public boolean checkUserName(Connection connection, String username) throws ServletException
    {
        return(new UserValidationDAO(connection).validateUserName(username));
    }


}
