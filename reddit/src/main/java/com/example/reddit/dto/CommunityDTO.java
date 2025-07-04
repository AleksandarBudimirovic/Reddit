package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.Community;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CommunityDTO {

    private Long id;
    private String name;
    private Date creationDate;
    private String description;
    private byte isSuspended;
    private String suspendedReason;
    
    @JsonIgnore
    private UserDTO user;
    
    public CommunityDTO() {
        super();
    }

    public CommunityDTO(Long id, String name, Date creationDate, String description, byte isSuspended, String suspendedReason,
                        UserDTO user) {
        super();
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        this.user = user;
    }

    public CommunityDTO(Community community) {
        this.id = community.getId();
        this.name = community.getName();
        this.creationDate = community.getCreationDate();
        this.description = community.getDescription();
        this.isSuspended = community.getIsSuspended();
        this.suspendedReason = community.getSuspendedReason();
        this.user = new UserDTO(community.getUser());
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
}
