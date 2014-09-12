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
    public long validateAccountNumber(Connection connection, long accountNumber)throws BankingException { //Couldn't understand this method kindly change it.

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
    public CustomerDTO validateCustomerDetails(Connection connection,long customerId) throws BankingException{ //Couldn't understand this method kindly change it.
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
    public boolean checkuserID(Connection connection,long customerId) throws SQLException{ //Couldn't understand this method kindly change it.
        return(new CustomerService().checkuser_ID(connection,customerId));
    }
}


