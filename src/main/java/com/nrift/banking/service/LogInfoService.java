package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;



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
     */
	public boolean validateUsername(Connection connection, String username,String password) throws SQLException{ //Couldn't understand this method kindly change it.
		if(!(new UserValidationService().checkUserName(connection, username,password))){
			 connection.commit();
             connection.setAutoCommit(true);
             return true;
		}else{
			connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
		}


	
	public boolean insertUserIdInCustomer(Connection connection,long customerId, String username) throws SQLException{ //Couldn't understand this method kindly change it.
		if(new UserValidationService().insertUserId(connection,customerId,username)){
			 connection.commit();
             connection.setAutoCommit(true);
             return true;
		}else{
			connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
		}
	}

