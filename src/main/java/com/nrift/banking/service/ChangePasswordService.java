package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.nrift.banking.dao.RegisterUserDAO;
import com.nrift.banking.exception.BankingException;

public class ChangePasswordService {
	
	
	/**
	 * Gets the old password.
	 *
	 * @param con the con
	 * @param userID the user id
	 * @throws BankingException the banking exception
	 */
	public String getOldPassword(Connection con, long userID) throws  BankingException {
		String oldPassword;
    	try{
    		oldPassword = new RegisterUserDAO(con).getPassword(userID);
		}catch(SQLException e){
			throw new BankingException(e);
    	}
		return oldPassword;
		
	}

	/**
	 * Update password.
	 *
	 * @param con the con
	 * @param userId the user id
	 * @param newPswd the new pswd
	 * @throws BankingException the banking exception
	 */
	public void updatePassword(Connection con, long userId, String newPswd) throws  BankingException  {
		
		// TODO Auto-generated method stub
		try{
			new RegisterUserDAO(con).changePassword(userId,newPswd);
			 con.commit();
		}catch(SQLException e){
			throw new BankingException(e);
    	}
		
	}

	

}
