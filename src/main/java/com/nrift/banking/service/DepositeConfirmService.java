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
        if(updatedTime!=null && accountManager.IsAmountDeposited(connection,receiverAccountNo,amount)){
            if(transaction.insertRowForDepositeAmount(connection,receiverAccountNo,amount)!=0 && 
                    updatedTime.equals(accountManager.getUpdateTime(connection, receiverAccountNo))){

                if(accountManager.setUpdatedByandUpdatedTime(connection,receiverAccountNo, userId)){
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                }
            }
        }else{
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        return false;
    }	
}


