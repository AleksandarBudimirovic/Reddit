package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.Community;
import com.example.reddit.model.Post;
import com.example.reddit.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostDTO {

    private Long id;
    private Date creationDate;
    private String imagePath;
    private String title;
    private String text;
    private CommunityDTO Community;
    @JsonIgnore
    private UserDTO User;

    public PostDTO(Long id, Date creationDate, String imagePath, String title, String text,
        CommunityDTO community, UserDTO user) {
        this.id = id;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.title = title;
        this.text = text;
        this.Community = community;
        this.User = user;
    }

    public PostDTO(Post obj) {
        this(obj.getId(), obj.getCreationDate(), obj.getImagePath(), obj.getTitle(), obj.getText(),
            new CommunityDTO(obj.getCommunity()), new UserDTO(obj.getUser()));
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
}
