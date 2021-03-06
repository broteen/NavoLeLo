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

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.UserValidationService;
import com.nrift.banking.utility.DatasourceConnectionManager;
import com.nrift.banking.utility.DatasourceManager;

/**
 * The Class LoginController.
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMsg = null;
		if (username == null || username.equals("")) {
			errorMsg = "User Name can't be null or empty";
		}
		if (password == null || password.equals("")) {
			errorMsg = "Password can't be null or empty";
		}

		if (errorMsg != null) {
			request.setAttribute("errorMsg",errorMsg);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/login.jsp");
			rd.forward(request, response);
		} else {
			Connection con = null;
			try {
				con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute(
						"datasource"));
				UserValidationService userValidation = new UserValidationService();
				UserDTO user = userValidation.validate(con, username,password);
				logger.info("User found with details=" + user);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				//setting session expiry to 5mins
				session.setMaxInactiveInterval(5*60);
				if(!user.isAdmin())
					response.sendRedirect("index.jsp");
				else
					response.sendRedirect("index.jsp");
				/*
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher("/login.html");
                    PrintWriter out = response.getWriter();
                    logger.error("User not found with username=" + username);
                    out.println("<font color=red>No user found with given email id, please register first.</font>");
                    rd.include(request, response);
				 */
			}catch(BankingException e) {
				DatasourceConnectionManager.rollbackConnection(con);
				request.setAttribute("errorMsg",e.getMessage());  //There should be an error block on around the top of every jsp page
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
		}
	}

}
