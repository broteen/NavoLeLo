package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

/**
 * The Class RegistrationService.
 */
public class RegistrationService {

    /**
     * Validate account number.
     *
     * @param connection the connection
     * @param accountNumber the account number
     * @return the long
     * @throws SQLException the SQL exception
     */
    public long validateAccountNumber(Connection connection, long accountNumber)throws SQLException {

        return(new AccountService().validateAccount(connection,accountNumber));
    }


    /**
     * Validate customer details.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return the customer dto
     * @throws SQLException the SQL exception
     */
    public CustomerDTO validateCustomerDetails(Connection connection,long customerId) throws SQLException
    {
        return(new CustomerService().validateCustomer(connection,customerId));
    }

    /**
     * Check user id.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean checkuserID(Connection connection,long customerId) throws SQLException
    {
        return(new CustomerService().checkuser_ID(connection,customerId));
    }
}


