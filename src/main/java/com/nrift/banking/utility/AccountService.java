package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;




import javax.servlet.ServletException;

public class AccountService {

    public List<AccountDTO> getAllAccountDetails(Connection connection,long customerId) throws ServletException {

        return new AccountDAO(connection).getAllAccountDetails(customerId);
    }

    public long validateAccount(Connection connection, long account_number) throws ServletException {

        long customerId= new AccountDAO(connection).getCustomerId(account_number);

        return customerId;

    }

    public AccountDTO getAccountDetails(Connection connection,long receiverAccount) throws ServletException {

        return new AccountDAO(connection).getAccountDetails(receiverAccount);
    }

    public Timestamp getUpdateTime(Connection connection,long accountNo) throws ServletException{
        return new AccountDAO(connection).getUpdatedTime(accountNo);
    }

    public  AccountDTO getAccountHistory(Connection connection, long accountNo) throws ServletException {

        AccountDTO account= new AccountDAO(connection).getAccountDetails(accountNo);
        if(account!=null)
        {
            account.setTransactionHistoryDetailsList(new TransactionHistoryService(). getTransactionHistoryDetails(connection,account.getAccountNo()));
        }
        return account;
    }
    public boolean IsAmountWithdrawn(Connection connection,long AccountNo, long amount) throws ServletException {
        if (new AccountDAO(connection).WithdrawAmount(AccountNo,amount)==0)
            return false;
        else
            return true;
    }

    public boolean IsAmountDeposited(Connection connection,long receiverAccountNo, long amount) throws ServletException {
        if (new AccountDAO(connection).DepositeAmount(receiverAccountNo,amount)==0)
            return false;
        else
            return true;
    }

    public boolean setUpdatedByandUpdatedTime(Connection connection, long accountNo,long userId) throws ServletException {
        if (new AccountDAO(connection).setUpdatedByandUpdatedTime(accountNo,userId)==0)
            return false;
        else
            return true;

    }
}
