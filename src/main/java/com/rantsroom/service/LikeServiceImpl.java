package com.rantsroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.rantsroom.model.Like;
import com.rantsroom.model.Rant;
import com.rantsroom.model.User;
import com.rantsroom.repository.LikeRepository;

@Service 
public class LikeServiceImpl implements LikeService{
    
  @Autowired 
  private LikeService likeService; 
  
  @Autowired 
  private LikeRepository likeRepository;
  
	/*
	 * @Override
	 * 
	 * @Query("FROM like_rant l WHERE l.rant_id=?1") public boolean
	 * findLikeStatus(@Param("rantId") long rantId) {
	 * System.out.println("Inside find like status: rant id is :"+rantId); return
	 * likeService.findLikeStatus(rantId); }
	 */

	@Override
	public void save(Like like) {
		
		likeRepository.save(like);
		
	}
 
  
  }
 