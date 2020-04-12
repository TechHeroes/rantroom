package com.rantsroom.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likeRant")
public class Like {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private boolean likeFlag;
    
	@OneToOne(fetch = FetchType.LAZY)
    private User user;	
    
	@OneToOne(fetch = FetchType.LAZY)
    private Rant rant;
	
	public Like() {
		
	}

	public Like(boolean likeFlag, User user, Rant rant) {
		super();		
		this.likeFlag = likeFlag;
		this.user = user;
		this.rant = rant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public boolean getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(boolean likeFlag) {
		this.likeFlag = likeFlag;
	}

	public User getUser() {
		return user;
	}

	public Rant getRant() {
		return rant;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", likeFlag=" + likeFlag + ", user=" + user + ", rant=" + rant + "]";
	}    

}
