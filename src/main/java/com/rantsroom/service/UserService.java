package com.rantsroom.service;

import java.util.Optional;

import com.rantsroom.model.User;

public interface UserService {
	
    void save(User user);

    User findByUsername(String username);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    Optional<User> findById(Long Id);
    User getOne(Long Id);
}
