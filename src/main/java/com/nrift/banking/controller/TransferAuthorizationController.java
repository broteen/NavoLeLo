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

import com.nrift.banking.utility.AccountDTO;
import com.nrift.banking.utility.TransferAmountDTO;
import com.nrift.banking.utility.TransferAuthorizationService;
import com.nrift.banking.utility.UserDetails;

/**
 * Servlet implementation class TransactionAuthorisationController
 */
@WebServlet("/TransactionAuthorisationController")
public class TransferAuthorizationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(TransferAuthorizationController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferAuthorizationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long senderAccount = Long.parseLong(request.getParameter("senderAccountNumber"));
		long receiverAccount = Long.parseLong(request.getParameter("receiverAccountNumber"));
		long amount = Long.parseLong(request.getParameter("amount"));
		String errorMsg = null;
		if (senderAccount==0L) {
			errorMsg = "Sender's Account Number can't be null or empty";
		}
		if (receiverAccount==0L) {
			errorMsg = "Receiver's Account Number can't be null or empty";
		}
		if(senderAccount==receiverAccount){
			errorMsg = "Sender's and Receiver's Account Number can't be same";
		}
		if(amount==0L){
			errorMsg = "Amount can't be null or empty or zero";
		}

		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/transferFund.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {

			Connection con = (Connection) getServletContext().getAttribute(
					"connection");
			TransferAuthorizationService trancAuthorise = new TransferAuthorizationService();
			
			try{
				TransferAmountDTO transferAmountDetails= trancAuthorise.validate(con,senderAccount,receiverAccount,amount);
				HttpSession session= request.getSession(false);
				if (transferAmountDetails!=null) {
					logger.info("Transaction is Authorised");
					session.setAttribute("transferAmountDetails", transferAmountDetails);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/transferUserConformation.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/transferFund.jsp");
					PrintWriter out = response.getWriter();
					logger.error("Transaction is Not Authorised");
					out.println("<font color=red>Transaction Failed At Authorization</font>");
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
	            request.getRequestDispatcher("transferFund.jsp").forward(request,response);
			}
		}

	}
	
}

