package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.dao.CustomerDAO;
import com.nrift.banking.dto.CustomerDTO;

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
     * @throws SQLException the SQL exception
     */
    public  CustomerDTO getCustomerDetails(Connection connection, long userId) throws SQLException {

        CustomerDTO customer= new CustomerDAO(connection).getCustomerDetailsByUserId(userId);
        if(customer!=null)
        {
            customer.setAccountList(new AccountService().getAllAccountDetails(connection,customer.getCustomerId()));
        }
        return customer;
    }

    /**
     * Validate customer.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return the customer dto
     * @throws SQLException the SQL exception
     */
    public CustomerDTO validateCustomer(Connection connection,long customerId) throws SQLException
    {
        CustomerDTO customer=new CustomerDAO(connection).getCustomerDetailsByCustomerId(customerId);
        return customer;
    }

    /**
     * Checkuser_ id.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean checkuser_ID(Connection connection,long customerId) throws SQLException
    {
        boolean user_id=new CustomerDAO(connection).checkUserId(customerId);
        return user_id;
    }

}