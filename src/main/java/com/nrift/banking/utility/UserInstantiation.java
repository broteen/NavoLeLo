/**
 * 
 */
package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

/**
 * The Class UserInstantiation.
 */
public class UserInstantiation {

    /**
     * Gets the customer details.
     *
     * @param connection the connection
     * @param userId the user id
     * @return the customer details
     * @throws ServletException the servlet exception
     */
    public static CustomerDTO getCustomerDetails(Connection connection, long userId) throws ServletException {

        return(new CustomerService().getCustomerDetails(connection,userId));

    }

    /**
     * Gets the admin details.
     *
     * @param connection the connection
     * @param userId the user id
     * @return the admin details
     * @throws ServletException the servlet exception
     */
    public static AdminDTO getAdminDetails(Connection connection, long userId) throws ServletException {

        return(new AdminService().getAdminDetails(connection,userId));
    }

}
