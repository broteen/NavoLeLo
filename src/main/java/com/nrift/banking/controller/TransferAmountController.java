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

import com.nrift.banking.utility.TransferAmountManager;
import com.nrift.banking.utility.TransferAmountDetails;
import com.nrift.banking.utility.TransferAuthorizationManager;
import com.nrift.banking.utility.UserDetails;
import com.nrift.banking.utility.UserInstantiation;
/**
 * Servlet implementation class TransferAmountController
 */
@WebServlet("/TransferAmountController")
public class TransferAmountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(TransferAuthorizationController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferAmountController() {
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
		
		Connection con = (Connection) getServletContext().getAttribute("connection");
		TransferAmountManager transAmount = new TransferAmountManager();
		HttpSession session= request.getSession(false);
		TransferAmountDetails transferAmountDetails = (TransferAmountDetails)session.getAttribute("transferAmountDetails"); 
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/transferSystemConformation.jsp");
				
		try{
			if (transAmount.IsTransferSuccessfull(con,transferAmountDetails)) {
				logger.info("Transaction is Successfull");
				request.setAttribute("message", "Transaction is Successfull");
				
			} else {
				logger.error("Transaction is Not Successfull");
				request.setAttribute("message", "Transaction is Not Successfull");
			}
			rd.forward(request, response);
		}catch(ServletException e)
		{
			//To be Implemented later this is not the correct implmentation
			response.getWriter().print(e.getMessage()+"loginController");
		}
	}

}
