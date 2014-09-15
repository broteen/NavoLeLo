package com.nrift.banking;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.nrift.banking.dao.CustomerDAO;
import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.exception.BankingException;
/*
public class CustomerDAOTest extends DAOTest{
    @Test
    public void testGetCustomerDetailsByCustomerId() {
        CustomerDAO customerDAO = new CustomerDAO(getConnection());
        try {
            CustomerDTO customerDTO = customerDAO.getCustomerDetailsByCustomerId(1);
            assertEquals("Zeeshan Khan", customerDTO.getName());   
        } catch (SQLException | BankingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void testgetCustomerDetailsByUserId() {
        CustomerDAO customerDAO = new CustomerDAO(getConnection());
        try {
            CustomerDTO customerDTO = customerDAO.getCustomerDetailsByUserId(5);
            assertEquals("kolkata", customerDTO.getAddress());   
        } catch (SQLException | BankingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}*/
