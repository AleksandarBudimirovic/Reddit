package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;


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
    private String User;
    
	public CommentDTO(int id, byte isDeleted, String text, Date timestamp, String user) {
		super();
		this.id = id;
		this.isDeleted = isDeleted;
		this.text = text;
		this.timestamp = timestamp;
		User = user;
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
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}

    
	
    
    
	
}
