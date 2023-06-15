package com.example.reddit.dto;

import com.example.reddit.model.Community;
import com.example.reddit.model.Flair;

public class FlairDTO {

    private int id;
    private String name;
    private CommunityDTO Community;
    private PostDTO Post;
    
	public FlairDTO(int id, String name, CommunityDTO community, PostDTO post) {
		super();
		this.id = id;
		this.name = name;
		Community = community;
		Post = post;
	}

	public FlairDTO(Flair obj) {
		
	}
	
	public FlairDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommunityDTO getCommunity() {
		return Community;
	}

	public void setCommunity(CommunityDTO community) {
		Community = community;
	}

	public PostDTO getPost() {
		return Post;
	}

	public void setPost(PostDTO post) {
		Post = post;
	}
    
    
	
}
