package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

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
     * @throws ServletException the servlet exception
     */
    public AdminDTO getAdminDetails(Connection connection, long userId) throws ServletException{

        return (new AdminDAO(connection).getAdminDetails(userId));

    }

}
