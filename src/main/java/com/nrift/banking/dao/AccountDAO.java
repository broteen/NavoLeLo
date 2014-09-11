package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


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

    /** The Constant ALL_ACCOUNT_QUERY_STRING. */
    private static final String ALL_ACCOUNT_QUERY_STRING="select b.account_number,a.account_type,b.balance,b.updated_time from account_type a "
            + "inner join account b on a.account_type_id = b.account_type_id  where customer_id=?";


    /** The Constant ACCOUNT_QUERY_STRING. */
    private static final String ACCOUNT_QUERY_STRING = "SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
            + "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE B.ACCOUNT_NUMBER=?";



    /** The Constant CUSTOMER_QUERY_STRING. */
    private static final String CUSTOMER_QUERY_STRING="select customer_id,status from account where account_number=?";


    /** The Constant UPDATED_TIME_QUERY_STRING. */
    private static final String UPDATED_TIME_QUERY_STRING= "select updated_time from account where account_number=? and status=?";


    /** The Constant WITHDRAW_QUERY_STRING. */
    private static final String WITHDRAW_QUERY_STRING="update account set balance=balance-? where account_number=? and status=?";

    /** The Constant DEPOSITE_QUERY_STRING. */
    private static final String DEPOSITE_QUERY_STRING="update account set balance=balance+? where account_number=? and status=?";


    /** The Constant CLOSE_ACCOUNT_QUERY_STRING. */
    private static final String CLOSE_ACCOUNT_QUERY_STRING="update account set status=? where account_number=?";


    /** The Constant UPDATED_BY_AND_TIME_QUERY_STRING. */
    private static final String UPDATED_BY_AND_TIME_QUERY_STRING= "update account set updated_by=?,updated_time=? where account_number=? and status=?";


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
            rs = DBHelper.getResultSetFromSQL(connection, ALL_ACCOUNT_QUERY_STRING, customerId);
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


            rs = DBHelper.getResultSetFromSQL(connection, ACCOUNT_QUERY_STRING, accountNo);
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
            rs=DBHelper.getResultSetFromSQL(connection, CUSTOMER_QUERY_STRING, accountNumber);
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

            rs = DBHelper.getResultSetFromSQL(connection,UPDATED_TIME_QUERY_STRING, accountNo,"normal");
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
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, WITHDRAW_QUERY_STRING, amount,accountNo,"normal");

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
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, CLOSE_ACCOUNT_QUERY_STRING,"cancel",accountNo);
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
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, DEPOSITE_QUERY_STRING, amount,accountNo,"normal");

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
            updatedRows=DBHelper.getUpdateInfoFromSQL(connection, UPDATED_BY_AND_TIME_QUERY_STRING, userId,new Timestamp(new java.util.Date().getTime()),accountNo,"normal");

        }finally{
        }
        return updatedRows;
    }

}
