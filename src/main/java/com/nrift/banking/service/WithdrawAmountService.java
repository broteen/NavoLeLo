package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.nrift.banking.dto.WithdrawAmountDTO;
import com.nrift.banking.exception.BankingException;

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
    public void makeWithdraw(Connection connection,WithdrawAmountDTO withdrawAmountDetails,long userId) throws BankingException{
        try{
    	AccountService accountManager= new AccountService();
        TransactionService transaction= new TransactionService();
        long AccountNo=withdrawAmountDetails.getAccountNo();
        long amount=withdrawAmountDetails.getAmount();

        Timestamp updatedTime= accountManager.getUpdateTime(connection, withdrawAmountDetails.getAccountNo());
        accountManager.withdrawAmount(connection,AccountNo,amount,updatedTime);
        transaction.insertRowForWithdrawAmount(connection,AccountNo,amount);
        accountManager.setUpdatedByandUpdatedTime(connection, withdrawAmountDetails.getAccountNo(),userId);
        connection.commit();
        }catch(SQLException e){
    		throw new BankingException(e);
    	} 
                
    }

}
