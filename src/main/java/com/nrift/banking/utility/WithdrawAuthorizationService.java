package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

/**
 * The Class WithdrawAuthorizationService.
 */
public class WithdrawAuthorizationService {

    /**
     * Validate the withdraw transaction
     *
     * @param connection the connection
     * @param account the account
     * @param amount the amount
     * @return the withdraw amount dto
     * @throws ServletException the servlet exception
     */
    public WithdrawAmountDTO validate(Connection connection, long account,
            long amount) throws ServletException {
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
