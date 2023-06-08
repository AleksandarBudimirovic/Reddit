package com.example.reddit.dto;

import java.util.Date;



public class BanDTO {
	
    private int id;
    private Date timestamp;
    private String Community;
    private String User;
    
	public BanDTO(int id, Date timestamp, String community, String user) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		Community = community;
		User = user;
		//asdasd
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

	public String getCommunity() {
		return Community;
	}

	public void setCommunity(String community) {
		Community = community;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}
    
    
	
}
