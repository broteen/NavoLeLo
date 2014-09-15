package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.dao.UserValidationDAO;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.utility.UserInstantiation;

/**
 * The Class UserValidationService.
 */
public class UserValidationService {

    /**
     * Validate the user
     *
     * @param connection the connection
     * @param username the username
     * @param password the password
     * @return the user details
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public UserDTO validate(Connection connection, String username,String password) throws BankingException {
    	try{
        UserDTO user= new UserValidationDAO(connection).validate(username, password);

        if(user!=null){
            if(user.isAdmin()== true)
                user.setAdminDetails(UserInstantiation.getAdminDetails(connection, user.getUserId()));
            else
                user.setCustomerDetails(UserInstantiation.getCustomerDetails(connection, user.getUserId()));
        }
        return user;
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}

    }

    /**
     * Check user name.
     *
     * @param connection the connection
     * @param username the username
     * @return true, if successful
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public void checkUserName(Connection connection, String username,String password) throws BankingException
	{
    	try{
    		new UserValidationDAO(connection).validateUserName(username, password);
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
		
	}
    
    public void insertUserId(Connection connection, long customerId, String username) throws BankingException { 
    	try{
    		new UserValidationDAO(connection).insertInCustomerUserId(customerId,username);
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
		
	}


}
