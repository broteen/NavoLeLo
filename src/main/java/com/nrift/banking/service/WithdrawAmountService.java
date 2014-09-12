package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.nrift.banking.dto.WithdrawAmountDTO;

/**
 * The Class WithdrawAmountService.
 */
public class WithdrawAmountService {


    /**
     * Checks if is withdraw successful.
     *
     * @param connection the connection
     * @param withdrawAmountDetails the withdraw amount details
     * @param userID the user id
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean IsWithdrawSuccessfull(Connection connection,WithdrawAmountDTO withdrawAmountDetails,long userID) throws SQLException{
        AccountService accountManager= new AccountService();
        TransactionService transaction= new TransactionService();
        long AccountNo=withdrawAmountDetails.getAccountNo();
        long amount=withdrawAmountDetails.getAmount();

        Timestamp updatedTime= accountManager.getUpdateTime(connection, withdrawAmountDetails.getAccountNo());
        if(updatedTime!=null && accountManager.IsAmountWithdrawn(connection,AccountNo,amount,updatedTime,userID)){

            if(transaction.insertRowForWithdrawAmount(connection,AccountNo,amount)!=0){
            	 connection.commit();
                 connection.setAutoCommit(true);
                 return true;
            }
                		
        }else{
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        return false;
    }

}
