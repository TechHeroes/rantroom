package com.rantsroom.controller;
/*package com.webapp.rantsroom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.rantsroom.exception.UserNotFoundException;
import com.webapp.rantsroom.model.User;
import com.webapp.rantsroom.repository.UserRepository;

public class AdminController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public User retrieveUser(@PathVariable long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		return user.get();
	}
	@RequestMapping(value = "/users/{id}}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}	
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateStudent(@RequestBody User user, @PathVariable long id) {

		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}

}
*/