package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class ReactionDTO {

    private Long id;
    private Date timestamp;
    private String type;
    @JsonIgnore
    private CommentDTO comment;
    @JsonIgnore
    private PostDTO post;
    @JsonIgnore
    private UserDTO user;

	

	public ReactionDTO(Reaction obj) {
		this(obj.getId(), obj.getTimestamp(), obj.getType(), new CommentDTO(obj.getComment()), new PostDTO(obj.getPost()), new UserDTO(obj.getUser()));
	}



	public ReactionDTO(Long id, Date timestamp, String type, CommentDTO comment, PostDTO post, UserDTO user) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
		this.comment = comment;
		this.post = post;
		this.user = user;
	}



	public ReactionDTO() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
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



	public CommentDTO getComment() {
		return comment;
	}



	public void setComment(CommentDTO comment) {
		this.comment = comment;
	}



	public PostDTO getPost() {
		return post;
	}



	public void setPost(PostDTO post) {
		this.post = post;
	}



	public UserDTO getUser() {
		return user;
	}



	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
    
    
	
}
