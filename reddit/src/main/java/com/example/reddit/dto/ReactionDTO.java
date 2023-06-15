package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;


public class ReactionDTO {

    private int id;
    private Date timestamp;
    private String type;
    private CommentDTO Comment;
    private PostDTO Post;
    private UserDTO User;

	public ReactionDTO(int id, Date timestamp, String type, CommentDTO comment, PostDTO post, UserDTO user) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.type = type;
		Comment = comment;
		Post = post;
		User = user;
	}

	public ReactionDTO(Reaction obj) {
		this(obj.getId(), obj.getTimestamp(), obj.getType(), new CommentDTO(obj.getComment()), new PostDTO(obj.getPost()), new UserDTO(obj.getUser()));
	}
	
	public ReactionDTO() {
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

	public UserDTO getUser() {
		return User;
	}

	public void setUser(UserDTO user) {
		User = user;
	}
    
    
	
}
