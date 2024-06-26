package com.example.reddit.dto;

import com.example.reddit.model.Report;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class ReportDTO {

    private Long id;
    private Byte accepted; // Use Byte instead of byte
    private String reason;
    private Date timestamp;

    @JsonIgnore
    private CommentDTO comment;

    @JsonIgnore
    private PostDTO post;

    private UserDTO user;

    public ReportDTO() {
    	super();
    }

    public ReportDTO(Long id, Byte accepted, String reason, Date timestamp, CommentDTO comment, PostDTO post, UserDTO user) {
        this.id = id;
        this.accepted = accepted;
        this.reason = reason;
        this.timestamp = timestamp;
        if (comment != null) {
            this.comment = comment;
        }
        if (post != null) {
            this.post = post;
        }
        if (user != null) {
            this.user = user;
        }
    }

    public ReportDTO(Report report) {
        this(report.getId(), report.getAccepted(), report.getReason(), report.getTimestamp(),
                report.getComment() != null ? new CommentDTO(report.getComment()) : null,
                report.getPost() != null ? new PostDTO(report.getPost()) : null,
                report.getReportedUser() != null ? new UserDTO(report.getReportedUser()) : null);
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getAccepted() {
        return accepted;
    }

    public void setAccepted(Byte accepted) {
        this.accepted = accepted;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public CommentDTO getComment() {
        return comment;
    }

    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
