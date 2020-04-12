package com.rantsroom.controller;

import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rantsroom.model.AjaxResponseBody;
import com.rantsroom.model.Like;
import com.rantsroom.model.Rant;
import com.rantsroom.model.User;
import com.rantsroom.repository.RantRepository;
import com.rantsroom.service.LikeService;
import com.rantsroom.service.UserService;

@Controller
public class LikeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LikeService likeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RantRepository rantRepository;


	@RequestMapping(value = "/likes/{rantId}/like", method = RequestMethod.POST)
	public ResponseEntity<?> setLike(@PathVariable long rantId,Principal principal) {

		AjaxResponseBody result = new AjaxResponseBody();
		Optional<Rant> rant = rantRepository.findById(rantId);
		Like like = rant.get().getLike();
		// If error, just return a 400 bad request, along with the error message
//		if (errors.hasErrors()) {
//
//			result.setMsg(
//					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
//			logger.error(errors.toString());
//			return ResponseEntity.badRequest().body(result);
//		}
		try {
			User user = userService.findByUsername(principal.getName());
		} catch (NullPointerException e) {
			logger.error("No user logged in");
			result.setMsg("User not logged in");
			return ResponseEntity.badRequest().body(result);
		}		
		like.setLikeFlag(true);
		likeService.save(like);

		logger.info(like.toString());

		return ResponseEntity.ok(result);

	}

	@RequestMapping(value = "/likes/{rantId}/unlike", method = RequestMethod.POST) 
	public ResponseEntity<?> removeLike(@PathVariable long rantId, Principal principal) {
  
		AjaxResponseBody result = new AjaxResponseBody(); 

		Optional<Rant> rant = rantRepository.findById(rantId);
		Like like = rant.get().getLike();
		
		try { 
			User user = userService.findByUsername(principal.getName()); 
			} catch(NullPointerException e) { 
				logger.error("No user logged in");
				result.setMsg("User not logged in"); 
				return ResponseEntity.badRequest().body(result); 
			}
		like.setLikeFlag(false);
		likeService.save(like);  
		logger.info(like.toString());
  
		return ResponseEntity.ok(result);
  
  }
}
