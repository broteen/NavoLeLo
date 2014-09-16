package com.nrift.banking.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nrift.banking.exception.BankingException;
import com.nrift.banking.utility.DBHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterUserDAO.
 */
public class RegisterUserDAO {
	
	/** The connection. */
	private Connection connection;

    /** The logger. */
    private Logger logger = Logger.getLogger(AccountDAO.class);

    
    /**
     * Instantiates a new register user dao.
     *
     * @param connection the connection
     */
    public RegisterUserDAO(Connection connection) {
        this.connection = connection;
    }
    
    /** The Constant CHECK_PASSWORD_QUERY_STRING. */
    private static final String CHECK_PASSWORD_QUERY_STRING="select password from registered_users where user_id=?";
    
    /** The Constant CHANGE_PASSWORD_QUERY_STRING. */
    private static final String CHANGE_PASSWORD_QUERY_STRING="update registered_users set password=? where user_id=?";
    
    /**
     * Gets the password.
     *
     * @param userId the user id
     * @return the password
     * @throws SQLException the SQL exception
     * @throws BankingException the banking exception
     */
    public String getPassword(long userId) throws SQLException, BankingException{     
        ResultSet rs = null;
        try {
            rs=DBHelper.getResultSetFromSQL(connection, CHECK_PASSWORD_QUERY_STRING, userId);
            if(rs !=null && rs.next()){
            	String password=rs.getString("password");
            	return password;
            }
            throw new BankingException("Password is Empty");
        }finally{
            DBHelper.closeResultSet(rs);
        }
    }
    
    /**
     * Change password.
     *
     * @param userId the user id
     * @param newPswd the new pswd
     * @throws SQLException the SQL exception
     * @throws BankingException the banking exception
     */
    public void changePassword(long userId,String newPswd)throws SQLException,BankingException{
        try {
            int updatedRows=DBHelper.getUpdateInfoFromSQL(connection, CHANGE_PASSWORD_QUERY_STRING,newPswd,userId);
            
            if(updatedRows==0)
            	throw new BankingException("Error in Withdrawing");
        }finally{
        }
    }

	
}
