package com.rantsroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rantsroom.model.User;
import com.rantsroom.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<User, Long> {
	
	void save (UserProfile userProfile);
}
