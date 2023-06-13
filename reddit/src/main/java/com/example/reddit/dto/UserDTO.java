package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;

import jakarta.persistence.OneToMany;


public class UserDTO {

    private int id;
    private String avatar;
    private String description;
    private String displayName;
    private String password;
    private Date registrationDate;
    private String role;
    private String username;
    private ArrayList<BanDTO> Banneds = new ArrayList<BanDTO>();
    private ArrayList<CommentDTO> Comments = new ArrayList<CommentDTO>();
    private ArrayList<CommunityDTO> Communities = new ArrayList<CommunityDTO>();
    private ArrayList<ReactionDTO> Reactions = new ArrayList<ReactionDTO>();
    private ArrayList<PostDTO> Posts = new ArrayList<PostDTO>();
    
	public UserDTO(int id, String avatar, String description, String displayName, String password,
			Date registrationDate, String role, String username) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.description = description;
		this.displayName = displayName;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.username = username;
	}

	public UserDTO() {
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

	
    
    
	
}
