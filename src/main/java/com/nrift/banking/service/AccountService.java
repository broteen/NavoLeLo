package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.nrift.banking.dao.AccountDAO;
import com.nrift.banking.dto.AccountDTO;

/**
 * The Class AccountService.
 */
public class AccountService {

    /**
     * Gets the all account details.
     *
     * @param connection the connection
     * @param customerId the customer id
     * @return the all account details
     * @throws SQLException the SQL exception
     */
    public List<AccountDTO> getAllAccountDetails(Connection connection,long customerId) throws SQLException {

        return new AccountDAO(connection).getAllAccountDetails(customerId);
    }

    /**
     * Validate account.
     *
     * @param connection the connection
     * @param account_number the account_number
     * @return the long
     * @throws SQLException the SQL exception
     */
    public long validateAccount(Connection connection, long account_number) throws SQLException {

        long customerId= new AccountDAO(connection).getCustomerId(account_number);

        return customerId;

    }

    /**
     * Gets the account details.
     *
     * @param connection the connection
     * @param receiverAccount the receiver account
     * @return the account details
     * @throws SQLException the SQL exception
     */
    public AccountDTO getAccountDetails(Connection connection,long receiverAccount) throws SQLException {

        return new AccountDAO(connection).getAccountDetails(receiverAccount);
    }

    /**
     * Gets the update time.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the update time
     * @throws SQLException the SQL exception
     */
    public Timestamp getUpdateTime(Connection connection,long accountNo) throws SQLException{
        return new AccountDAO(connection).getUpdatedTime(accountNo);
    }

    /**
     * Gets the account history.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the account history
     * @throws SQLException the SQL exception
     */
    public  AccountDTO getAccountHistory(Connection connection, long accountNo) throws SQLException {

        AccountDTO account= new AccountDAO(connection).getAccountDetails(accountNo);
        if(account!=null)
        {
            account.setTransactionHistoryDetailsList(new TransactionHistoryService(). getTransactionHistoryDetails(connection,account.getAccountNo()));
        }
        return account;
    }

 
    /**
     * Checks if is amount withdrawn.
     *
     * @param connection the connection
     * @param AccountNo the account no
     * @param amount the amount
     * @param recentUpdatedTime the recent updated time
     * @param userID the user id
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean IsAmountWithdrawn(Connection connection,long AccountNo, long amount, Timestamp recentUpdatedTime, long userID) throws SQLException {
        if (new AccountDAO(connection).WithdrawAmount(AccountNo,amount,recentUpdatedTime, userID)==0)
            return false;
        else
            return true;
    }

    /**
     * Checks if is amount deposited.
     *
     * @param connection the connection
     * @param receiverAccountNo the receiver account no
     * @param amount the amount
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean IsAmountDeposited(Connection connection,long receiverAccountNo, long amount) throws SQLException {
        if (new AccountDAO(connection).DepositeAmount(receiverAccountNo,amount)==0)
            return false;
        else
            return true;
    }

    /**
     * Sets the updated by and updated time.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @param userId the user id
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean setUpdatedByandUpdatedTime(Connection connection, long accountNo,long userId) throws SQLException{
        if (new AccountDAO(connection).setUpdatedByandUpdatedTime(accountNo,userId)==0)
            return false;
        else
            return true;

    }
}
