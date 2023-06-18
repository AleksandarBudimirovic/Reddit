package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Community;
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


    private Long id;
    private Date creationDate;
    private String description;
    private byte isSuspended;
    private String suspendedReason;
    private UserDTO user;
    private ArrayList<PostDTO> posts;
    private ArrayList<BanDTO> bans;
    

	public CommunityDTO(Long id, Date creationDate, String description, byte isSuspended, String suspendedReason,
			UserDTO user, ArrayList<PostDTO> posts, ArrayList<BanDTO> bans) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.isSuspended = isSuspended;
		this.suspendedReason = suspendedReason;
		this.bans = bans;
		this.user = user;
		this.posts = posts;
	}
	
	public CommunityDTO(Community obj) {
		this(obj.getId(), obj.getCreationDate(), obj.getDescription(), obj.getIsSuspended(), obj.getSuspendedReason(), new UserDTO(obj.getUser()), getPosts(obj.getPosts()), getBans(obj.getBanneds()));
	}
	
	public static ArrayList<PostDTO> getPosts(List<Post> list) {
		ArrayList<PostDTO> listDTO=new ArrayList<>();
		for(Post object : list) {
			listDTO.add(new PostDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<BanDTO> getBans(List<Ban> list) {
		ArrayList<BanDTO> listDTO=new ArrayList<>();
		for(Ban object : list) {
			listDTO.add(new BanDTO(object));
		}
		return listDTO;
	}
	
	public CommunityDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public ArrayList<PostDTO> getPosts() {
		return posts;
	}
	public void setPosts(ArrayList<PostDTO> posts) {
		this.posts = posts;
	}

	public ArrayList<BanDTO> getBans() {
		return bans;
	}

	public void setBans(ArrayList<BanDTO> bans) {
		this.bans = bans;
	}
    
    
    
    
	
}
