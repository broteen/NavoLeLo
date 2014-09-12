package com.nrift.banking.filter;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nrift.banking.dao.UserValidationDAO;
import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.CustomException;


/**
 * The Class AuthorizationFilter.
 */
@WebFilter("/AuthorizationFilter")
public class AuthorizationFilter implements Filter {

    private Logger logger = Logger.getLogger(AuthorizationFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("AuthorizationFilter initialized");
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        logger.info("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);
        UserDTO checkUser = (UserDTO)session.getAttribute("user");

        if(uri.contains("userAdmin")){
            if(!checkUser.isAdmin()){
                logger.error("Unauthorized access request");
                //custom exception to be thrown
                res.sendRedirect("index.html");
            }
        }
        else if(uri.contains("userCustomer")){
            if(checkUser.isAdmin()){
                logger.error("Unauthorized access request");
                //custom exception to be thrown
                res.sendRedirect("searchAccount.jsp");
            }
        }
        else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }


    }

    public void destroy() {
        //close any resources here
    }

}
