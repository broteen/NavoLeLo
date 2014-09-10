package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;


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
     */
    public WithdrawAmountDTO validate(Connection connection, long account,
            long amount) throws SQLException  {
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
