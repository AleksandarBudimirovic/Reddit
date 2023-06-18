package com.example.reddit.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Flair {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community Community;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post Post;

	public Flair(Long id, String name, com.example.reddit.model.Community community,
			com.example.reddit.model.Post post) {
		super();
		this.id = id;
		this.name = name;
		Community = community;
		Post = post;
	}

	public Flair() {
		super();
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

	public Community getCommunity() {
		return Community;
	}

	public void setCommunity(Community community) {
		Community = community;
	}

	public Post getPost() {
		return Post;
	}

	public void setPost(Post post) {
		Post = post;
	}
    
    
	
}
