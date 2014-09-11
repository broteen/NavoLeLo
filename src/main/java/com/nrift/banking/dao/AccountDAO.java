package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.AccountDTO;
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

    /**
     * Gets the all account query string.
     *
     * @return the all account query string
     */
    private String getAllAccountQueryString() {
        return "SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
                + "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE CUSTOMER_ID=?";
    }

    /**
     * Gets the account query string.
     *
     * @return the account query string
     */
    private String getAccountQueryString() {
        return "SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
                + "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE B.ACCOUNT_NUMBER=?";
    }

    /**
     * Gets the customer query string.
     *
     * @return the customer query string
     */
    private String getCustomerQueryString() {
        return "SELECT CUSTOMER_ID,STATUS FROM ACCOUNT WHERE ACCOUNT_NUMBER=?";
    }

    /**
     * Gets the updated time query string.
     *
     * @return the updated time query string
     */
    private String getUpdatedTimeQueryString() {
        return "SELECT UPDATED_TIME FROM ACCOUNT WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }

    /**
     * Gets the withdraw query string.
     *
     * @return the withdraw query string
     */
    private String getWithdrawQueryString() {
        return "UPDATE ACCOUNT SET BALANCE=BALANCE-? WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }

    /**
     * Gets the deposite query string.
     *
     * @return the deposite query string
     */
    private String getDepositeQueryString() {
        return "UPDATE ACCOUNT SET BALANCE=BALANCE+? WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }

    /**
     * Gets the close account query string.
     *
     * @return the close account query string
     */
    private String getCloseAccountQueryString() {
        return "UPDATE ACCOUNT SET STATUS=? WHERE ACCOUNT_NUMBER=?";
    }

    /**
     * Gets the updated by and time query string.
     *
     * @return the updated by and time query string
     */
    private String getUpdatedByAndTimeQueryString() {
        return "UPDATE ACCOUNT SET UPDATED_BY=?,UPDATED_TIME=? WHERE ACCOUNT_NUMBER=? AND STATUS=?";
    }


    /**
     * Gets the all account details.
     *
     * @param customerId the customer id
     * @return the all account details
     * @throws SQLException the SQL exception
     */
    public List<AccountDTO> getAllAccountDetails(long customerId) throws SQLException{

        List<AccountDTO> list = new ArrayList<AccountDTO>();
        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, getAllAccountQueryString(), customerId);
            if(rs != null) {
                while(rs.next()) {
                    AccountDTO validAccount = new AccountDTO(
                            rs.getLong("ACCOUNT_NUMBER"),
                            rs.getString("ACCOUNT_TYPE"),
                            rs.getLong("BALANCE"),
                            rs.getTimestamp("UPDATED_TIME"));
                    logger.info("Customer found with details=" + validAccount);
                    list.add(validAccount);
                }
            }
        } finally {
            DBHelper.closeResultSet(rs);
        }
        return list;

    }



    /**
     * Gets the account details.
     *
     * @param accountNo the account no
     * @return the account details
     * @throws SQLException the SQL exception
     */
    public AccountDTO getAccountDetails(long accountNo ) throws SQLException {

        AccountDTO accountDetails = null;
        ResultSet rs = null;
        try {


            rs = DBHelper.getResultSetFromSQL(connection, getAccountQueryString(), accountNo);
            if(rs !=null && rs.next())
            {
                accountDetails = new AccountDTO(rs.getLong("ACCOUNT_NUMBER"),rs.getString("ACCOUNT_TYPE"),rs.getLong("BALANCE"),rs.getTimestamp("UPDATED_TIME"));
                logger.info("Customer found with details="+accountDetails);
            }
            return accountDetails;
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
     */
    public long getCustomerId(long accountNumber) throws SQLException
    {
        long customerID=0L; 
        ResultSet rs = null;

        try {
            rs=DBHelper.getResultSetFromSQL(connection, getCustomerQueryString(), accountNumber);
            if(rs !=null && rs.next()){
                customerID=rs.getLong("CUSTOMER_ID");
                String status=rs.getString("STATUS");
                if(status.equals("cancel")){
                    customerID=0L;
                }
            }
        }finally{
            DBHelper.closeResultSet(rs);
        }
        return customerID;
    }

    /**
     * Gets the updated time.
     *
     * @param accountNo the account no
     * @return the updated time
     * @throws SQLException the SQL exception
     */
    public Timestamp getUpdatedTime(long accountNo) throws SQLException {
        Timestamp timestamp=null;
        ResultSet rs = null;
        try {

            rs = DBHelper.getResultSetFromSQL(connection,getUpdatedTimeQueryString(), accountNo,"normal");
            if(rs !=null && rs.next())
                timestamp=rs.getTimestamp("UPDATED_TIME");
        }
        finally{
            DBHelper.closeResultSet(rs);
        }
        return timestamp;
    }

    /**
     * Withdraw amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int WithdrawAmount(long accountNo, long amount) throws SQLException {
        int updatedRows=0;
        try {
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, getWithdrawQueryString(), amount,accountNo,"normal");

        }finally{
        }
        return updatedRows;
    }

    /**
     * Close account.
     *
     * @param accountNo the account no
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int CloseAccount(long accountNo)throws SQLException {
        int updatedRows=0;
        try {
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, getCloseAccountQueryString(),"cancel",accountNo);
        }finally{
        }
        return updatedRows;
    }

    /**
     * Deposit amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int DepositeAmount(long accountNo, long amount) throws SQLException{
        int updatedRows=0;
        try {
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, getDepositeQueryString(), amount,accountNo,"normal");

        }finally{
        }
        return updatedRows;
    }

    /**
     * Sets the updated by and updated time.
     *
     * @param accountNo the account no
     * @param userId the user id
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int setUpdatedByandUpdatedTime(long accountNo,long userId) throws SQLException{
        int updatedRows=0;
        try {
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, getUpdatedByAndTimeQueryString(), userId,new Timestamp(new java.util.Date().getTime()),accountNo,"normal");

        }finally{
        }
        return updatedRows;
    }

}
