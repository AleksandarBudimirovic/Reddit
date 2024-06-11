package com.example.reddit.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.reddit.model.Comment;
import com.example.reddit.model.Post;
import com.example.reddit.model.User;

public class CommentDTO {

    private Long id;
    private byte isDeleted;
    private String text;
    private Date timestamp;
    private UserDTO user;
    private PostDTO post;
    private CommentDTO mainComment;
    
    public CommentDTO(Long id, byte isDeleted, String text, Date timestamp, UserDTO user, PostDTO post,
                      CommentDTO mainComment) {
        this.id = id;
        this.isDeleted = isDeleted;
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
        this.post = post;
        this.mainComment = mainComment;
    }
    
    public CommentDTO(Comment obj) {
        this(obj.getId(), obj.getIsDeleted(), obj.getText(), obj.getTimestamp(), new UserDTO(obj.getUser()),
                new PostDTO(obj.getPost()), new CommentDTO(obj.getMainComment()));
    }
    
    public static ArrayList<CommentDTO> getComments(List<Comment> list) {
        ArrayList<CommentDTO> listDTO = new ArrayList<>();
        for (Comment object : list) {
            listDTO.add(new CommentDTO(object));
        }
        return listDTO;
    }
    
    public CommentDTO() {
        super();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public PostDTO getPost() {
        return post;
    }
    public void setPost(PostDTO post) {
        this.post = post;
    }
    public CommentDTO getMainComment() {
        return mainComment;
    }
    public void setMainComment(CommentDTO mainComment) {
        this.mainComment = mainComment;
    }
}
