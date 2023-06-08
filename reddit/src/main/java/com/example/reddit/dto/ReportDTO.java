package com.example.reddit.dto;

import java.util.Date;



public class ReportDTO {

    private int id;
    private byte accepted;
    private String reason;
    private Date timestamp;
    private CommentDTO Comment;
    private PostDTO Post;
    
	public ReportDTO(int id, byte accepted, String reason, Date timestamp, CommentDTO comment, PostDTO post) {
		super();
		this.id = id;
		this.accepted = accepted;
		this.reason = reason;
		this.timestamp = timestamp;
		Comment = comment;
		Post = post;
	}

	public ReportDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public CommentDTO getComment() {
		return Comment;
	}

	public void setComment(CommentDTO comment) {
		Comment = comment;
	}

	public PostDTO getPost() {
		return Post;
	}

	public void setPost(PostDTO post) {
		Post = post;
	}

    
    
}
