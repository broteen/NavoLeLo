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

import com.nrift.banking.utility.UserDetails;
import com.nrift.banking.utility.UserValidationService;

/**
 * The Class SearchAccountController.
 */
@WebServlet(name = "SearchAccount", urlPatterns = { "/admin/searchAccount" })
public class SearchAccountController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginController.class);

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        long customerId = (Long.parseLong(request.getParameter("customerId")));
        long accountNo = (Long.parseLong(request.getParameter("accountNo")));
        long panNo = (Long.parseLong(request.getParameter("panNo")));
        String errorMsg = null;
        if (customerName == null || customerName.equals("")) {
            errorMsg = "Customer Name can't be null or empty";

            if (errorMsg != null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher(
                        "/admin/search");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>" + errorMsg + "</font>");
                rd.include(request, response);
            } /*else {

			Connection con = (Connection) getServletContext().getAttribute(
					"connection");
			UserValidationManager userValidation = new UserValidationManager();

			try{
				UserDetails user = userValidation.validate(con, username,password);
				if (user != null) {
					logger.info("User found with details=" + user);
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					System.out.print("hi");
					response.sendRedirect("index.jsp");
				} else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/login.html");
					PrintWriter out = response.getWriter();
					logger.error("User not found with username=" + username);
					out.println("<font color=red>No user found with given email id, please register first.</font>");
					rd.include(request, response);
				}
			}catch(ServletException e)
			{
				//To be Implemented later this is not the correct implmentation
				response.getWriter().print(e.getMessage()+"loginController");
			}*/
        }
    }

}

