package com.nrift.banking.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.ChangePasswordService;

/**
 * Servlet implementation class ChangePasswordController
 */
@WebServlet("/ChangePasswordController")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(ChangePasswordController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordController() {
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

		String oldPswd = request.getParameter("currentPswd");
		String newPswd = request.getParameter("newPswd");
		String confirmPswd = request.getParameter("confirmPswd");
		List<String> emptyFieldList = new ArrayList<String>();

		String errorMsg = null;
		if (oldPswd == null || oldPswd.equals("")) {
			emptyFieldList.add("Old Password");
		}
		if (newPswd == null || newPswd.equals("")) {
			emptyFieldList.add("New Password");
		}
		if (confirmPswd == null || confirmPswd.equals("")) {
			emptyFieldList.add("Confirm Password");
		}
		if (!emptyFieldList.isEmpty()) {
			errorMsg = "The following fields cannot be empty:";
			for (String emptyField : emptyFieldList) {
				errorMsg.concat(emptyField);
			}
		}

		if (errorMsg == null) {

			Connection con = (Connection) getServletContext().getAttribute(
					"connection");

			ChangePasswordService changePswd = new ChangePasswordService();
			try {
				HttpSession session = request.getSession(false);
				logger.info("Transaction is Authorised");
				UserDTO user = (UserDTO) session.getAttribute("user");
				long userId = user.getUserId();
				String dbPaswd = changePswd.getOldPassword(con, userId);
				if (!oldPswd.equals(dbPaswd)) {
					errorMsg = "Your currnt password is wrong";
				} else if (newPswd.equals(confirmPswd)) {
					changePswd.updatePassword(con, userId, newPswd);

				} else {
					errorMsg = "password Mitchmatch";
				}

				request.setAttribute("infoMsg",
						"Your password has been changed successfully");

				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/logout");
				rd.forward(request, response);
				
			} catch (BankingException e) {
				try {
					con.rollback();
					logger.error(" Exception Thrown" + e.getMessage());
				} catch (SQLException e1) {
					logger.error("Rollback error=" + e1.getMessage());
				}
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("changePassword.jsp").forward(
						request, response);
			}

		} else {
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("changePassword.jsp").forward(request,
					response);
		}

	}
}
