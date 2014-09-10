package com.nrift.banking.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.utility.WithdrawAmountDTO;
import com.nrift.banking.utility.withdrawAmountManager;

/**
 * Servlet implementation class withdrawAmountController
 */
@WebServlet("/WithdrawAmountController")
public class WithdrawAmountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger
			.getLogger(WithdrawalAuthorizationController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WithdrawAmountController() {
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

		Connection con = (Connection) getServletContext().getAttribute(
				"connection");
		withdrawAmountManager withdrawAmount = new withdrawAmountManager();
		HttpSession session = request.getSession(false);
		WithdrawAmountDTO withdrawAmountDetails = (WithdrawAmountDTO) session
				.getAttribute("WithdrawAmountDetails");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/withdrawSystemConformation.jsp");

		try {
			if (withdrawAmount
					.IsWithdrawSuccessfull(con, withdrawAmountDetails)) {
				logger.info("Withdraw Successfull");
				request.setAttribute("message", "Amount has been dispatched");

			} else {
				logger.error("Withdraw Unsuccessfull");
				request.setAttribute("message",
						"Transaction is Not Successfull");
			}
			rd.forward(request, response);
		} catch (ServletException e) {
			logger.error(e);
		}
	}

}
