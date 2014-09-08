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

import com.nrift.banking.utility.CustomerDetails;
import com.nrift.banking.utility.RegistrationManager;

@WebServlet(name = "Register", urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(RegisterController.class);
	
	
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		long accountNumber =Long.parseLong( request.getParameter("account_number"));
		long contactNumber = Long.parseLong(request.getParameter("contact_number"));
		//String pan_number = request.getParameter("pan_number");
		String errorMsg = null;
		if (name == null ||name.equals("")) {
			errorMsg = "Name can't be null or empty";
		}
		if (accountNumber == 0L) {
			errorMsg = "Account Number can't be null or empty";
		}
		if (contactNumber==0L) {
			errorMsg = "Contact Number can't be null or empty";
		}

		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/register.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {

			Connection con = (Connection) getServletContext().getAttribute(
					"connection");
			RegistrationManager registerValidation = new RegistrationManager();
			
			try{
				
				long customerId = registerValidation.validateAccountNumber(con,accountNumber);
				if (customerId != 0L) {
					logger.info("account found with given account number =" + customerId);	
				}
				
				else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/register.jsp");
					PrintWriter out = response.getWriter();
					logger.error("account not found=" + name);
					out.println("<font color=red>Account does not exists with the given account number</font>");
					rd.include(request, response);
				}
				CustomerDetails customer=registerValidation.validateCustomerDetails(con,customerId);
					if(customer!=null)
					{
						
						if(customer.getName()==name && customer.getContactNumber()==contactNumber)
						{		logger.info("customer found with details=" + customerId);
						
					
					if(registerValidation.checkuserID(con,customerId))
					{
						logger.error("new user.....");
					response.sendRedirect("loginfo.jsp");
						}
					else
					{
						RequestDispatcher rd = getServletContext()
								.getRequestDispatcher("/register.jsp");
						PrintWriter out = response.getWriter();
						logger.error("customer is already registered");
						out.println("<font color=red>customer is already register</font>");
						rd.include(request, response);
					}
						}
						else{
							RequestDispatcher rd = getServletContext()
									.getRequestDispatcher("/register.jsp");
							PrintWriter out = response.getWriter();
							logger.error("customer not found=" + name);
							out.println("<font color=red>No customer found with given customer details, please fill correct details</font>");
							rd.include(request, response);
						}
						
				} 
				else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/register.jsp");
					PrintWriter out = response.getWriter();
					logger.error("customer not found=" + name);
					out.println("<font color=red>No customer found with given account number, please fill correct details</font>");
					rd.include(request, response);
				}
				
					
			}	
			catch(ServletException e)
			{
				//To be Implemented later this is not the correct implmentation
				response.getWriter().print(e.getMessage()+"RegisterController");
			}
		}
	}

}
