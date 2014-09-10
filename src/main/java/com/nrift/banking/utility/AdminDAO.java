package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class AdminDAO {
	
	private Connection connection;
	
	
	private Logger logger = Logger.getLogger(AdminDAO.class);

	public AdminDAO(Connection connection) {
		this.connection = connection;
		
	}
	
	private String getAdminQueryString() {
        return "SELECT * FROM ADMIN WHERE USER_ID=?";
    }
	
	public AdminDTO getAdminDetails(long userId) throws SQLException{
		AdminDTO admindeatils=null;
		ResultSet rs = null;
		try {
			rs = DBUtils.getResultSetFromSQL(connection, getAdminQueryString(), userId);
			if (rs != null && rs.next()) {
				admindeatils = new AdminDTO(rs.getLong("ADMIN_ID"),rs.getString("NAME"),rs.getString("EMAIL"));
				logger.info("Admin found with details="+admindeatils);
			}
			
		}finally{
			DBUtils.closeResultSet(rs);   
        }
		return admindeatils; 
	}

}
