package com.nrift.banking.controller;


	import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

	import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.utility.DepositeManager;

	@WebServlet(name = "Deposite", urlPatterns = { "/deposite" })
	public class DepositeController extends HttpServlet {
		private static final long serialVersionUID = 1L;

		static Logger logger = Logger.getLogger(LoginController.class);

		protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			long accountNumber= Long.parseLong(request.getParameter("accountNumber"));
			long amount =Long.parseLong( request.getParameter("amount"));
			String errorMsg = null;
			if (accountNumber ==0L) {
				errorMsg = "Account Number can not be empty";
			}
			if (amount == 0L) {
				errorMsg = "Amount can not be  empty";
			}

			if (errorMsg != null) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher(
						"/deposite.jsp");
				PrintWriter out = response.getWriter();
				out.println("<font color=red>" + errorMsg + "</font>");
				rd.include(request, response);
			} else {

				Connection con = (Connection) getServletContext().getAttribute(
						"connection");
				DepositeManager deposite=new DepositeManager();
				
				try{
					
					long customerId  = deposite.validateAccountNumber(con,accountNumber);
					if (customerId != 0L) {
						logger.info("account found with given account number =" + customerId);	
					}
					
					else {
						RequestDispatcher rd = getServletContext()
								.getRequestDispatcher("/deposite.jsp");
						PrintWriter out = response.getWriter();
						logger.error("account not found=");
						out.println("<font color=red>Invalid account number</font>");
						rd.include(request, response);
					}
					
					if(amount<=0)
					{
						RequestDispatcher rd = getServletContext()
								.getRequestDispatcher("/deposite.jsp");
						PrintWriter out = response.getWriter();
						logger.error("Invalid Amount");
						out.println("<font color=red>Invalid amount</font>");
						rd.include(request, response);
					}
				}catch(ServletException e)
				{
					//To be Implemented later this is not the correct implmentation
					response.getWriter().print(e.getMessage()+"DepositeController");
				}
			}
		}

		
	}



