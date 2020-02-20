package com.rantsroom.controller;

import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rantsroom.model.User;
import com.rantsroom.service.UserService;

@Controller
public class RantRoomErrorController implements ErrorController  {
	
	@Autowired
    private UserService userService;
 
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, Principal principal) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
         
        User user = null;
    	/**
    	 * Finding logged in User
    	 */
    	try {
    		//logger.info("CURRENT LOGGED-IN USER: ",principal.getName());
    		System.out.println("CURRENT LOGGED-IN USER: "+principal.getName());
    		user = userService.findByUsername(principal.getName());			
		} catch (Exception e) {
			logger.info("No user logged in");
		}    	
    	model.addAttribute("user", user);
    	
        model.addAttribute("year", UserController.currentYear);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
         
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
            	model.addAttribute("error404", "Uh Oh! We couldn't find the page you're looking for.");
                return "error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            	model.addAttribute("error500", "Don't worry! Our Engineers are on it.");
                return "error";
            }
        }
    	logger.info("Error occurred");
        return "error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}