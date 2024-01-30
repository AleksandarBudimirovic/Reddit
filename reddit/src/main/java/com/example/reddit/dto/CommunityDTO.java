package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.reddit.mapper.BanMapper;
import com.example.reddit.mapper.PostMapper;
import com.example.reddit.model.Ban;
import com.example.reddit.model.Comment;
import com.example.reddit.model.Community;
import com.example.reddit.model.Flair;
import com.example.reddit.model.Post;
import com.example.reddit.model.User;





public class CommunityDTO {


    private Long id;
    private Date creationDate;
    private String description;
    private byte isSuspended;
    private String suspendedReason;
    private UserDTO user;
    private ArrayList<FlairDTO> flair;
    private ArrayList<PostDTO> posts;
    private ArrayList<BanDTO> bans;
    
	public CommunityDTO(Long id, Date creationDate, String description, byte isSuspended, String suspendedReason,
			UserDTO user, ArrayList<FlairDTO> flair, ArrayList<PostDTO> posts, ArrayList<BanDTO> bans) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.isSuspended = isSuspended;
		this.suspendedReason = suspendedReason;
		this.user = user;
		this.flair = flair;
		this.posts = posts;
		this.bans = bans;
	}
	
    public CommunityDTO(Community community) {
        this.id = community.getId();
        this.creationDate = community.getCreationDate();
        this.description = community.getDescription();
        this.isSuspended = community.getIsSuspended();
        this.suspendedReason = community.getSuspendedReason();
        this.user = new UserDTO(community.getUser());
        this.flair = getFlairs(community.getFlairs());
        this.posts = getPosts(community.getPosts());
        this.bans = getBans(community.getBans());
    }
    
	    public static ArrayList<FlairDTO> getFlairs(Set<Flair> list) {
	        ArrayList<FlairDTO> listDTO = new ArrayList();
	        for (Flair object : list) {
	            listDTO.add(new FlairDTO(object));
	        }
	        return listDTO;
	    }
	    
	    public static ArrayList<PostDTO> getPosts(List<Post> list) {
	        ArrayList<PostDTO> listDTO = new ArrayList();
	        for (Post object : list) {
	            listDTO.add(new PostDTO(object));
	        }
	        return listDTO;
	    }
	    
	    public static ArrayList<BanDTO> getBans(List<Ban> list) {
	        ArrayList<BanDTO> listDTO = new ArrayList();
	        for (Ban object : list) {
	            listDTO.add(new BanDTO(object));
	        }
	        return listDTO;
	    }



	public CommunityDTO() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsSuspended() {
		return isSuspended;
	}

	public void setIsSuspended(byte isSuspended) {
		this.isSuspended = isSuspended;
	}

	public String getSuspendedReason() {
		return suspendedReason;
	}

	public void setSuspendedReason(String suspendedReason) {
		this.suspendedReason = suspendedReason;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}



	public ArrayList<FlairDTO> getFlair() {
		return flair;
	}

	public void setFlair(ArrayList<FlairDTO> flair) {
		this.flair = flair;
	}

	public ArrayList<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<PostDTO> posts) {
		this.posts = posts;
	}

	public ArrayList<BanDTO> getBans() {
		return bans;
	}

	public void setBans(ArrayList<BanDTO> bans) {
		this.bans = bans;
	}

	
    
    
    

	
    
    
    
	
}
