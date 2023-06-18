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

public class Report {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private byte accepted;

    private String reason;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

	public Report(Long id, byte accepted, String reason, Date timestamp, Comment comment, Post post) {
		super();
		this.id = id;
		this.accepted = accepted;
		this.reason = reason;
		this.timestamp = timestamp;
		this.comment = comment;
		this.post = post;
	}

	public Report() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getAccepted() {
		return accepted;
	}

	public void setAccepted(byte accepted) {
		this.accepted = accepted;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	
    
	
}
