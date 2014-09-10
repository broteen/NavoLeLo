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

import com.nrift.banking.utility.CustomerDTO;
import com.nrift.banking.utility.RegistrationService;

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
                    "/register.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMsg + "</font>");
            rd.include(request, response);
        } else {

            Connection con = (Connection) getServletContext().getAttribute(
                    "connection");
            RegistrationService registerValidation = new RegistrationService();

            try{

                long customerId = registerValidation.validateAccountNumber(con,accountNumber);
                if (customerId != 0L) {
                    logger.info("account found with given account number =" + customerId);	
                }
                else {
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher("/register.html");
                    PrintWriter out = response.getWriter();
                    logger.error("account not found=" + name);
                    out.println("<font color=red>Account does not exists with the given account number</font>");
                    rd.include(request, response);
                }
                CustomerDTO customer=registerValidation.validateCustomerDetails(con,customerId);
                if(customer!=null){

                    if(customer.getName().equalsIgnoreCase(name) && customer.getContactNumber()==contactNumber){
                        logger.info("customer found with details=" + customerId);


                        if(registerValidation.checkuserID(con,customerId)){
                            logger.error("new user.....");
                            response.sendRedirect("loginfo.html");
                        }
                        else{
                            RequestDispatcher rd = getServletContext()
                                    .getRequestDispatcher("/register.html");
                            PrintWriter out = response.getWriter();
                            logger.error("customer is already registered");
                            out.println("<font color=red>customer is already register</font>");
                            rd.include(request, response);
                        }
                    }
                    else{
                        RequestDispatcher rd = getServletContext()
                                .getRequestDispatcher("/register.html");
                        PrintWriter out = response.getWriter();
                        logger.error("customer not found=" + name);
                        out.println("<font color=red>No customer found with given customer details, please fill correct details</font>");
                        rd.include(request, response);
                    }

                } 
                else {
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher("/register.html");
                    PrintWriter out = response.getWriter();
                    logger.error("customer not found=" + name);
                    out.println("<font color=red>No customer found with given account number, please fill correct details</font>");
                    rd.include(request, response);
                }


            }	
            catch(SQLException |ServletException| IOException e) {
                try {
                    con.rollback();
                } catch(SQLException e1) {
                    logger.error("Rollback error");
                }
                logger.error(" Exception Thrown");
                //There should be an error block on around the top of every jsp page
                request.setAttribute("errorMsg", "Exception Occured!");
                request.getRequestDispatcher("register.html").forward(request,response);
            }
        }
    }

}
