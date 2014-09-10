package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;




import javax.servlet.ServletException;

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
     * @throws ServletException the servlet exception
     */
    public List<AccountDTO> getAllAccountDetails(Connection connection,long customerId) throws ServletException {

        return new AccountDAO(connection).getAllAccountDetails(customerId);
    }

    /**
     * Validate account.
     *
     * @param connection the connection
     * @param account_number the account_number
     * @return the long
     * @throws ServletException the servlet exception
     */
    public long validateAccount(Connection connection, long account_number) throws ServletException {

        long customerId= new AccountDAO(connection).getCustomerId(account_number);

        return customerId;

    }

    /**
     * Gets the account details.
     *
     * @param connection the connection
     * @param receiverAccount the receiver account
     * @return the account details
     * @throws ServletException the servlet exception
     */
    public AccountDTO getAccountDetails(Connection connection,long receiverAccount) throws ServletException {

        return new AccountDAO(connection).getAccountDetails(receiverAccount);
    }

    /**
     * Gets the update time.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the update time
     * @throws ServletException the servlet exception
     */
    public Timestamp getUpdateTime(Connection connection,long accountNo) throws ServletException{
        return new AccountDAO(connection).getUpdatedTime(accountNo);
    }

    /**
     * Gets the account history.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the account history
     * @throws ServletException the servlet exception
     */
    public  AccountDTO getAccountHistory(Connection connection, long accountNo) throws ServletException {

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
     * @return true, if successful
     * @throws ServletException the servlet exception
     */
    public boolean IsAmountWithdrawn(Connection connection,long AccountNo, long amount) throws ServletException {
        if (new AccountDAO(connection).WithdrawAmount(AccountNo,amount)==0)
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
     * @throws ServletException the servlet exception
     */
    public boolean IsAmountDeposited(Connection connection,long receiverAccountNo, long amount) throws ServletException {
        if (new AccountDAO(connection).DepositeAmount(receiverAccountNo,amount)==0)
            return false;
        else
            return true;
    }

    /**
     * Sets the updated byand updated time.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @param userId the user id
     * @return true, if successful
     * @throws ServletException the servlet exception
     */
    public boolean setUpdatedByandUpdatedTime(Connection connection, long accountNo,long userId) throws ServletException {
        if (new AccountDAO(connection).setUpdatedByandUpdatedTime(accountNo,userId)==0)
            return false;
        else
            return true;

    }
}
