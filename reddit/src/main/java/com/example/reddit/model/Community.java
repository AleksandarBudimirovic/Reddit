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
import jakarta.persistence.Entity;

@Entity
public class Community {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String description;

    private byte isSuspended;

    private String suspendedReason;

    @OneToMany(mappedBy="community")
    private List<Ban> bans;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="community")
    private List<Flair> flairs;

    @OneToMany(mappedBy="community")
    private List<Post> posts;

	public Community(Long id, Date creationDate, String description, byte isSuspended, String suspendedReason,
			List<Ban> bans, com.example.reddit.model.User user, List<Flair> flairs, List<Post> posts) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.isSuspended = isSuspended;
		this.suspendedReason = suspendedReason;
		this.bans = bans;
		this.user = user;
		this.flairs = flairs;
		this.posts = posts;
	}

	public Community() {
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

	public List<Ban> getBanneds() {
		return bans;
	}

	public void setBans(List<Ban> bans) {
		this.bans = bans;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Flair> getFlairs() {
		return flairs;
	}

	public void setFlairs(List<Flair> flairs) {
		this.flairs = flairs;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
    
    
	
}
