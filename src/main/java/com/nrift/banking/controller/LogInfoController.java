package com.nrift.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.utility.LogInfoManager;
import com.nrift.banking.utility.UserDetails;
import com.nrift.banking.utility.UserValidationManager;

@WebServlet(name = "LogInfo", urlPatterns = { "/loginfo" })
public class LogInfoController  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginController.class);

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
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/loginfo.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {

			Connection con = (Connection) getServletContext().getAttribute(
					"connection");
			LogInfoManager logInfo=new LogInfoManager();
			
			try{
				boolean user=logInfo.validateUsername(con, username);
				
				if (user==false) {
					logger.info("Username registered");
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					//System.out.println(user.getCustomerDetails().getName());
					System.out.print("hi");
					response.sendRedirect("loginsuccess.html");
				} else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/login.html");
					PrintWriter out = response.getWriter();
					logger.error("Username already exists in database");
					out.println("<font color=red>Sorry! username already exists in the database</font>");
					rd.include(request, response);
				}
			}catch(ServletException e)
			{
				//To be Implemented later this is not the correct implmentation
				response.getWriter().print(e.getMessage()+"logInfoController");
			}
		}
	}

}
