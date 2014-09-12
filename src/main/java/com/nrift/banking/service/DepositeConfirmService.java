package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;

import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.TransactionService;

/**
 * The Class DepositeConfirmService.
 */
public class DepositeConfirmService {

    /**
     * Checks if amount is deposited.
     *
     * @param connection the connection
     * @param receiverAccountNo the receiver account no
     * @param amount the amount
     * @param userId the user id
     * @return true, if successful
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public void makeDeposit(Connection connection,long receiverAccountNo, long amount, long userId) throws BankingException{
    	try{
        AccountService accountManager= new AccountService();
        TransactionService transaction= new TransactionService();
        Timestamp updatedTime= accountManager.getUpdateTime(connection, receiverAccountNo);
        accountManager.depositAmount(connection,receiverAccountNo,amount,updatedTime);
        transaction.insertRowForDepositeAmount(connection,receiverAccountNo,amount);
        accountManager.setUpdatedByandUpdatedTime(connection,receiverAccountNo, userId);
        connection.commit();
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}    
    }	
}


