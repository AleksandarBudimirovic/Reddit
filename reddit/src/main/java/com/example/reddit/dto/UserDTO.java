package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Community;
import com.example.reddit.model.Post;
import com.example.reddit.model.Reaction;
import com.example.reddit.model.Report;
import com.example.reddit.model.User;




public class UserDTO {

    private Long id;
    private String avatar;
    private String description;
    private String displayName;
    private String password;
    private Date registrationDate;
    private String role;
    private String username;
    private ArrayList<BanDTO> Banned = new ArrayList<BanDTO>();
    private ArrayList<CommentDTO> Comments = new ArrayList<CommentDTO>();
    private ArrayList<CommunityDTO> Communities = new ArrayList<CommunityDTO>();
    private ArrayList<ReactionDTO> Reactions = new ArrayList<ReactionDTO>();
    private ArrayList<PostDTO> Posts = new ArrayList<PostDTO>();
    
    

	public UserDTO(Long id, String avatar, String description, String displayName, String password,
			Date registrationDate, String role, String username, ArrayList<BanDTO> banned,
			ArrayList<CommentDTO> comments, ArrayList<CommunityDTO> communities, ArrayList<ReactionDTO> reactions,
			ArrayList<PostDTO> posts) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.description = description;
		this.displayName = displayName;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.username = username;
		this.Banned = banned;
		this.Comments = comments;
		this.Communities = communities;
		this.Reactions = reactions;
		this.Posts = posts;
	}

	public UserDTO(User obj) {
		this(obj.getId(), obj.getAvatar(), obj.getDescription(), obj.getDisplayName(), obj.getPassword(), obj.getRegistrationDate(), obj.getRole(), 
				obj.getUsername(), getBans(obj.getBans()), getComments(obj.getComments()), getCommunities(obj.getCommunities()), getReactions(obj.getReactions()), getPosts(obj.getPosts()));
	}
	
	public static ArrayList<BanDTO> getBans(List<Ban> list) {
		ArrayList<BanDTO> listDTO=new ArrayList();
		for(Ban object : list) {
			listDTO.add(new BanDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<CommunityDTO> getCommunities(List<Community> list) {
		ArrayList<CommunityDTO> listDTO=new ArrayList();
		for(Community object : list) {
			listDTO.add(new CommunityDTO(object));
		}
		return listDTO;
	}
	
	public static ArrayList<CommentDTO> getComments(List<Comment> list) {
	    ArrayList<CommentDTO> listDTO = new ArrayList<>();
	    if (list != null) {
	        for (Comment comment : list) {
	            CommentDTO commentDTO = new CommentDTO(comment);
	            listDTO.add(commentDTO);
	        }
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
	
	public static ArrayList<PostDTO> getPosts(List<Post> list) {
		ArrayList<PostDTO> listDTO=new ArrayList();
		for(Post object : list) {
			listDTO.add(new PostDTO(object));
		}
		return listDTO;
	}
	
	public UserDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<BanDTO> getBanned() {
		return Banned;
	}

	public void setBanned(ArrayList<BanDTO> banned) {
		Banned = banned;
	}

	public ArrayList<CommentDTO> getComments() {
		return Comments;
	}

	public void setComments(ArrayList<CommentDTO> comments) {
		Comments = comments;
	}

	public ArrayList<CommunityDTO> getCommunities() {
		return Communities;
	}

	public void setCommunities(ArrayList<CommunityDTO> communities) {
		Communities = communities;
	}

	public ArrayList<ReactionDTO> getReactions() {
		return Reactions;
	}

	public void setReactions(ArrayList<ReactionDTO> reactions) {
		Reactions = reactions;
	}

	public ArrayList<PostDTO> getPosts() {
		return Posts;
	}

	public void setPosts(ArrayList<PostDTO> posts) {
		Posts = posts;
	}

	

	
    
    
	
}