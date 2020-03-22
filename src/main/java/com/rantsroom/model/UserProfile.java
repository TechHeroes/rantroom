package com.rantsroom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserProfile {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	//Profile photo attributes	
	private String fileName;	
	
	@OneToOne
	private User user;	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", fileName=" + fileName + ", user=" + user + "]";
	}
	
}
