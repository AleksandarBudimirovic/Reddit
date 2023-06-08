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

public class Comment {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private byte isDeleted;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post Post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User User;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment mainComment;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mainComment")
    private Set<Comment> subComments = new HashSet();

    @OneToMany(mappedBy="Comment")
    private List<Reaction> Reactions;

    @OneToMany(mappedBy="Comment")
    private List<Report> Reports;

	public Comment(int id, byte isDeleted, String text, Date timestamp, com.example.reddit.model.Post post,
			com.example.reddit.model.User user, Comment mainComment, Set<Comment> subComments, List<Reaction> reactions,
			List<Report> reports) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.text = text;
		this.timestamp = timestamp;
		Post = post;
		User = user;
		this.mainComment = mainComment;
		this.subComments = subComments;
		Reactions = reactions;
		Reports = reports;
	}

	public Comment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Post getPost() {
		return Post;
	}

	public void setPost(Post post) {
		Post = post;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Comment getMainComment() {
		return mainComment;
	}

	public void setMainComment(Comment mainComment) {
		this.mainComment = mainComment;
	}

	public Set<Comment> getSubComments() {
		return subComments;
	}

	public void setSubComments(Set<Comment> subComments) {
		this.subComments = subComments;
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
