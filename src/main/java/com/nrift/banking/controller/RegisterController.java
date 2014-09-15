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
import com.nrift.banking.dto.CustomerDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.RegistrationService;
import com.nrift.banking.utility.DatasourceConnectionManager;

/**
 * The Class RegisterController.
 */
@WebServlet(name = "Register", urlPatterns = { "/RegisterController" })
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterController.class);


    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	long accountNumber=0;
    	long contactNumber=0;
        String name = request.getParameter("name");
       
		if(request.getParameter("account_number")!="")
        accountNumber =Long.parseLong( request.getParameter("accountNumber"));
      
		if(request.getParameter("contact_number")!="")
        contactNumber = Long.parseLong(request.getParameter("contactNumber"));
        //String pan_number = request.getParameter("pan_number");
        String errorMsg = null;
        if (name == null ||name.equals("")) {
            errorMsg = "Name can't be null or empty";
        }
        if (accountNumber == 0) {
            errorMsg = "Account Number can't be null or empty";
        }
        if (contactNumber==0) {
            errorMsg = "Contact Number can't be null or empty";
        }
         if(name == null && accountNumber == 0 && contactNumber==0){
        	 errorMsg = " Fields can't be null or empty";
         }
        if (errorMsg != null) {
        	request.setAttribute("errorMsg",errorMsg);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/register.jsp");
            rd.forward(request, response);
        } else {
        	Connection con = null;
			try{
				con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute("datasource"));
				RegistrationService registerValidation = new RegistrationService();
				long customerId = registerValidation.validateAccountNumber(con,accountNumber);
				CustomerDTO customer=registerValidation.validateCustomerDetails(con,customerId);
				
					if(customer.getName().equalsIgnoreCase(name) && customer.getContactNumber()==contactNumber){
							logger.info("customer found with details=" + customerId);
						registerValidation.checkUserId(con,customerId);
						logger.error("new user.....");
						request.setAttribute("customerId", customerId);
						RequestDispatcher rd =getServletContext().getRequestDispatcher("/loginfo.jsp");
						rd.forward(request, response);
						}	
				else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/register.jsp");
					PrintWriter out = response.getWriter();
					logger.error("customer not found=" + name);
					out.println("<font color=red>No customer found with given Details, please fill correct details</font>");
					rd.include(request, response);
				}
						
			}catch(BankingException e) {
				DatasourceConnectionManager.rollbackConnection(con);
				request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }
        }
    }

}
