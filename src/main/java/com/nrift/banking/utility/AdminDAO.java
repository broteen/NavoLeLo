package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

/**
 * The Class AdminDAO.
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
     * Gets the admin details.
     *
     * @param userId the user id
     * @return the admin details
     * @throws ServletException the servlet exception
     */
    public AdminDTO getAdminDetails(long userId) throws ServletException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM ADMIN WHERE USER_ID=?");

            ps.setLong(1, userId);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) 
            {
                AdminDTO validAdmin = new AdminDTO(rs.getLong("ADMIN_ID"),rs.getString("NAME"),rs.getString("EMAIL"));
                logger.info("Admin found with details="+validAdmin);
                return validAdmin;
            }

            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException in exracting data from the ResultSet");
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
            }

        }

    }

}
