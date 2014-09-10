package com.nrift.banking.utility;

import java.sql.Connection;

import javax.servlet.ServletException;

public class LogInfoService {


    public boolean validateUsername(Connection connection, String username) throws ServletException{
        return(new UserValidationService().checkUserName(connection, username));

    }

}