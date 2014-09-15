package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.nrift.banking.exception.BankingException;


/**
 * The Class DepositeService.
 */
public class DepositeService {

    /**
     * Validate account number.
     *
     * @param con the con
     * @param accountNumber the account number
     * @return the long
     * @throws BankingException 
     * @throws SQLException the SQL exception
     */
    public long validateAccountNumber(Connection con,long accountNumber) throws BankingException {

        return new AccountService().validateAccount(con,accountNumber);	
    }
}
