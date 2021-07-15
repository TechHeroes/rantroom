package com.rantsroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rantsroom.model.Rant;
import com.rantsroom.model.User;
import com.rantsroom.repository.RantRepository;
import com.rantsroom.repository.RantRepositoryImpl;

@Service
public class RantServiceImpl implements RantService {

	@Autowired
	private RantRepository rantRepository;
	
	
	@Override
	public void save(Rant rant) {
		
		rantRepository.save(rant);
	}

	@Override
	public Rant findByRantTitle(String title) {
		
		return rantRepository.findByRantTitle(title);
	}

	

	@Override
	public List<Rant> findAll() {
		// TODO Auto-generated method stub
		return rantRepository.findAll();
	}

	@Override
	public List<Rant> findAllById(Long Id) {
		// TODO Auto-generated method stub
		return rantRepository.findAllById(Id);
	}

	@Override
	public Optional<Rant> findById(Long Id) {
		// TODO Auto-generated method stub
		return rantRepository.findById(Id);
	}

	@Override
	public List<Rant> findByUser(User user) {
		// TODO Auto-generated method stub
		return rantRepository.findByUser(user);
	}

	@Override
	public List<Rant> findAll(Sort by) {
		// TODO Auto-generated method stub
		return rantRepository.findAll(by);
	}
	
}
