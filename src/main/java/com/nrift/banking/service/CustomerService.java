package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.nrift.banking.dao.CustomerDAO;
import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.exception.OptimisticLockException;

/**
 * The Class CustomerService.
 */
public class CustomerService {

    /**
     * Gets the customer details.
     *
     * @param connection the connection
     * @param userId the user id
     * @return the customer details
     * @throws BankingException 
     * @throws SQLException the SQL exception
     */
    public  CustomerDTO getCustomerDetails(Connection connection, long userId) throws BankingException {
    	try{
        CustomerDTO customer= new CustomerDAO(connection).getCustomerDetailsByUserId(userId);
        customer.setAccountList(new AccountService().getAllAccountDetails(connection,customer.getCustomerId()));
        return customer;
    	}catch(SQLException e){
    		throw new BankingException(e);      
    	}
    }

    /**
     * Validate customer.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return the customer dto
     * @throws BankingException 
     * @throws SQLException the SQL exception
     */
    public CustomerDTO validateCustomer(Connection connection,long customerId) throws BankingException{
    	try{
        return new CustomerDAO(connection).getCustomerDetailsByCustomerId(customerId);
    	}catch(SQLException e){
    		throw new BankingException(e);      
    	}
    }

    /**
     * Checkuser_ id.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return true, if successful
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public void checkUserId(Connection connection,long customerId) throws BankingException
    {
    	try{
    		 new CustomerDAO(connection).checkUserId(customerId);  
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
           
    }

}