package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.reddit.model.Comment;
import com.example.reddit.model.Community;
import com.example.reddit.model.Flair;
import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;
import com.example.reddit.model.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;




public class PostDTO {

    private Long id;
    private Date creationDate;
    private String imagePath;
    private String title;
    private String text;
    private ArrayList<CommentDTO> Comments = new ArrayList<CommentDTO>();
    private ArrayList<FlairDTO> Flairs = new ArrayList<FlairDTO>();
    private CommunityDTO Community;
    private UserDTO User;
    private ArrayList<ReactionDTO> Reactions = new ArrayList<ReactionDTO>();
    private ArrayList<ReportDTO> Reports = new ArrayList<ReportDTO>();
    
	public PostDTO(Long id, Date creationDate, String imagePath, String title, String text,
			ArrayList<CommentDTO> comments, ArrayList<FlairDTO> flairs, CommunityDTO community, UserDTO user,
			ArrayList<ReactionDTO> reactions, ArrayList<ReportDTO> reports) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.imagePath = imagePath;
		this.title = title;
		this.text = text;
		Comments = comments;
		Flairs = flairs;
		Community = community;
		User = user;
		Reactions = reactions;
		Reports = reports;
	}

	public PostDTO(Post obj) {
		this(obj.getId(), obj.getCreationDate(), obj.getImagePath(), obj.getTitle(), obj.getText(), getComments(obj.getComments()), getFlairs(obj.getFlairs()), new CommunityDTO(obj.getCommunity()), 
				new UserDTO(obj.getUser()), getReactions(obj.getReactions()), getReports(obj.getReports()));
	}
	
	public static ArrayList<CommentDTO> getComments(List<Comment> list) {
		ArrayList<CommentDTO> listDTO=new ArrayList<>();
		for(Comment object : list) {
			listDTO.add(new CommentDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<ReactionDTO> getReactions(List<Reaction> list) {
		ArrayList<ReactionDTO> listDTO=new ArrayList<>();
		for(Reaction object : list) {
			listDTO.add(new ReactionDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<ReportDTO> getReports(List<Report> list) {
		ArrayList<ReportDTO> listDTO=new ArrayList<>();
		for(Report object : list) {
			listDTO.add(new ReportDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<FlairDTO> getFlairs(List<Flair> list) {
		ArrayList<FlairDTO> listDTO=new ArrayList<>();
		for(Flair object : list) {
			listDTO.add(new FlairDTO(object));
		}
		return listDTO;
	}
	
	public PostDTO() {
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

	public ArrayList<CommentDTO> getComments() {
		return Comments;
	}

	public void setComments(ArrayList<CommentDTO> comments) {
		Comments = comments;
	}

	public ArrayList<FlairDTO> getFlairs() {
		return Flairs;
	}

	public void setFlairs(ArrayList<FlairDTO> flairs) {
		Flairs = flairs;
	}

	public CommunityDTO getCommunity() {
		return Community;
	}

	public void setCommunity(CommunityDTO community) {
		Community = community;
	}

	public UserDTO getUser() {
		return User;
	}

	public void setUser(UserDTO user) {
		User = user;
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
