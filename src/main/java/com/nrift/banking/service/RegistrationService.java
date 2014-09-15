package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.exception.BankingException;

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
     * @throws BankingException 
     */
    public long validateAccountNumber(Connection connection, long accountNumber)throws BankingException { 

        return(new AccountService().validateAccount(connection,accountNumber));
    }


    /**
     * Validate customer details.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return the customer dto
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public CustomerDTO validateCustomerDetails(Connection connection,long customerId) throws BankingException{
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
    public void checkUserId(Connection connection,long customerId) throws BankingException{ 
        new CustomerService().checkUserId(connection,customerId);
    }
}


