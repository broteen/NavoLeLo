package com.nrift.banking.utility;

import java.sql.Connection;
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
     */
    public boolean IsWithdrawSuccessfull(Connection connection,
            WithdrawAmountDTO withdrawAmountDetails) {
        // TODO Auto-generated method stub
        try{
            AccountService accountManager= new AccountService();
            TransactionService transaction= new TransactionService();
            long AccountNo=withdrawAmountDetails.getAccountNo();
            long amount=withdrawAmountDetails.getAmount();

            Timestamp updatedTime= accountManager.getUpdateTime(connection, withdrawAmountDetails.getAccountNo());
            if(updatedTime!=null && accountManager.IsAmountWithdrawn(connection,AccountNo,amount)){

                if(transaction.insertRowForWithdrawAmount(connection,AccountNo,amount)!=0)
                    return true;
                else
                    return false;
            }
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return false;
    }

}
