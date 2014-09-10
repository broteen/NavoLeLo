package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

/**
 * The Class AccountDAO.
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
     * Gets the all account details.
     *
     * @param customerId the customer id
     * @return the all account details
     * @throws ServletException the servlet exception
     */
    public List<AccountDTO> getAllAccountDetails(long customerId) throws ServletException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = connection.prepareStatement("SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
                    + "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE CUSTOMER_ID=? AND B.STATUS=?");

            ps.setLong(1,customerId);
            ps.setString(2, "normal");
            rs = ps.executeQuery();

            if(rs !=null)
            {


                List<AccountDTO> list= new ArrayList<AccountDTO>();
                while (rs.next()) 
                {
                    AccountDTO validAccount = new AccountDTO(rs.getLong("ACCOUNT_NUMBER"),rs.getString("ACCOUNT_TYPE"),rs.getLong("BALANCE"),rs.getTimestamp("UPDATED_TIME"));
                    logger.info("Customer found with details="+validAccount);
                    list.add(validAccount);
                }

                return (list);

            }

            return null;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }

        finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }

    }

    /**
     * Gets the account details.
     *
     * @param accountNo the account no
     * @return the account details
     * @throws ServletException the servlet exception
     */
    public AccountDTO getAccountDetails(long accountNo ) throws ServletException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = connection.prepareStatement("SELECT B.ACCOUNT_NUMBER,A.ACCOUNT_TYPE,B.BALANCE,B.UPDATED_TIME FROM ACCOUNT_TYPE A "
                    + "INNER JOIN ACCOUNT B ON A.ACCOUNT_TYPE_ID = B.ACCOUNT_TYPE_ID  WHERE B.ACCOUNT_NUMBER=? AND B.STATUS=?");

            ps.setLong(1,accountNo);
            ps.setString(2, "normal");
            rs = ps.executeQuery();
            if(rs !=null && rs.next())
            {
                AccountDTO validAccount = new AccountDTO(rs.getLong("ACCOUNT_NUMBER"),rs.getString("ACCOUNT_TYPE"),rs.getLong("BALANCE"),rs.getTimestamp("UPDATED_TIME"));
                logger.info("Customer found with details="+validAccount);
                return(validAccount);
            }
            return null;
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }

        finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }
    }

    /**
     * Gets the customer id.
     *
     * @param accountNumber the account number
     * @return the customer id
     * @throws ServletException the servlet exception
     */
    public long getCustomerId(long accountNumber) throws ServletException
    {
        long customerID=0L; 
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = connection.prepareStatement("SELECT CUSTOMER_ID,STATUS FROM ACCOUNT WHERE ACCOUNT_NUMBER=? AND STATUS=?");

            ps.setLong(1,accountNumber);
            ps.setString(2, "normal");
            rs = ps.executeQuery();
            if(rs !=null)
            {
                customerID=rs.getInt("CUSTOMER_ID");
                String status=rs.getString("STATUS");
                if(status.equals("cancel")){
                    customerID=0L;
                }
            }
            return customerID;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }
        finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }

    }

    /**
     * Gets the updated time.
     *
     * @param accountNo the account no
     * @return the updated time
     * @throws ServletException the servlet exception
     */
    public Timestamp getUpdatedTime(long accountNo) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = connection.prepareStatement("SELECT UPDATED_TIME FROM ACCOUNT WHERE ACCOUNT_NUMBER=? AND STATUS=?");

            ps.setLong(1,accountNo);
            ps.setString(2, "normal");
            rs = ps.executeQuery();
            if(rs !=null && rs.next()){
                return(rs.getTimestamp("UPDATED_TIME"));
            }
            return null;
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }
        finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }
    }

    /**
     * Withdraw amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws ServletException the servlet exception
     */
    public int WithdrawAmount(long accountNo, long amount) throws ServletException {
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("UPDATE ACCOUNT SET BALANCE=BALANCE-? WHERE ACCOUNT_NUMBER=? AND STATUS=?");

            ps.setLong(1,amount);
            ps.setLong(2,accountNo);
            ps.setString(3, "normal");
            return ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in extracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }
        finally{
            try{
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }

    }

    /**
     * Close account.
     *
     * @param accountNo the account no
     * @return the int
     * @throws ServletException the servlet exception
     */
    public int CloseAccount(long accountNo) throws ServletException {
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("UPDATE ACCOUNT SET STATUS=? WHERE ACCOUNT_NUMBER=?");

            /*ps.setLong(1,amount);
			ps.setLong(2,accountNo);*/
            ps.setString(1, "cancel");
            ps.setLong(2, accountNo);
            return ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in extracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }
        finally{
            try{
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }

    }

    /**
     * Deposit amount.
     *
     * @param accountNo the account no
     * @param amount the amount
     * @return the int
     * @throws ServletException the servlet exception
     */
    public int DepositeAmount(long accountNo, long amount) throws ServletException {
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("UPDATE ACCOUNT SET BALANCE=BALANCE+? WHERE ACCOUNT_NUMBER=? AND STATUS=?");

            ps.setLong(1,amount);
            ps.setLong(2,accountNo);
            ps.setString(3, "normal");
            return ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }
        finally{
            try{
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }
    }

    /**
     * Sets the updated by and updated time.
     *
     * @param accountNo the account no
     * @param userId the user id
     * @return the int
     * @throws ServletException the servlet exception
     */
    public int setUpdatedByandUpdatedTime(long accountNo,long userId) throws ServletException {
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("UPDATE ACCOUNT SET UPDATED_BY=?,UPDATED_TIME=? WHERE ACCOUNT_NUMBER=? AND STATUS=?");

            ps.setLong(1,userId);
            ps.setTimestamp(2,new Timestamp(new java.util.Date().getTime()));
            ps.setLong(3,accountNo);
            ps.setString(4, "normal");
            return ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet");
            System.out.println(e);
            throw new ServletException("DB Connection problem.");
        }
        finally{
            try{
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }
    }

}
