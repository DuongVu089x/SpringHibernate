package com.dav.spring.hibernate.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping({"/login","/"})
    public String login(Principal principal, HttpSession httpSession) {
        if (principal != null && principal.getName() != null && !principal.getName().equals("")) {
            return "redirect:/student/";
        }else{
        	httpSession.removeAttribute("role");
        }
        return "login";
    }

}
