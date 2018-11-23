package com.rantsroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rantsroom.model.UserProfile;
import com.rantsroom.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
	public void save(UserProfile userProfile) {
		
    	userProfileRepository.save(userProfile);
	}
    
}
