package com.rantsroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rantsroom.model.Rant;
import com.rantsroom.model.User;

@Repository
public interface RantRepository extends JpaRepository<Rant, Long> {
	
	Rant findByRantTitle(String rantTitle);
	//User findByRant(Rant rant);
	List<Rant> findByUser(User user);
	List<Rant> findAll();
	Optional<Rant> findById(Long Id);
}
