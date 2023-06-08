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

public class Community {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String description;

    private byte isSuspended;

    private String suspendedReason;

    @OneToMany(mappedBy="Community")
    private List<Ban> Banneds;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User User;

    @OneToMany(mappedBy="Community")
    private List<Flair> Flairs;

    @OneToMany(mappedBy="Community")
    private List<Post> Posts;

	public Community(int id, Date creationDate, String description, byte isSuspended, String suspendedReason,
			List<Ban> banneds, com.example.reddit.model.User user, List<Flair> flairs, List<Post> posts) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.isSuspended = isSuspended;
		this.suspendedReason = suspendedReason;
		Banneds = banneds;
		User = user;
		Flairs = flairs;
		Posts = posts;
	}

	public Community() {
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

	public List<Ban> getBanneds() {
		return Banneds;
	}

	public void setBanneds(List<Ban> banneds) {
		Banneds = banneds;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public List<Flair> getFlairs() {
		return Flairs;
	}

	public void setFlairs(List<Flair> flairs) {
		Flairs = flairs;
	}

	public List<Post> getPosts() {
		return Posts;
	}

	public void setPosts(List<Post> posts) {
		Posts = posts;
	}
    
    
	
}
