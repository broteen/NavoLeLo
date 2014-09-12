package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.AccountDTO;
import com.nrift.banking.dto.AdminDTO;
import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.AccountService;
import com.nrift.banking.utility.DBHelper;

/**
 * The Class Admin-Data Access Object.
 */
public class AdminDAO {

    private Connection connection;


    private Logger logger = Logger.getLogger(AdminDAO.class);

    /**
     * Instantiates a new admin dao.
     *
     * @param connection the connection
     */
    public AdminDAO(Connection connection) {
        this.connection = connection;

    }



    /** The Constant ADMIN_QUERY_STRING. */
    private static final String ADMIN_QUERY_STRING= "select * from admin where user_id=?";

    /**
     * Gets the admin details.
     *
     * @param userId the user id
     * @return the admin details
     * @throws SQLException the SQL exception
     * @throws BankingException 
     */
    public AdminDTO getAdminDetails(long userId) throws SQLException, BankingException{
        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, ADMIN_QUERY_STRING, userId);
            if (rs != null && rs.next()) {
            	AdminDTO adminDetails = new AdminDTO(rs.getLong("ADMIN_ID"),rs.getString("NAME"),rs.getString("EMAIL"));
                logger.info("Admin found with details="+adminDetails);
                return adminDetails;
        	}
        	throw new BankingException("Admin Details is Empty");
        }finally{
        	DBHelper.closeResultSet(rs);
        }
    }
    
    /**
     * Gets the customer search details.
     *
     * @param connection the connection
     * @param query the query
     * @param objects the objects
     * @return the customer search details
     * @throws SQLException the SQL exception
     */
    public List<CustomerDTO> getCustomerSearchDetails(Connection connection,String query,Object... objects) throws SQLException,BankingException{
		ResultSet rs = null;
		try {
			rs = DBHelper.getResultSetFromSQL(connection, query, objects);
			if (rs != null) {
				List<CustomerDTO> customerList= new ArrayList<CustomerDTO>();
				AccountService accountService= new AccountService();
				while(rs.next()){
				CustomerDTO customer = new CustomerDTO(rs.getLong("CUSTOMER_ID"),rs.getString("NAME"),rs.getLong("CONTACT_NUMBER"),rs.getString("PAN_NUMBER"),
						rs.getString("EMAIL"),rs.getString("ADDRESS"));
				AccountDTO account= accountService.getAccountDetails(connection, rs.getLong("ACCOUNT_NUMBER"));
				List<AccountDTO> accountList= new ArrayList<AccountDTO>();
				accountList.add(account);
				customer.setAccountList(accountList);
				logger.info("Customer found with details="+customer);
				customerList.add(customer);
				}
				if(!customerList.isEmpty())
					return customerList;
			}
			throw new BankingException("Search Result is Empty");
		}finally{
			DBHelper.closeResultSet(rs);   
        }
	}

}
