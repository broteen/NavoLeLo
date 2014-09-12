package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.dto.WithdrawAmountDTO;
import com.nrift.banking.exception.BankingException;


/**
 * The Class WithdrawAuthorizationService.
 */
public class WithdrawAuthorizationService {

    /**
     * Validate the account and amount
     *
     * @param connection the connection
     * @param account the account
     * @param amount the amount
     * @return the withdraw amount dto
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public WithdrawAmountDTO validate(Connection connection, long account,
            long amount) throws SQLException, BankingException  {
        AccountService acc = new AccountService();
        AccountDTO AccountDetails = acc.getAccountDetails(connection,account);
        if (AccountDetails == null)
            return null;
        if (AccountDetails.getBalance() > amount)
            return new WithdrawAmountDTO(account, amount,AccountDetails.getUpdatedTime());
        else
            return null;
    }

}
