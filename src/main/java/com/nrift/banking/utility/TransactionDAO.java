
package com.nrift.banking.utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public class TransactionDAO {
    private Connection connection;
    private Logger logger = Logger.getLogger(UserValidationDAO.class);
    public TransactionDAO(Connection connection) {
        this.connection=connection;
    }
    public int insertRowForTransferAmount(long senderAccountNo,long receiverAccountNo, long amount) throws ServletException {

        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("INSERT INTO TRANSACTION(TRANSACTION_TIME,CR_ACC_NUM,DR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?,?)");

            ps.setTimestamp(1,new Timestamp(new java.util.Date().getTime()));
            ps.setLong(2,receiverAccountNo);
            ps.setLong(3,senderAccountNo);
            ps.setLong(4,amount);
            ps.setLong(5,TransactionRefGenerator.getInstance().getCounter());
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
    public int insertRowForWithdrawAmount(long accountNo, long amount) throws ServletException {
        // TODO Auto-generated method stub
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("INSERT INTO TRANSACTION(TRANSACTION_TIME,DR_ACC_NUM,AMOUNT,TRANSACTION_REF) VALUES(?,?,?,?)");

            ps.setTimestamp(1,new Timestamp(new java.util.Date().getTime()));
            ps.setLong(2,accountNo);
            ps.setLong(3,amount);
            ps.setLong(4,TransactionRefGenerator.getInstance().getCounter());
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
}

