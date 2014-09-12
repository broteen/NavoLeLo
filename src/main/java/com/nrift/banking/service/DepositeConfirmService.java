package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;

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
     */
    public boolean IsDeposited(Connection connection,long receiverAccountNo, long amount, long userId) throws SQLException{
        AccountService accountManager= new AccountService();
        TransactionService transaction= new TransactionService();
        Timestamp updatedTime= accountManager.getUpdateTime(connection, receiverAccountNo);
        if(updatedTime!=null && accountManager.IsAmountDeposited(connection,receiverAccountNo,amount,updatedTime)){
            if(transaction.insertRowForDepositeAmount(connection,receiverAccountNo,amount)!=0){

                if(accountManager.setUpdatedByandUpdatedTime(connection,receiverAccountNo, userId)){
                    connection.commit();
                    return true;
                }
            }
        }
        connection.rollback();         //See if we need to throw an exception here and remove con.rollback() from here
        return false;
    }	
}


