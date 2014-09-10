package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

/**
 * The Class TransactionHistoryService.
 */
public class TransactionHistoryService {

    /**
     * Gets the transaction history details.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the transaction history details
     * @throws SQLException the SQL exception
     */
    public List<TransactionHistoryDTO> getTransactionHistoryDetails(Connection connection,long accountNo) throws SQLException {
        AccountService acc=new AccountService();
        AccountDTO accountDetails= acc.getAccountDetails(connection, accountNo);
        return new TransactionHistoryDAO(connection).getTransactionHistoryDetails(accountNo) ;
    }
}