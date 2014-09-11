package com.nrift.banking.testCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nrift.banking.dto.CustomerDTO;

public class CustomerDTOTest{
    CustomerDTO customer = null;
    CustomerDTO customer2 = null;

    @Before
    public void setup(){
        customer.setCustomerId(12);
        customer.setName("abc");
        customer.setContactNumber(9433442);
        customer.setPanNumber("PANCARD");
        customer.setEmail("hello@nrifintech.com");
        customer.setAddress("xyz333");        
    }

    @Test
    public void testCheck() { 
        long customerId;
        String name;
        long contactNumber;
        String panNumber;
        String email;
        String address;
        customerId=customer.getCustomerId();
        name=customer.getName();
        contactNumber=customer.getContactNumber();
        panNumber=customer.getPanNumber();
        email=customer.getEmail();
        address=customer.getAddress();
        customer2 = new CustomerDTO(customerId,name, contactNumber,panNumber, email, address);
    }

    @After
    public void teardown() {

    }
}