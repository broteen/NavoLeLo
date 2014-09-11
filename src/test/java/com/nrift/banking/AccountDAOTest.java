package com.nrift.banking;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nrift.banking.dao.AccountDAO;
import com.nrift.banking.utility.DBConnectionManager;

public class AccountDAOTest {
    private Connection connection;


    @Before
    public void setup(){
        try {
            DBConnectionManager dbConnectionManager = new DBConnectionManager("jdbc:oracle:thin:@172.16.29.43:1521:D11GR22", "frs2014_obnkg", "frs2014_obnkg");
            this.connection = dbConnectionManager.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCustomerDetailsByCustomerId() {
        AccountDAO accountDAO = new AccountDAO(connection);
        try {
            long customerID = accountDAO.getCustomerId(12345601);
            assertEquals(1, customerID);   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @After
    public void teardown() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
