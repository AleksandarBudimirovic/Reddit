package com.example.reddit.dto;

import java.util.Date;

import com.example.reddit.model.User;

public class UserDTO {

    private Long id;
    private String avatar;
    private String description;
    private String displayName;
    private String password;
    private Date registrationDate;
    private String role;
    private String username;
    private boolean banned; // New attribute for banning status

    public UserDTO(Long id, String avatar, String description, String displayName, String password,
                   Date registrationDate, String role, String username, boolean banned) {
        this.id = id;
        this.avatar = avatar;
        this.description = description;
        this.displayName = displayName;
        this.password = password;
        this.registrationDate = registrationDate;
        this.role = role;
        this.username = username;
        this.banned = banned;
    }

    public UserDTO(User obj) {
        this(obj.getId(), obj.getAvatar(), obj.getDescription(), obj.getDisplayName(), obj.getPassword(),
                obj.getRegistrationDate(), obj.getRole(), obj.getUsername(), false); // Default to false when converting from User
    }

    public UserDTO() {
        // Default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
