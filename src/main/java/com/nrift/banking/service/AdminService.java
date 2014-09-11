package com.nrift.banking.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.nrift.banking.dao.AdminDAO;
import com.nrift.banking.dto.AdminDTO;
import com.nrift.banking.dto.CustomerDTO;;

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
     * @throws SQLException the SQL exception
     */
    public AdminDTO getAdminDetails(Connection connection, long userId) throws SQLException{

        return (new AdminDAO(connection).getAdminDetails(userId));

    }
    
    public List<CustomerDTO> getCustomerSearchDetails(Connection connection,String query,Object...objects) throws SQLException {
		return new AdminDAO(connection).getCustomerSearchDetails(connection,query,objects);
	}

}
