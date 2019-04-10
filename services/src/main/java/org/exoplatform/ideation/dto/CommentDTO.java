package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.CommentEntity;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {
    public CommentDTO() {
    }

    public CommentDTO(CommentEntity commente) {
        this.id = commente.getId();
        this.user = commente.getUSER();
        this.createdTime = commente.getCreatedTime();
        this.commentText = commente.getCommentText();
        this.id_ideac = commente.getIdea().getId();
    }

    private Long  id;
    private String user;
    private Date createdTime;
    private String  commentText;
    private Long id_ideac;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getId_ideac() {
        return id_ideac;
    }

    public void setId_ideac(Long id_ideac) {
        this.id_ideac = id_ideac;
    }
}
