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

import com.nrift.banking.dto.TransferAmountDTO;
import com.nrift.banking.exception.BankingException;
import com.nrift.banking.service.DepositeService;

/**
 * The Class DepositeController.
 */
@WebServlet(name = "Deposite", urlPatterns = { "/DepositeController" })
public class DepositeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(DepositeController.class);

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inside deposite controller class");
        long accountNumber= Long.parseLong(request.getParameter("accountNumber"));
        long amount =Long.parseLong( request.getParameter("amount"));
        String errorMsg = null;
        if (accountNumber ==0L) {
            errorMsg = "Account Number can not be empty";
        }
        if (amount == 0L) {
            errorMsg = "Amount can not be  empty";
        }

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/deposite.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMsg + "</font>");
            rd.include(request, response);
        } else {

            Connection con = (Connection) getServletContext().getAttribute(
                    "connection");
            DepositeService deposite=new DepositeService();

            try{
                logger.info("Inside deposite controller class");
                TransferAmountDTO depositeAmountDetails=new TransferAmountDTO(0L,accountNumber,amount,null);
                HttpSession session= request.getSession(false);
                long customerId  = deposite.validateAccountNumber(con,accountNumber);
                if (customerId != 0L) {
                    logger.info("account found with given account number =" + customerId);	

                    if(amount>0L)
                    {
                        logger.error("show the deposite details.....");
                        session.setAttribute("depositeAmountDetails", depositeAmountDetails);
                        //session.setAttribute("depositeAccountNumber", accountNumber);
                        //session.setAttribute("depositeAmount", amount);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/confirmDeposite.jsp");
                        rd.forward(request, response);

                    }
                    else{
                        RequestDispatcher rd = getServletContext()
                                .getRequestDispatcher("/deposite.jsp");
                        PrintWriter out = response.getWriter();
                        logger.error("Invalid Amount");
                        out.println("<font color=red>Invalid amount</font>");
                        rd.include(request, response);
                    }				
                }

                else {
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher("/deposite.jsp");
                    PrintWriter out = response.getWriter();
                    logger.error("account not found=");
                    out.println("<font color=red>Invalid account number</font>");
                    rd.include(request, response);
                }
            }catch(BankingException |ServletException| IOException e) {
                try {
                    con.rollback();
                    logger.error(" Exception Thrown="+e.getMessage());
                } catch(SQLException e1) {
                    logger.error("Rollback error"+e1.getMessage());
                }
                request.setAttribute("errorMsg", "Deposite Can't be performed!");//There should be an error block on around the top of every jsp page
                request.getRequestDispatcher("deposite.jsp").forward(request,response);
            }
        }
    }


}



