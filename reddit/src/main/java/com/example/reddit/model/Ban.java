package com.example.reddit.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Ban {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community Community;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User User;

	public Ban(int id, Date timestamp, Community Community, com.example.reddit.model.User user) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		Community = Community;
		User = user;
	}

	public Ban() {
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

	public Community getCommunity() {
		return Community;
	}

	public void setRCommunity(Community rCommunity) {
		Community = rCommunity;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}
	
    
    
}
