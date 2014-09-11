package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.AdminDTO;
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

    /**
     * Gets the admin query string.
     *
     * @return the admin query string
     */
    
    private static final String ADMIN_QUERY_STRING= "select * from admin where user_id=?";
    /**
     * Gets the admin details.
     *
     * @param userId the user id
     * @return the admin details
     * @throws SQLException the SQL exception
     */
    public AdminDTO getAdminDetails(long userId) throws SQLException{
        AdminDTO admindeatils=null;
        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, ADMIN_QUERY_STRING, userId);
            if (rs != null && rs.next()) {
                admindeatils = new AdminDTO(rs.getLong("ADMIN_ID"),rs.getString("NAME"),rs.getString("EMAIL"));
                logger.info("Admin found with details="+admindeatils);
            }

        }finally{
            DBHelper.closeResultSet(rs);   
        }
        return admindeatils; 
    }

}
