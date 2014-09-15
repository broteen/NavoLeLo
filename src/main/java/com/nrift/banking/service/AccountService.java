package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;

import com.nrift.banking.dao.AccountDAO;
import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.exception.OptimisticLockException;

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
     * @throws BankingException 
     */
    public List<AccountDTO> getAllAccountDetails(Connection connection,long customerId) throws BankingException {
    	try{
    		return new AccountDAO(connection).getAllAccountDetails(customerId);
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
    }

    /**
     * Validate account.
     *
     * @param connection the connection
     * @param account_number the account_number
     * @return the long
     * @throws SQLException the SQL exception
     */
    public long validateAccount(Connection connection, long account_number) throws BankingException { 
    	try{
    	return new AccountDAO(connection).getCustomerId(account_number);
    	}catch(SQLException  e){
    		throw new BankingException(e);
    	}

    }

    /**
     * Gets the account details.
     *
     * @param connection the connection
     * @param receiverAccount the receiver account
     * @return the account details
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public AccountDTO getAccountDetails(Connection connection,long receiverAccount) throws BankingException {
    	try{
		return new AccountDAO(connection).getAccountDetails(receiverAccount);	
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
    }

    /**
     * Gets the update time.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the update time
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public Timestamp getUpdateTime(Connection connection,long accountNo) throws BankingException{
    	try{
        return new AccountDAO(connection).getUpdatedTime(accountNo);
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
    }

    /**
     * Gets the account history.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @return the account history
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public AccountDTO getAccountHistory(Connection connection, long accountNo) throws BankingException {
    	try{
        AccountDTO account= new AccountDAO(connection).getAccountDetails(accountNo);
        account.setTransactionHistoryDetailsList(new TransactionHistoryService(). getTransactionHistoryDetails(connection,account.getAccountNo()));
        return account;
    	}catch(SQLException e){
    		throw new BankingException(e);      
    	}
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
     * @throws BankingException 
     */
    public void withdrawAmount(Connection connection,long AccountNo, long amount, Timestamp recentUpdatedTime) throws BankingException {
    	try{
    	new AccountDAO(connection).WithdrawAmount(AccountNo,amount,recentUpdatedTime);
    	}catch(SQLException | OptimisticLockException e){
    		throw new BankingException(e);
    	}
        
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
    public void depositAmount(Connection connection,long receiverAccountNo, long amount, Timestamp recentUpdatedTime) throws BankingException {
    	try{
        new AccountDAO(connection).DepositeAmount(receiverAccountNo,amount,recentUpdatedTime);
    	}catch(SQLException | OptimisticLockException e){
    		throw new BankingException(e);
    	}
    }

    /**
     * Sets the updated by and updated time.
     *
     * @param connection the connection
     * @param accountNo the account no
     * @param userId the user id
     * @return true, if successful
     * @throws SQLException the SQL exception
     * @throws OptimisticLockException 
     */
    public void setUpdatedByandUpdatedTime(Connection connection, long accountNo,long userId) throws BankingException{
    	try{
        new AccountDAO(connection).setUpdatedByandUpdatedTime(accountNo,userId);
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
    }
    public void closeAccount(Connection connection, long accountNo) throws  BankingException {
    	try{
			new AccountDAO(connection).CloseAccount(accountNo);
		}catch(SQLException e){
			throw new BankingException(e);
    	}
	}
}
