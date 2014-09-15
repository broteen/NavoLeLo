package com.nrift.banking.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.LogInfoService;
import com.nrift.banking.service.UserValidationService;

/**
 * The Class LogInfoController.
 */
@WebServlet(name = "LogInfo", urlPatterns = { "/LogInfoController" })
public class LogInfoController  extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginController.class);

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
    	long customerID =Long.parseLong(request.getParameter("customerId"));
        String errorMsg = null;
        if (username == null || username.equals("")) {
            errorMsg = "User Name can't be null or empty";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty";
        }

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/loginfo.jsp");
            rd.include(request, response);
        } else {

        	Connection con = (Connection) getServletContext().getAttribute(
					"connection");
			LogInfoService logInfo=new LogInfoService();
			
			try{
				logger.info("Username registeration");
				logInfo.validateUsername(con, username,password);
				logger.info("CustomerId is..."+customerID);
				logInfo.insertUserIdInCustomer(con, customerID,username);
				response.sendRedirect("loginsuccess.jsp");
				
			}catch(BankingException e) {
                try {
                    con.rollback();
                    logger.error(" Exception Thrown="+ e.getMessage());
                } catch(SQLException e1) {
                    logger.error("Rollback error" +e1.getMessage());
                }
                request.setAttribute("errorMsg",e.getMessage()); 
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }
        }
    }

}
