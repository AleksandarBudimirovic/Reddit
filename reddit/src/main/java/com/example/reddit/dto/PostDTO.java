package com.example.reddit.dto;

import java.util.Date;
import java.util.List;




public class PostDTO {

    private int id;
    private Date creationDate;
    private String imagePath;
    private String title;
    private String text;
    private String User;

	
	public PostDTO(int id, Date creationDate, String imagePath, String title, String text, String user) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.imagePath = imagePath;
		this.title = title;
		this.text = text;
		User = user;
	}

	public PostDTO() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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


    
	
}
