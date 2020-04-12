package com.rantsroom.repository;
  
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import  org.springframework.stereotype.Repository;
  
import com.rantsroom.model.Like;
  
 @Repository 
 public interface LikeRepository extends CrudRepository<Like,Long> {
	 
	 Like findByRantId(long rantId);
  
  }
 