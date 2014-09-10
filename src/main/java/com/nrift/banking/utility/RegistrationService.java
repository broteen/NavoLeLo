package com.nrift.banking.utility;

import java.sql.Connection;

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
     * @throws ServletException the servlet exception
     */
    public long validateAccountNumber(Connection connection, long accountNumber)throws ServletException {

        return(new AccountService().validateAccount(connection,accountNumber));
    }


    /**
     * Validate customer details.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return the customer dto
     * @throws ServletException the servlet exception
     */
    public CustomerDTO validateCustomerDetails(Connection connection,long customerId) throws ServletException
    {
        return(new CustomerService().validateCustomer(connection,customerId));
    }

    /**
     * Check user id.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return true, if successful
     * @throws ServletException the servlet exception
     */
    public boolean checkuserID(Connection connection,long customerId) throws ServletException
    {
        return(new CustomerService().checkuser_ID(connection,customerId));
    }
}


