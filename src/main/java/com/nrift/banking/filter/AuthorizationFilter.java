package com.nrift.banking.filter;

import java.io.IOException;

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

import com.nrift.banking.exception.InsufficientPrivilegeException;
import com.nrift.banking.validators.accessValidator;


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

        HttpSession session = req.getSession(false);

        try {
            if(accessValidator.validate(session,req.getRequestURI())){
                chain.doFilter(request, response);
            }
        } catch (InsufficientPrivilegeException e) {
            logger.error("Authorization violation");
            request.setAttribute("errorMsg", e.getMessage());
            //redirection to be done
        }
    }

    public void destroy() {
        //close any resources here
    }

}
