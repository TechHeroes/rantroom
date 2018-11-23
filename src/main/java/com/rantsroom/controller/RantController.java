package com.rantsroom.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rantsroom.model.Rant;
import com.rantsroom.model.User;
import com.rantsroom.repository.RantRepository;
import com.rantsroom.service.RantService;
import com.rantsroom.service.RantServiceImpl;
import com.rantsroom.service.UserService;
import com.rantsroom.validator.RantValidator;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class RantController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RantService rantService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RantValidator rantValidator;
    
    @Autowired
    private RantRepository rantRepository;
    
    @Autowired
    private RantServiceImpl rantServiceImpl;
    
    @RequestMapping(value = "/users/rant", method = RequestMethod.GET)
    public String createRant(Model model, Principal principal) {
    	    
    	User user = null;
		try {
			user = userService.findByUsername(principal.getName());
		} catch (NullPointerException e) {
			logger.error("No user logged in");
		}
    	model.addAttribute("user", user);
    	model.addAttribute("rantForm", new Rant());    	
        return "users/rant";        		
    }
    
    @RequestMapping(value = "/users/rant", method = RequestMethod.POST)
    public String createRant(@ModelAttribute("ranttForm") Rant rantform,BindingResult bindingResult, 
    		Model model,Principal principal, RedirectAttributes redirectAttributes) {

    	
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	rantValidator.validate(rantform, bindingResult);
    	
    	if (bindingResult.hasErrors())
            return "users/rant";
        
    	else {
        	rantform.setUser(userService.findByUsername(principal.getName()));
	    	rantService.save(rantform);
	    	redirectAttributes.addFlashAttribute("rantstatus", "Success!  Your Rant is posted.");
	        return "redirect:/rant/"+rantform.getId();
    	}
        		
    }
    
    @RequestMapping(value = "/rant/{rantId}", method = RequestMethod.GET)
    public String RantDetails(@PathVariable Long rantId,Model model, Principal principal) {
    	
    	User user = null;
		try {
			user = userService.findByUsername(principal.getName());
		} catch (NullPointerException e) {
			logger.error("No user logged in");
		}
    	model.addAttribute("user", user);
    	Optional<Rant> rant = rantService.findById(rantId);
		model.addAttribute("rantDesc", rant.get());
		return "/users/rant";
    }
    @RequestMapping(value = "/editrant/{rantId}", method = RequestMethod.GET)
    public String editRant(@PathVariable Long rantId,Model model, Principal principal) {
    	
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	Optional<Rant> rant = rantService.findById(rantId);
		model.addAttribute("rantForm", rant.get());
		return "/users/editRant";
    }
    
    @RequestMapping(value = "/editrant/{rantId}", method = RequestMethod.POST)
    public String editRant(@PathVariable Long rantId, @ModelAttribute("rantForm") Rant rantform,
    		Model model, Principal principal, BindingResult bindingResult) {
    	
    	rantValidator.validate(rantform, bindingResult);
    	
    	if (bindingResult.hasErrors())
            return "/editrant/"+rantId;
        
    	else {
    		Optional<Rant> rant = rantService.findById(rantId);
    		updateRant(rant, rantform);
	    	rantService.save(rant.get());
	    	String rantUpdated = "Rant is updated succesfully";
			model.addAttribute("rantUpdated",rantUpdated);
			model.addAttribute("rant",rant);
	        return "redirect:/rant/"+rantId;
    	}
    }
	
	 @RequestMapping(value = "/deleterant/{rantId}", method = RequestMethod.POST)
	    public String deleteRant(@PathVariable Long rantId,Model model,
	    		RedirectAttributes redirectAttributes,Principal principal) {
	    	
		rantRepository.deleteById(rantId);
		User user = userService.findByUsername(principal.getName());
		redirectAttributes.addFlashAttribute("deleterant", "Your rant has been deleted successfully.");
		model.addAttribute("user", user);
		List<Rant> rant = rantServiceImpl.findAllById(user.getId());
		model.addAttribute("rants", rant);
		return "redirect:/users/profile";
	 }
	 private void updateRant(Optional<Rant> rant, Rant rantform) {
		 rant.get().setRantTitle(rantform.getRantTitle());
		 rant.get().setRantDesc(rantform.getRantDesc());
		 
	 }
}



    /*
@RequestMapping(value = "/users/postsuccess", method = RequestMethod.GET)
public String success(Model model,Principal principal) {    	
	
	User user = userService.findByUsername(principal.getName());
	model.addAttribute("user", user);
	model.addAttribute("verifyPost", "");
	return "/users/postsuccess";
}
public static Long getCurrentUserId() {
	SecurityContext securityContext = SecurityContextHolder.getContext();
	Authentication authentication = securityContext.getAuthentication();
	String id = null;
	if (authentication != null)
		if (authentication.getPrincipal() instanceof UserDetails)
			id = ((UserDetails) authentication.getPrincipal()).getUsername();
		else if (authentication.getPrincipal() instanceof String)
			id = (String) authentication.getPrincipal();
	try {
		return Long.valueOf(id != null ? id : "1"); //anonymoususer
	} catch (NumberFormatException e) {
		return 1L;
	}
}


    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }*/


