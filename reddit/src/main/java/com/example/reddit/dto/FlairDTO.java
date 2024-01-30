package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Set;

import com.example.reddit.model.Community;
import com.example.reddit.model.Flair;

public class FlairDTO {

    private Long id;
    private String name;
    private ArrayList<CommunityDTO> community;
    
	
	
    public FlairDTO(Flair flair) {
        this.id = flair.getId();
        this.name = flair.getName();
        this.community = getCommunityDTOs(flair.getCommunities());
    }

    // Method to convert list of Community objects to list of CommunityDTO objects
    public static ArrayList<CommunityDTO> getCommunityDTOs(Set<Community> communities) {
    	ArrayList<CommunityDTO> communityDTOs = new ArrayList();
        for (Community community : communities) {
            communityDTOs.add(new CommunityDTO(community));
        }
        return communityDTOs;
    }



	public FlairDTO(Long id, String name, ArrayList<CommunityDTO> community) {
		super();
		this.id = id;
		this.name = name;
		this.community = community;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public ArrayList<CommunityDTO> getCommunity() {
		return community;
	}



	public void setCommunity(ArrayList<CommunityDTO> community) {
		this.community = community;
	}
	
	
	
    
    
	
}
