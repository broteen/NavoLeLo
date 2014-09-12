package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.nrift.banking.dao.AdminDAO;
import com.nrift.banking.dto.AdminDTO;
import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.exception.BankingException;

/**
 * The Class AdminService.
 */
public class AdminService {

    /**
     * Gets the admin details.
     *
     * @param connection the connection
     * @param userId the user id
     * @return the admin details
     * @throws BankingException 
     * @throws SQLException the SQL exception
     */
    public AdminDTO getAdminDetails(Connection connection, long userId) throws BankingException{
    	try{
        return new AdminDAO(connection).getAdminDetails(userId);
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
    }
    
    public List<CustomerDTO> getCustomerSearchDetails(Connection connection,String query,Object...objects) throws BankingException{
    	try{
		return new AdminDAO(connection).getCustomerSearchDetails(connection,query,objects);
    	}catch(SQLException e){
    		throw new BankingException(e);
    	}
	}

}
