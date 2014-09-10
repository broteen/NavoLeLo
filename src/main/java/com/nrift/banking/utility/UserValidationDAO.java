package com.nrift.banking.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

/**
 * The Class UserValidationDAO.
 */
public class UserValidationDAO {
    private Connection connection;

    private Logger logger = Logger.getLogger(UserValidationDAO.class);

    /**
     * Instantiates a new user validation dao.
     *
     * @param connection the connection
     */
    public UserValidationDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Validate the username and password at the time of login
     *
     * @param username the username
     * @param password the password
     * @return the user details
     * @throws ServletException the servlet exception
     */
    public UserDetails validate(String username,String password) throws ServletException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM REGISTERED_USERS WHERE USER_NAME=? and PASSWORD=?");

            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) 
            {
                boolean isAdmin;
                if(rs.getString("IS_ADMIN").compareTo("n")==0)
                {
                    isAdmin=false;
                }
                else
                {
                    isAdmin=true;
                }
                UserDetails validUser = new UserDetails(rs.getLong("USER_ID"),username,isAdmin,rs.getTimestamp("LAST_LOGGED_ON"));
                logger.info("User found with details="+validUser);
                return validUser;
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

    /**
     * Validate user name at the time of registration
     *
     * @param username the username
     * @return true, if successful
     * @throws ServletException the servlet exception
     */
    public boolean validateUserName(String username) throws ServletException
    {
        boolean user;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM REGISTERED_USERS WHERE USER_NAME=?");

            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs != null) 
            {
                logger.info("User name already exists"+username);
                user=true;	
            }
            else
            {
                user=false;
            }


            return user;
        }

        catch (SQLException e) {
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
