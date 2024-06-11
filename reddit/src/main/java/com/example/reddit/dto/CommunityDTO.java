package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.reddit.model.Ban;
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
    
    public CommunityDTO() {
        super();
    }

    public CommunityDTO(Long id, Date creationDate, String description, byte isSuspended, String suspendedReason,
            UserDTO user) {
        super();
        this.id = id;
        this.creationDate = creationDate;
        this.description = description;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        this.user = user;
    }

    public CommunityDTO(Community community) {
        this.id = community.getId();
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
