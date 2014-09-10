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

import com.nrift.banking.utility.WithdrawAmountDTO;
import com.nrift.banking.utility.WithdrawAuthorizationService;

/**
 * Servlet implementation class WithdrawalAuthorizationController
 */
@WebServlet("/WithdrawalAuthorizationController")
public class WithdrawalAuthorizationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger
			.getLogger(WithdrawalAuthorizationController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WithdrawalAuthorizationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long account = Long.parseLong(request.getParameter("AccountNumber"));
		long amount = Long.parseLong(request.getParameter("Amount"));
		String errorMsg = null;
		if (account==0L) {
			errorMsg = "Please enter a valid account number";
		}
		if(amount==0L){
			errorMsg = "Please enter a valid amount";
		}

		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/withdrawAmt.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {

			Connection con = (Connection) getServletContext().getAttribute(
					"connection");
			WithdrawAuthorizationService withdrawAuthorise = new WithdrawAuthorizationService();
			
			try{
				WithdrawAmountDTO withdrawAmountDetails= withdrawAuthorise.validate(con,account,amount);
				HttpSession session= request.getSession(false);
				if (withdrawAmountDetails!=null) {
					logger.info("Transaction is Authorised");
					session.setAttribute("withdrawAmountDetails", withdrawAmountDetails);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/withdrawUserConfirmation.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/withdrawAmt.jsp");
					PrintWriter out = response.getWriter();
					logger.error("Transaction is Not Authorised");
					out.println("<font color=red>Transaction Failed due to authorization failure</font>");
					rd.include(request, response);
				}
			}catch(SQLException |ServletException| IOException e) {
				try {
                    con.rollback();
                } catch(SQLException e1) {
                    logger.error("Rollback error");
                }
				logger.error(" Exception Thrown");
				//There should be an error block on around the top of every jsp page
				request.setAttribute("errorMsg", "Exception Occured!");
	            request.getRequestDispatcher("withdrawAmt.jsp").forward(request,response);
			}
		}
	}

}
