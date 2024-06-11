package com.example.reddit.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String avatar;

    private String description;

    private String displayName;

    private String password;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    private String role;

    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Ban> bans;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Community> communities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Reaction> reactions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    private List<Post> posts;

	public User(Long id, String avatar, String description, String displayName, String password, Date registrationDate,
			String role, String username, List<Ban> bans, List<Comment> comments, List<Community> communities,
			List<Reaction> reactions, List<Post> posts) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.description = description;
		this.displayName = displayName;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.username = username;
		this.bans = bans;
		this.comments = comments;
		this.communities = communities;
		this.reactions = reactions;
		this.posts = posts;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Ban> getBans() {
		return bans;
	}

	public void setBans(List<Ban> bans) {
		this.bans = bans;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	
	

    
    
	
}
