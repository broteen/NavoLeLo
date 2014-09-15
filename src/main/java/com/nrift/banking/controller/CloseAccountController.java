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

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.CloseAccountService;
import com.nrift.banking.utility.DatasourceConnectionManager;
import com.nrift.banking.utility.DatasourceManager;
import com.nrift.banking.utility.UserInstantiation;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class CloseAccountServlet.
 */
@WebServlet("/CloseAccountController")
public class CloseAccountController extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	static Logger logger = Logger.getLogger(CloseAccountController.class);

	/**
	 * Instantiates a new close account controller.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public CloseAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		long account = Long.parseLong(request.getParameter("accountNo"));
		String errorMsg = null;
		if (account == 0L) {
			errorMsg = "Please enter a valid account number";
		}
		if (errorMsg != null) {
			request.setAttribute("errorMsg",errorMsg);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/delete.jsp");
			rd.forward(request, response);
		} else {
			Connection con = null;
			try {
				con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute(
						"datasource"));
				CloseAccountService closeAccountMgr = new CloseAccountService();
				HttpSession session = request.getSession(false);
				logger.info("Transaction is Authorised");
				session.setAttribute("AccountClosed", account);
				closeAccountMgr.closeAccount(con,account);
				UserDTO user = (UserDTO) session.getAttribute("user");
				user.setCustomerDetails(UserInstantiation.getCustomerDetails(con, user.getUserId()));
				session.setAttribute("user", user);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/deleteAccConfirmation.jsp");
				rd.forward(request, response);
				/*
					RequestDispatcher rd = getServletContext()
							.getRequestDispatcher("/delete.jsp");
					PrintWriter out = response.getWriter();
					logger.error("Transaction is Not Authorised");
					out.println("<font color=red>Transaction Failed due to authorization failure</font>");
					rd.include(request, response);
				 */
			}catch(BankingException e) {
				DatasourceConnectionManager.rollbackConnection(con);
				request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page
				request.getRequestDispatcher("delete.jsp").forward(request,response);
			}
		}
	}
}
