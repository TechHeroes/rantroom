package com.rantsroom.controller;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rantsroom.model.Rant;
import com.rantsroom.model.User;
import com.rantsroom.repository.UserRepository;
import com.rantsroom.service.EmailService;
import com.rantsroom.service.RantService;
import com.rantsroom.service.UserService;
import com.rantsroom.validator.UserValidator;

@Controller
//@RequestMapping("/users")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;    
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private RantService rantService;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    
    public static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("year", currentYear);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, 
    		Model model) {
    	
    	userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        else {
        	// Disable user until they click on confirmation link in email
		    userForm.setActive(false);
		    userForm.setEmail_confirmed(false);
		    
		    // Generate random 36-character string token for confirmation link
		    	//userForm.setConfirmationToken(UUID.randomUUID().toString());
		    
		    userService.save(userForm);
		    
		    //Sending verification token via mail
		    //sendConfirmationMail(request,userForm);
		    
		    return "redirect:/confirm";
        }
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String error, Model model, String logout, 
    		String delete, Principal principal) {
    	
    	User user = null;
    	model.addAttribute("year", currentYear);
		try {
			user = userService.findByUsername(principal.getName());
		} catch (Exception e) {
			logger.info("No user found");
		}
    	if (error != null) 
            model.addAttribute("error", "Your username/password is invalid.");    		
    	
        if (logout != null) 
            model.addAttribute("message", "You have been logged out successfully.");            
        
        if(delete != null) { 
        	userRepository.delete(user);
        	model.addAttribute("message", "Your account has been deleted successfully.");        	               
        }
        try {
			if(!(user.isEmail_confirmed()))
				model.addAttribute("error", "Oops! Looks like you haven't verified your email yet.Please check your mail box.");
		} catch (NullPointerException e) {
			logger.info("No user found");
		}
        return "login";
    }   
    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal) {//, @AuthenticationPrincipal UserDetails currentUser) {    	
    	
    	User user = null;
    	/**
    	 * Finding logged in User
    	 */
    	try {
    		logger.info("CURRENT LOGGED-IN USER: ",principal.getName());
    		user = userService.findByUsername(principal.getName());			
		} catch (Exception e) {
			logger.info("No user logged in");
		}    	
    	model.addAttribute("user", user);    	
    	List<Rant> rants = rantService.findAll();
		
    	
    	model.addAttribute("rants", rants);
    	model.addAttribute("year", currentYear);    	
    	
        return "home";
    }
    
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirm(Model model,RedirectAttributes redirectAttributes) {   	
    	redirectAttributes.addFlashAttribute("postregistration",
    			"Thanks for joining RantRoom. Just one more step and you'll be ready to Rant.\r\n" + 
    			"We have sent you a confirmation mail. \nKindly click on the link given in mail to get verified and get started.");
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public String verify(Model model, @RequestParam("token") String token, RedirectAttributes redirectAttributes) {   	
    	User user = userService.findByConfirmationToken(token);
    	
    	if (user == null) // No token found in DB
    		redirectAttributes.addFlashAttribute("verifyUser", "Oops!  This is an invalid confirmation link or the link is expired.");
    	else { // Token found
    		redirectAttributes.addFlashAttribute("verifyUser", "Success!  Your e-mail is verified. Login below to start ranting.");
    		user.setActive(true);
    		user.setEmail_confirmed(true);
    	}
    	
        return "redirect:/login";
    }
    
    // Sending email for verification
    private void sendConfirmationMail(HttpServletRequest request, User userForm) {
		
    	String appUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();
		
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(userForm.getEmail());
		registrationEmail.setSubject("RantsRoom Registration Confirmation");
		registrationEmail.setText("Hi "+userForm.getFirstname()+",\n\nWelcome to RantsRoom! Your portal to rant about anything you like.\n\n"
				+ "To confirm your e-mail address, please click the link below:\n"
				+ appUrl + "/verification?token=" + userForm.getConfirmationToken());
		registrationEmail.setFrom("khan.ssaad@gmail.com");
		
		emailService.sendEmail(registrationEmail);
		
	}

}