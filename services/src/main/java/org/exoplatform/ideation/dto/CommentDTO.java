package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.CommentEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class CommentDTO implements Serializable {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public CommentDTO() {
    }

    public CommentDTO(CommentEntity comment) {
        this.id=comment.getId();
        this.user = comment.getUSER();
        if (comment.getCreatedTime()!= null) {
            this.createdTime= formatter.format(comment.getCreatedTime());
        }
        this.commentText = comment.getCommentText();
        this.id_ideac = comment.getIdea().getId();
    }
    private Long id;
    private String user;
    private String createdTime;
    private String  commentText;
    private Long id_ideac;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
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
