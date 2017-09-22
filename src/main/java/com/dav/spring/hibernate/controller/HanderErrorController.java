package com.dav.spring.hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dav.spring.hibernate.common.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class HanderErrorController.
 */
@Controller
public class HanderErrorController {

    /**
     * Not found page.
     *
     * @return the string
     */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage() {
        return Constants.STR_404_PAGE;
    }

    /**
     * Error page.
     *
     * @return the string
     */
    @RequestMapping(value = "/error-page", method = RequestMethod.GET)
    public String errorPage() {
        return Constants.STR_404_PAGE;
    }

    /**
     * Access denied.
     *
     * @return the string
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return Constants.STR_403_PAGE;
    }
}
