package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.Ban;



public class BanDTO {
	
    private int id;
    private Date timestamp;
    private CommunityDTO Community;
    private UserDTO User;
    
	public BanDTO(int id, Date timestamp, CommunityDTO community, UserDTO user) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		Community = community;
		User = user;
		
	}
	public BanDTO(Ban obj) {
		this(obj.getId(), obj.getTimestamp(), new CommunityDTO(obj.getCommunity()), new UserDTO(obj.getUser()));
	}

	public BanDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public CommunityDTO getCommunity() {
		return Community;
	}

	public void setCommunity(CommunityDTO community) {
		Community = community;
	}

	public UserDTO getUser() {
		return User;
	}

	public void setUser(UserDTO user) {
		User = user;
	}
    
    
	
}
