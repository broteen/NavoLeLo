package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

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
     * @throws ServletException the servlet exception
     */
    public  CustomerDTO getCustomerDetails(Connection connection, long userId) throws ServletException {

        CustomerDTO customer= new CustomerDAO(connection).getCustomerDetails(userId);
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
     * @throws ServletException the servlet exception
     */
    public CustomerDTO validateCustomer(Connection connection,long customerId) throws ServletException
    {
        CustomerDTO customer=new CustomerDAO(connection).getCustomer_Details(customerId);
        return customer;
    }

    /**
     * Checkuser_ id.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return true, if successful
     * @throws ServletException the servlet exception
     */
    public boolean checkuser_ID(Connection connection,long customerId) throws ServletException
    {
        boolean user_id=new CustomerDAO(connection).checkUserId(customerId);
        return user_id;
    }
}