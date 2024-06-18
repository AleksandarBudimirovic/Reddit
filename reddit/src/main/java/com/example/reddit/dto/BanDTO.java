package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.Ban;
import com.fasterxml.jackson.annotation.JsonIgnore;



public class BanDTO {
	
    private Long id;
    private Date timestamp;
    @JsonIgnore
    private CommunityDTO Community;
    @JsonIgnore
    private UserDTO User;
    
	public BanDTO(Long id, Date timestamp, CommunityDTO community, UserDTO user) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
