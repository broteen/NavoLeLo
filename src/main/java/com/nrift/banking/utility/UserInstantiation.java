/**
 * 
 */
package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.dto.AdminDTO;
import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.service.AdminService;
import com.nrift.banking.service.CustomerService;

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
     * @throws SQLException the SQL exception
     */
    public static CustomerDTO getCustomerDetails(Connection connection, long userId) throws SQLException {

        return(new CustomerService().getCustomerDetails(connection,userId));

    }

    /**
     * Gets the admin details.
     *
     * @param connection the connection
     * @param userId the user id
     * @return the admin details
     * @throws SQLException the SQL exception
     */
    public static AdminDTO getAdminDetails(Connection connection, long userId) throws SQLException {

        return(new AdminService().getAdminDetails(connection,userId));
    }
}
