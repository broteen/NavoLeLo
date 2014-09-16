package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.exception.OptimisticLockException;
import com.nrift.banking.utility.DBHelper;

/**
 * The Class Account-Data Access Object.
 */
public class AccountDAO {

    private Connection connection;

    private Logger logger = Logger.getLogger(AccountDAO.class);

    /**
     * Instantiates a new account dao.
     *
     * @param connection the connection
     */
    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    /** The Constant ALL_ACCOUNT_QUERY_STRING. */
    private static final String ALL_ACCOUNT_QUERY_STRING="select b.account_number,a.account_type,b.balance,b.updated_time from account_type a "
            + "inner join account b on a.account_type_id = b.account_type_id  where customer_id=? and b.status=?";


    /** The Constant ACCOUNT_QUERY_STRING. */
    private static final String ACCOUNT_QUERY_STRING = "select b.account_number,a.account_type,b.balance,b.updated_time from account_type a "
            + "inner join account b on a.account_type_id = b.account_type_id  where b.account_number=? and b.status=?";



    /** The Constant CUSTOMER_QUERY_STRING. */
    private static final String CUSTOMER_QUERY_STRING="select customer_id,status from account where account_number=? and status=?";


    /** The Constant UPDATED_TIME_QUERY_STRING. */
    private static final String UPDATED_TIME_QUERY_STRING= "select updated_time from account where account_number=? and status=?";


    /** The Constant WITHDRAW_QUERY_STRING. */
    private static final String WITHDRAW_QUERY_STRING="update account set balance=balance-? where account_number=? and status=? and updated_time=?";

    /** The Constant DEPOSITE_QUERY_STRING. */
    private static final String DEPOSITE_QUERY_STRING="update account set balance=balance+? where account_number=? and status=? and updated_time=?";


    /** The Constant CLOSE_ACCOUNT_QUERY_STRING. */
    private static final String CLOSE_ACCOUNT_QUERY_STRING="update account set status=? where account_number=? and status=?";


    /** The Constant UPDATED_BY_AND_TIME_QUERY_STRING. */
    private static final String UPDATED_BY_AND_TIME_QUERY_STRING= "update account set updated_by=?,updated_time=? where account_number=? and status=?";


    /**
     * Gets the all account details.
     *
     * @param customerId the customer id
     * @return the all account details
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public List<AccountDTO> getAllAccountDetails(long customerId) throws SQLException, BankingException{
    	ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, ALL_ACCOUNT_QUERY_STRING, customerId,"normal");
            if(rs != null) {
            	List<AccountDTO> list = new ArrayList<AccountDTO>();
                while(rs.next()) {
                    AccountDTO validAccount = new AccountDTO(
                            rs.getLong("ACCOUNT_NUMBER"),
                            rs.getString("ACCOUNT_TYPE"),
                            rs.getLong("BALANCE"),
                            rs.getTimestamp("UPDATED_TIME"));
                    logger.info("Customer found with details=" + validAccount);
                    list.add(validAccount);
                }
                if(!list.isEmpty())
                	return list;  	
            }
            throw new BankingException("All Account Details are Empty");
            
        }finally {
            DBHelper.closeResultSet(rs);
        }
    }



    /**
     * Gets the account details.
     *
     * @param accountNo the account no
     * @return the account details
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public AccountDTO getAccountDetails(long accountNo ) throws SQLException, BankingException {
    	ResultSet rs = null;
        try {
        	rs = DBHelper.getResultSetFromSQL(connection, ACCOUNT_QUERY_STRING, accountNo,"normal");
        	if(rs !=null && rs.next())
        	{
        		AccountDTO accountDetails = new AccountDTO(rs.getLong("ACCOUNT_NUMBER"),rs.getString("ACCOUNT_TYPE"),rs.getLong("BALANCE"),rs.getTimestamp("UPDATED_TIME"));
        		logger.info("Customer found with details="+accountDetails);
        		return accountDetails;
        	}
        	throw new BankingException("Account Details is Empty");
        }finally{
        	DBHelper.closeResultSet(rs);
        }

    }

    /**
     * Gets the customer id.
     *
     * @param accountNumber the account number
     * @return the customer id
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public long getCustomerId(long accountNumber) throws SQLException, BankingException{     
        ResultSet rs = null;
        try {
            rs=DBHelper.getResultSetFromSQL(connection, CUSTOMER_QUERY_STRING, accountNumber,"normal");
            if(rs !=null && rs.next()){
            	long customerId=rs.getLong("CUSTOMER_ID");
            	return customerId;
            }
            throw new BankingException("CustomerId is Empty");
        }finally{
            DBHelper.closeResultSet(rs);
        }
    }

    /**
     * Gets the updated time.
     *
     * @param accountNo the account no
     * @return the updated time
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public Timestamp getUpdatedTime(long accountNo) throws SQLException, BankingException {
        ResultSet rs = null;
        try {

            rs = DBHelper.getResultSetFromSQL(connection,UPDATED_TIME_QUERY_STRING, accountNo,"normal");
            if(rs !=null && rs.next())
                return rs.getTimestamp("UPDATED_TIME");
            else
            	throw new BankingException("Updated Time is NULL");
        }
        finally{
            DBHelper.closeResultSet(rs);
        }
    }

    /**
     * Withdraw amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @param recentUpdatedTime the recent updated time
     * @param userID the user id
     * @return the value returned after the query execution
     * @throws SQLException the SQL exception
     * @throws OptimisticLockException 
     */
    public void WithdrawAmount(long accountNo, long amount, Timestamp recentUpdatedTime) throws SQLException, OptimisticLockException {
            try {
                int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, WITHDRAW_QUERY_STRING, amount,accountNo,"normal",recentUpdatedTime);
                if(updatedRows==0)
                	throw new OptimisticLockException("Error in Withdrawing");
            }finally{
            }
    }

    /**
     * Close account.
     *
     * @param accountNo the account no
     * @return the int
     * @throws SQLException the SQL exception
     * @throws  
     */
    public void closeAccount(long accountNo)throws SQLException,BankingException{
        try {
            int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, CLOSE_ACCOUNT_QUERY_STRING,"cancel",accountNo,"normal");
            if(updatedRows==0)
            	throw new BankingException("Error while closing an account");
        }finally{
        }
    }

    /**
     * Deposit amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public void DepositeAmount(long accountNo, long amount,Timestamp recentUpdatedTime) throws SQLException,OptimisticLockException{
    	try {
            int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, DEPOSITE_QUERY_STRING, amount,accountNo,"normal",recentUpdatedTime);
            if(updatedRows==0)
            	throw new OptimisticLockException("Error in Depositing");
        }finally{
        }
    }

    /**
     * Sets the updated by and updated time.
     *
     * @param accountNo the account no
     * @param userId the user id
     * @throws SQLException the SQL exception
     * @throws OptimisticLockException 
     */
    public void setUpdatedByandUpdatedTime(long accountNo,long userId) throws SQLException, OptimisticLockException{
        try {
            int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, UPDATED_BY_AND_TIME_QUERY_STRING, userId,new Timestamp(new java.util.Date().getTime()),accountNo,"normal");
            if(updatedRows==0)
            	throw new OptimisticLockException("Error in Depositing");
        }finally{
        }
    
    }
}
