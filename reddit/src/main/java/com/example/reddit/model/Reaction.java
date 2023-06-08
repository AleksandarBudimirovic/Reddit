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

public class Reaction {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    private String type;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment Comment;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post Post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User User;

	public Reaction(int id, Date timestamp, String type, com.example.reddit.model.Comment comment,
			com.example.reddit.model.Post post, com.example.reddit.model.User user) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
		Comment = comment;
		Post = post;
		User = user;
	}

	public Reaction() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Comment getComment() {
		return Comment;
	}

	public void setComment(Comment comment) {
		Comment = comment;
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
    
    
	
}
