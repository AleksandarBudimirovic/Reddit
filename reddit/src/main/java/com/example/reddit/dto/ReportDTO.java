package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;
import com.fasterxml.jackson.annotation.JsonIgnore;



public class ReportDTO {

    private Long id;
    private byte accepted;
    private String reason;
    private Date timestamp;
    @JsonIgnore
    private CommentDTO Comment;
    @JsonIgnore
    private PostDTO Post;
    
	public ReportDTO(Long id, byte accepted, String reason, Date timestamp, CommentDTO comment, PostDTO post) {
		super();
		this.id = id;
		this.accepted = accepted;
		this.reason = reason;
		this.timestamp = timestamp;
		Comment = comment;
		Post = post;
	}

	public ReportDTO(Report obj) {
		this(obj.getId(), obj.getAccepted(), obj.getReason(), obj.getTimestamp(), new CommentDTO(obj.getComment()), new PostDTO(obj.getPost()));
	}
	
	public ReportDTO() {
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
