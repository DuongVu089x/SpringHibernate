package com.dav.spring.hibernate.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dav.spring.hibernate.common.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
public class LoginController {
	
	
    /**
     * Login.
     *
     * @return the string
     */
    @RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
    public String login(Principal principal, HttpSession httpSession) {
        if (principal != null && principal.getName() != null && !principal.getName().equals("")) {
            return "redirect:/student/";
        }else{
        	httpSession.removeAttribute(Constants.STR_ROLE);
        	httpSession.removeAttribute(Constants.STR_USERNAME);
        	httpSession.removeAttribute(Constants.STR_JWT);
        }
        return "login";
    }

}
