package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



public class CommunityDTO {


    private int id;
    private Date creationDate;
    private String description;
    private byte isSuspended;
    private String suspendedReason;
    
	public CommunityDTO(int id, Date creationDate, String description, byte isSuspended, String suspendedReason) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.isSuspended = isSuspended;
		this.suspendedReason = suspendedReason;
	}

	public CommunityDTO() {
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
    
    
    
	
}
