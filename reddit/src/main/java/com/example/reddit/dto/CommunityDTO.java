package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.reddit.model.Flair;
import com.example.reddit.model.Post;
import com.example.reddit.model.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



public class CommunityDTO {


    private int id;
    private Date creationDate;
    private String description;
    private byte isSuspended;
    private String suspendedReason;
    private UserDTO User;
    private ArrayList<PostDTO> Posts;
    

	public CommunityDTO(int id, Date creationDate, String description, byte isSuspended, String suspendedReason,
			UserDTO user, ArrayList<PostDTO> posts) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.isSuspended = isSuspended;
		this.suspendedReason = suspendedReason;
		User = user;
		Posts = posts;
	}
	public CommunityDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte getIsSuspended() {
		return isSuspended;
	}
	public void setIsSuspended(byte isSuspended) {
		this.isSuspended = isSuspended;
	}
	public String getSuspendedReason() {
		return suspendedReason;
	}
	public void setSuspendedReason(String suspendedReason) {
		this.suspendedReason = suspendedReason;
	}
	public UserDTO getUser() {
		return User;
	}
	public void setUser(UserDTO user) {
		User = user;
	}
	public ArrayList<PostDTO> getPosts() {
		return Posts;
	}
	public void setPosts(ArrayList<PostDTO> posts) {
		Posts = posts;
	}
    
    
    
    
	
}
