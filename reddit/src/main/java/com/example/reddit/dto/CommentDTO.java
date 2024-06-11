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
import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;




public class CommentDTO {


    private Long id;
    private byte isDeleted;
    private String text;
    private Date timestamp;
    private UserDTO User;
    private PostDTO Post;
    private CommentDTO mainComment;
    private ArrayList<CommentDTO> subComments = new ArrayList<CommentDTO>();
    private ArrayList<ReactionDTO> Reactions  = new ArrayList<ReactionDTO>();
    private ArrayList<ReportDTO> Reports  = new ArrayList<ReportDTO>();
    
   
    
	public CommentDTO(Long id, byte isDeleted, String text, Date timestamp, UserDTO user, PostDTO post,
			CommentDTO mainComment, ArrayList<CommentDTO> subComments, ArrayList<ReactionDTO> reactions,
			ArrayList<ReportDTO> reports) {
		super();
	    this.id = id;
	    this.isDeleted = isDeleted;
	    this.text = text;
	    this.timestamp = timestamp;
	    this.User = user;
	    this.Post = post;
	    this.mainComment = mainComment;
	    this.subComments = subComments;
	    this.Reactions = reactions;
	    this.Reports = reports;
	}
	
	public CommentDTO(Comment obj) {
		this(obj.getId(), obj.getIsDeleted(), obj.getText(), obj.getTimestamp(), new UserDTO(obj.getUser()), new PostDTO(obj.getPost()), new CommentDTO(obj.getMainComment()), new ArrayList<>(), 
				getReactions(obj.getReactions()), getReports(obj.getReports()));
	}
	
	
	
	public static ArrayList<CommentDTO> getComments(List<Comment> list) {
		ArrayList<CommentDTO> listDTO=new ArrayList();
		for(Comment object : list) {
			listDTO.add(new CommentDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<ReactionDTO> getReactions(List<Reaction> list) {
		ArrayList<ReactionDTO> listDTO=new ArrayList();
		for(Reaction object : list) {
			listDTO.add(new ReactionDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<ReportDTO> getReports(List<Report> list) {
		ArrayList<ReportDTO> listDTO=new ArrayList();
		for(Report object : list) {
			listDTO.add(new ReportDTO(object));
		}
		return listDTO;
	}
	
	public CommentDTO() {
		super();
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
