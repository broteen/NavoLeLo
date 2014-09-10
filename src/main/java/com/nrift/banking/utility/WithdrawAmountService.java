package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * The Class WithdrawAmountService.
 */
public class WithdrawAmountService {

    /**
     * Checks if is withdraw successful.
     *
     * @param connection the connection
     * @param withdrawAmountDetails the withdraw amount details
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean IsWithdrawSuccessfull(Connection connection,WithdrawAmountDTO withdrawAmountDetails) throws SQLException{
        AccountService accountManager= new AccountService();
        TransactionService transaction= new TransactionService();
        long AccountNo=withdrawAmountDetails.getAccountNo();
        long amount=withdrawAmountDetails.getAmount();

        Timestamp updatedTime= accountManager.getUpdateTime(connection, withdrawAmountDetails.getAccountNo());
        if(updatedTime!=null && accountManager.IsAmountWithdrawn(connection,AccountNo,amount)){

            if(transaction.insertRowForWithdrawAmount(connection,AccountNo,amount)!=0)
                return true;		
        }
        return false;
    }

}
