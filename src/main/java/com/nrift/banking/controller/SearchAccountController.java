package com.nrift.banking.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.nrift.banking.service.SearchAccountService;
import com.nrift.banking.utility.DatasourceConnectionManager;

/**
 * The Class SearchAccountController.
 */
@WebServlet(name = "SearchAccountController", urlPatterns = { "/searchAccountController" })
public class SearchAccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(SearchAccountController.class);

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> reqParams = new HashMap<String, Object>();
		if(request.getParameter("customerName")!="")
			reqParams.put("customerName",request.getParameter("customerName"));
		if(request.getParameter("customerId")!="")
			reqParams.put("customerId",Long.parseLong(request.getParameter("customerId")));
		if(request.getParameter("accountNo")!="")
			reqParams.put("accountNo",Long.parseLong(request.getParameter("accountNo")));
		if(request.getParameter("panNo")!="")
			reqParams.put("panNo",request.getParameter("panNo"));

		Connection con = null;
		try {
			con = DatasourceConnectionManager.getConnection((ComboPooledDataSource)getServletContext().getAttribute(
					"datasource"));
			SearchAccountService searchAccountService = new SearchAccountService();
			List<CustomerDTO> customerList=searchAccountService.searchCustomerDetails(con,reqParams);
			logger.info("Customer found with details=" + customerList);
			HttpSession session = request.getSession(false);
			session.setAttribute("customerList", customerList);				
			response.sendRedirect("searchResult.jsp");

			/*
                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher("/searchAccount.jsp");
                PrintWriter out = response.getWriter();
                logger.info("Customer Not found with details=");
                out.println("<font color=red>No Customer found with given Details</font>");
                rd.include(request, response);
			 */
		}catch(BankingException e) {
			DatasourceConnectionManager.rollbackConnection(con);
			request.setAttribute("errorMsg", e.getMessage()); //There should be an error block on around the top of every jsp page
			request.getRequestDispatcher("login.html").forward(request,response);
		}
	}
}



