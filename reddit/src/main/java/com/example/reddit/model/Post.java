package com.example.reddit.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Post {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String imagePath;

    private String title;

    private String text;

    @OneToMany(mappedBy="Post")
    private List<Comment> Comments;

    @OneToMany(mappedBy="Post")
    private List<Flair> Flairs;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community Community;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User User;

    @OneToMany(mappedBy="Post")
    private List<Reaction> Reactions;

    @OneToMany(mappedBy="Post")
    private List<Report> Reports;

	public Post(int id, Date creationDate, String imagePath, String title, String text, List<Comment> comments,
			List<Flair> flairs, com.example.reddit.model.Community community, com.example.reddit.model.User user,
			List<Reaction> reactions, List<Report> reports) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.imagePath = imagePath;
		this.title = title;
		this.text = text;
		Comments = comments;
		Flairs = flairs;
		Community = community;
		User = user;
		Reactions = reactions;
		Reports = reports;
	}

	public Post() {
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Comment> getComments() {
		return Comments;
	}

	public void setComments(List<Comment> comments) {
		Comments = comments;
	}

	public List<Flair> getFlairs() {
		return Flairs;
	}

	public void setFlairs(List<Flair> flairs) {
		Flairs = flairs;
	}

	public Community getCommunity() {
		return Community;
	}

	public void setCommunity(Community community) {
		Community = community;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public List<Reaction> getReactions() {
		return Reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		Reactions = reactions;
	}

	public List<Report> getReports() {
		return Reports;
	}

	public void setReports(List<Report> reports) {
		Reports = reports;
	}
    
    
	
}
