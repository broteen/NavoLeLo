package com.nrift.banking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.utility.DBHelper;

/**
 * The Class UserValidation-Data Access Object.
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



    /** The Constant VALIDATE_QUERY_STRING. */
    private static final String VALIDATE_QUERY_STRING= "select * from registered_users where user_name=? and password=?";


    /** The Constant VALIDATE_USER_NAME_QUERY_STRING. */
    private static final String VALIDATE_USER_NAME_QUERY_STRING= "select * from registered_users where user_name=?";

    /**
     * Validate username and password at the time of login
     *
     * @param username the username
     * @param password the password
     * @return the user details
     * @throws SQLException the SQL exception
     */
    public UserDTO validate(String username,String password) throws SQLException{
        UserDTO userDetails=null;
        ResultSet rs = null;
        try {
            rs = DBHelper.getResultSetFromSQL(connection, VALIDATE_QUERY_STRING, username,password);
            if (rs != null && rs.next()) {
                boolean isAdmin;
                if(rs.getString("IS_ADMIN").compareTo("n")==0)
                    isAdmin=false;
                else
                    isAdmin=true;
                userDetails = new UserDTO(rs.getLong("USER_ID"),username,isAdmin,rs.getTimestamp("LAST_LOGGED_ON"));
                logger.info("User found with details="+userDetails);

            }
        }finally{
            DBHelper.closeResultSet(rs);
        }
        return userDetails;
    }

    /**
     * Validate user name at the time of registration
     *
     * @param username the username
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean validateUserName(String username) throws SQLException{
        boolean user=false;
        ResultSet rs = null;

        try {
            rs = DBHelper.getResultSetFromSQL(connection, VALIDATE_USER_NAME_QUERY_STRING, username);

            if (rs != null && rs.next()) {
                logger.info("User name already exists"+username);
                user=true;	
            }else
                user=false;
        }finally{
            DBHelper.closeResultSet(rs);
        }
        return user;  
    }
}

