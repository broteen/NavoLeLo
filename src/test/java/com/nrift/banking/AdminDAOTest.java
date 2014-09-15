package com.nrift.banking;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.nrift.banking.dao.AdminDAO;
import com.nrift.banking.dto.AdminDTO;
import com.nrift.banking.exception.BankingException;
/*
public class AdminDAOTest extends DAOTest{

    @Test
    public void testGetAdminDetails() {
        AdminDAO AdminDAO = new AdminDAO(getConnection());
        try {
            AdminDTO adminDTO = AdminDAO.getAdminDetails(1);
            assertEquals("Rick Riordan", adminDTO.getName());   
        } catch (SQLException | BankingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

}*/
