package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.utility.DBHelper;



/**
 * The Class UserValidation-Data Access Object.
 */
public class UserValidationDAO {
	private Connection connection;

	private Logger logger = Logger.getLogger(UserValidationDAO.class);

	/**
	 * Instantiates a new user validation dao.
	 *
	 * @param connection the connection
	 */
	public UserValidationDAO(Connection connection) {
		this.connection = connection;
	}



	/** The Constant VALIDATE_QUERY_STRING. */
	private static final String VALIDATE_QUERY_STRING= "select * from registered_users where user_name=? and password=?";


	/** The Constant VALIDATE_USER_NAME_QUERY_STRING. */
	private static final String VALIDATE_USER_NAME_QUERY_STRING= "select * from registered_users where user_name=?";


	private String insertRowforNewUser() {

		return "insert into registered_users(user_name,password,is_admin) values(?,?,?)";
	}
	private String insertUserIdInCustomer() {

		return "update customer set user_id=? where customer_id=?" ;
	}


	/**
	 * Validate username and password at the time of login
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user details
	 * @throws SQLException the SQL exception
	 * @throws BankingException 
	 */
	public UserDTO validate(String username,String password) throws SQLException, BankingException{
		ResultSet rs = null;
		try {
			rs = DBHelper.getResultSetFromSQL(connection, VALIDATE_QUERY_STRING, username,password);
			if (rs != null && rs.next()) {
				boolean isAdmin;
				if(rs.getString("IS_ADMIN").compareTo("n")==0)
					isAdmin=false;
				else
					isAdmin=true;
				UserDTO userDetails = new UserDTO(rs.getLong("USER_ID"),username,isAdmin,rs.getTimestamp("LAST_LOGGED_ON"));
				logger.info("User found with details="+userDetails);
				return userDetails;
			}
			throw new BankingException("User Details is Empty");
		}finally{
			DBHelper.closeResultSet(rs);
		}
	}

	/**
	 * Validate user name at the time of registration
	 *
	 * @param username the username
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws BankingException 
	 */

	public void validateUserName(String username,String password) throws SQLException, BankingException{     
		ResultSet rs1 = null;
		ResultSet rs2=null;
		String isAdmin = "n";
		try {
			rs1 = DBHelper.getResultSetFromSQL(connection, VALIDATE_USER_NAME_QUERY_STRING, username);

			if (rs1 != null && rs1.next()) {
				throw new BankingException("UserName already exists...Enter different username");	
			}else{
				rs2=DBHelper.getResultSetFromSQL(connection, insertRowforNewUser(),username,password,isAdmin);
		      }
		}finally{
			DBHelper.closeResultSet(rs1);
			DBHelper.closeResultSet(rs2);
		}
	}

	public void insertInCustomerUserId(long customerId,String username) throws SQLException, BankingException { 
		ResultSet rs1 = null;
		ResultSet rs2=null;
		try{
			rs1 = DBHelper.getResultSetFromSQL(connection, VALIDATE_USER_NAME_QUERY_STRING, username);
			if (rs1 != null && rs1.next()){
				rs2=DBHelper.getResultSetFromSQL(connection, insertUserIdInCustomer(),rs1.getLong("USER_ID"),customerId);
			}
			else{
				throw new BankingException("Sorry!!!..Error in Entry process...");	
			}
		}finally{
			DBHelper.closeResultSet(rs1);
			DBHelper.closeResultSet(rs2);
		}
	}
}

