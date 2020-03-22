package com.rantsroom.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class User extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;	
    private String username;
    private String firstname;
    private String lastname;
    private String password;    
    private String email;
    private boolean active;
    private boolean email_confirmed;
    private String confirmationToken;
    
    @OnDelete(action=OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Rant> rants;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private UserProfile userProfile;
    
    public List<Rant> getRants() {
		return rants;
	}

	public void setRants(List<Rant> rants) {
		this.rants.clear();
		this.rants = rants;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
	public boolean isEmail_confirmed() {
		return email_confirmed;
	}

	public void setEmail_confirmed(boolean email_confirmed) {
		this.email_confirmed = email_confirmed;
	}

    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", email=" + email;
	}	
}
