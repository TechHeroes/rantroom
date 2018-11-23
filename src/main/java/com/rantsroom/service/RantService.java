package com.rantsroom.service;

import java.util.List;

import java.util.Optional;

import com.rantsroom.model.Rant;
import com.rantsroom.model.User;

public interface RantService {
	
	void save (Rant rant);
	Rant findByRantTitle(String title);
	List<Rant> findByUser(User user);
	List<Rant> findAll();
	Optional<Rant> findById(Long Id);
}
