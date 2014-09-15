package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.nrift.banking.exception.BankingException;
import com.nrift.banking.exception.OptimisticLockException;



/**
 * The Class LogInfoService.
 */
public class LogInfoService {


    /**
     * Validate username.
     *
     * @param connection the connection
     * @param username the username
     * @return true, if successful
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
	public void validateUsername(Connection connection, String username,String password) throws  BankingException{ 
		
			new UserValidationService().checkUserName(connection, username,password);
		}
		


	
	public void insertUserIdInCustomer(Connection connection,long customerId, String username) throws  BankingException{
		new UserValidationService().insertUserId(connection,customerId,username);
		
		}
	}

