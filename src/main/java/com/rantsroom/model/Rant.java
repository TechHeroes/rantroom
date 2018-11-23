package com.rantsroom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Rant extends AuditModel {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@NotNull
    @Size(max = 100)
    private String rantTitle;	
	@NotNull
    @Lob
	private String rantDesc;	
	private boolean deleted;

    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    private User user;
	  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getRantTitle() {
		return rantTitle;
	}
	public void setRantTitle(String rantTitle) {
		this.rantTitle = rantTitle;
	}
	public String getRantDesc() {
		return rantDesc;
	}
	public void setRantDesc(String rantDesc) {
		this.rantDesc = rantDesc;
	}
	@Override
	public String toString() {
		return "Rants [id=" + id + ", rantTitle=" + rantTitle + ", rantDesc=" + rantDesc + ", deleted=" + deleted
				+ ", user=" + user + "]";
	}
		
}
