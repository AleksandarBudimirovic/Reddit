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
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String imagePath;

    private String title;

    private String text;

    @OneToMany(mappedBy="post")
    private List<Comment> comments;

    @OneToMany(mappedBy="post")
    private List<Flair> flairs;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="post")
    private List<Reaction> reactions;

    @OneToMany(mappedBy="post")
    private List<Report> reports;

	public Post(Long id, Date creationDate, String imagePath, String title, String text, List<Comment> comments,
			List<Flair> flairs, Community community, User user, List<Reaction> reactions, List<Report> reports) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.imagePath = imagePath;
		this.title = title;
		this.text = text;
		this.comments = comments;
		this.flairs = flairs;
		this.community = community;
		this.user = user;
		this.reactions = reactions;
		this.reports = reports;
	}

	public Post() {
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
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Flair> getFlairs() {
		return flairs;
	}

	public void setFlairs(List<Flair> flairs) {
		this.flairs = flairs;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	
    
	
}
