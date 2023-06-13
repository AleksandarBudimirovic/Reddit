package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.reddit.dto.CommentDTO;
import com.example.reddit.dto.PostDTO;
import com.example.reddit.dto.ReactionDTO;
import com.example.reddit.dto.ReportDTO;
import com.example.reddit.dto.UserDTO;




public class CommentDTO {


    private int id;
    private byte isDeleted;
    private String text;
    private Date timestamp;
    private UserDTO User;
    private PostDTO Post;
    private CommentDTO mainComment;
    private ArrayList<CommentDTO> subComments = new ArrayList<CommentDTO>();
    private ArrayList<ReactionDTO> Reactions  = new ArrayList<ReactionDTO>();
    private ArrayList<ReportDTO> Reports  = new ArrayList<ReportDTO>();
    
    
    
	public CommentDTO(int id, byte isDeleted, String text, Date timestamp, UserDTO user, PostDTO post,
			CommentDTO mainComment, ArrayList<CommentDTO> subComments, ArrayList<ReactionDTO> reactions,
			ArrayList<ReportDTO> reports) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.text = text;
		this.timestamp = timestamp;
		User = user;
		Post = post;
		this.mainComment = mainComment;
		this.subComments = subComments;
		Reactions = reactions;
		Reports = reports;
	}
	
	public CommentDTO() {
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
	public UserDTO getUser() {
		return User;
	}
	public void setUser(UserDTO user) {
		User = user;
	}
	public PostDTO getPost() {
		return Post;
	}
	public void setPost(PostDTO post) {
		Post = post;
	}
	public CommentDTO getMainComment() {
		return mainComment;
	}
	public void setMainComment(CommentDTO mainComment) {
		this.mainComment = mainComment;
	}
	public ArrayList<CommentDTO> getSubComments() {
		return subComments;
	}
	public void setSubComments(ArrayList<CommentDTO> subComments) {
		this.subComments = subComments;
	}
	public ArrayList<ReactionDTO> getReactions() {
		return Reactions;
	}
	public void setReactions(ArrayList<ReactionDTO> reactions) {
		Reactions = reactions;
	}
	public ArrayList<ReportDTO> getReports() {
		return Reports;
	}
	public void setReports(ArrayList<ReportDTO> reports) {
		Reports = reports;
	}
    
    
    
	
}
