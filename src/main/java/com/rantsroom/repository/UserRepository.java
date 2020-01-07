package com.rantsroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rantsroom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    Optional<User> findById(Long Id);
    User getOne(Long Id);    
}
