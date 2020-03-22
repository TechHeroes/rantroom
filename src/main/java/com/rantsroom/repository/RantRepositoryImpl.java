package com.rantsroom.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.rantsroom.model.Rant;
import com.rantsroom.model.User;

public class RantRepositoryImpl{
	
	@Autowired
	EntityManager em;
	
	public List<Rant> findAllById(Long Id){
		User user = em.find(User.class, Id);
		List<Rant> rants = user.getRants();		
		return rants;		
	}
	

}
