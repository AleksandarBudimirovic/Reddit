package com.example.reddit.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String avatar;

    private String description;

    private String displayName;

    private String password;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    private String role;

    private String username;

    @OneToMany(mappedBy="User")
    private List<Ban> Banneds;

    @OneToMany(mappedBy="User")
    private List<Comment> Comments;

    @OneToMany(mappedBy="User")
    private List<Community> Communities;

    @OneToMany(mappedBy="User")
    private List<Reaction> Reactions;

    @OneToMany(mappedBy="User")
    private List<Post> Posts;

	public User(int id, String avatar, String description, String displayName, String password, Date registrationDate,
			String role, String username, List<Ban> banneds, List<Comment> comments, List<Community> communities,
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
		Banneds = banneds;
		Comments = comments;
		Communities = communities;
		Reactions = reactions;
		Posts = posts;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<Ban> getBanneds() {
		return Banneds;
	}

	public void setBanneds(List<Ban> banneds) {
		Banneds = banneds;
	}

	public List<Comment> getComments() {
		return Comments;
	}

	public void setComments(List<Comment> comments) {
		Comments = comments;
	}

	public List<Community> getCommunities() {
		return Communities;
	}

	public void setCommunities(List<Community> communities) {
		Communities = communities;
	}

	public List<Reaction> getReactions() {
		return Reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		Reactions = reactions;
	}

	public List<Post> getPosts() {
		return Posts;
	}

	public void setPosts(List<Post> posts) {
		Posts = posts;
	}

    
    
	
}
