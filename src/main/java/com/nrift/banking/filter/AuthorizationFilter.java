package com.nrift.banking.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.InsufficientPriviledgeException;


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
        //logger.info("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);
        
        String adminPatternString = ".*/admin/.*";
        Pattern adminPattern = Pattern.compile(adminPatternString);
        Matcher adminMatcher = adminPattern.matcher(uri);
        boolean adminMatches = adminMatcher.matches();
        
        String customerPatternString = ".*/customer/.*";
        Pattern customerPattern = Pattern.compile(customerPatternString);
        Matcher customerMatcher = customerPattern.matcher(uri);
        boolean customerMatches = customerMatcher.matches();

        if(session!=null){
            UserDTO checkUser = (UserDTO)session.getAttribute("user");
            if(adminMatches && !checkUser.isAdmin()){
                    logger.error("Authorization violation");
                    //custom exception to be thrown
                    //throw new InsufficientPriviledgeException("Authorization Violation", "Access not permitted");
                    res.sendRedirect("index.jsp");
            }
            else if(customerMatches && checkUser.isAdmin()){
                    logger.error("Authorization violation");
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
