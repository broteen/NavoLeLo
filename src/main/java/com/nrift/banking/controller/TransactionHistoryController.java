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

import com.nrift.banking.utility.TransactionHistoryDetails;
import com.nrift.banking.utility.TransactionHistoryManager;

@WebServlet(name = "TransactionHistory", urlPatterns = { "/customer/account/accountDetails" })
public class TransactionHistoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginController.class);

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		long accountNo = (Long.parseLong(request.getParameter("accountNo")));
		
		String errorMsg = null;
		
		if (errorMsg != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/index.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>" + errorMsg + "</font>");
			rd.include(request, response);
		} else {

			Connection con = (Connection) getServletContext().getAttribute(
					"connection");
			TransactionHistoryManager transactionHistoryManager = new TransactionHistoryManager();
			
			try{
				TransactionHistoryDetails transactionHistoryDetails = (TransactionHistoryDetails) transactionHistoryManager.getTransactionHistoryDetails(con, accountNo);
				if (transactionHistoryDetails != null) {
					logger.info("Transaction history for account number=" + transactionHistoryDetails);
					HttpSession session = request.getSession();
					session.setAttribute("accountDetails", transactionHistoryDetails);
					System.out.print("hi");
					response.sendRedirect("accountDetails.jsp");
				} else {
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/index.jsp");
					PrintWriter out = response.getWriter();
					logger.error("Transaction not found for account number=" + accountNo);
					//out.println("<font color=red>No user found with given email id, please register first.</font>");
					rd.include(request, response);
				}
			}catch(ServletException e)
			{
				//To be Implemented later this is not the correct implmentation
				response.getWriter().print(e.getMessage()+"transactionHistoryController");
			}
		}
	}

}
