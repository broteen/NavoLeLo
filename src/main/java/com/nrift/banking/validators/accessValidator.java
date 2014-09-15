package com.nrift.banking.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import com.nrift.banking.dto.UserDTO;
import com.nrift.banking.exception.InsufficientPrivilegeException;

public class accessValidator {

    public static boolean validate(HttpSession session, String requestURI) throws InsufficientPrivilegeException {
        int error=1;
        
        String adminPatternString = ".*/admin/.*";
        Pattern adminPattern = Pattern.compile(adminPatternString);
        Matcher adminMatcher = adminPattern.matcher(requestURI);
        boolean adminMatches = adminMatcher.matches();
        
        String customerPatternString = ".*/customer/.*";
        Pattern customerPattern = Pattern.compile(customerPatternString);
        Matcher customerMatcher = customerPattern.matcher(requestURI);
        boolean customerMatches = customerMatcher.matches();
        
        UserDTO checkUser = (UserDTO)session.getAttribute("user");
        if((adminMatches && !checkUser.isAdmin()) || (customerMatches && checkUser.isAdmin())){
                error=0;
        }
        if(error==0){
                throw new InsufficientPrivilegeException("Authorization Violation");             
        }else{
            return true;
        }
    }

}
