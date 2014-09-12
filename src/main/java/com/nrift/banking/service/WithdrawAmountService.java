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
    public boolean IsWithdrawSuccessfull(Connection connection,WithdrawAmountDTO withdrawAmountDetails,long userId) throws SQLException{
        AccountService accountManager= new AccountService();
        TransactionService transaction= new TransactionService();
        long AccountNo=withdrawAmountDetails.getAccountNo();
        long amount=withdrawAmountDetails.getAmount();

        Timestamp updatedTime= accountManager.getUpdateTime(connection, withdrawAmountDetails.getAccountNo());
        if(updatedTime!=null && accountManager.IsAmountWithdrawn(connection,AccountNo,amount,updatedTime)){

            if(transaction.insertRowForWithdrawAmount(connection,AccountNo,amount)!=0){
            	if(accountManager.setUpdatedByandUpdatedTime(connection, withdrawAmountDetails.getAccountNo(),userId)){
                    connection.commit();
                    return true;
                }
            }
                		
        }
        connection.rollback();         //See if we need to throw an exception here and remove con.rollback() from here
        return false;
    }

}
