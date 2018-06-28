package org.exoplatform.ideation.entities.dto;

import org.exoplatform.ideation.entities.domain.CommentEntity;
import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable{

    public CommentDTO(){}

    public CommentDTO(CommentEntity commentEntity){
        this.id = commentEntity.getId();
        this.ideaId = commentEntity.getIdeaId();
        this.commentText = commentEntity.getCommentText();
        this.author = commentEntity.getAuthor();
        this.createdTime = commentEntity.getCreatedTime();
        }

        private long id;

    private String posterAvatar;


        private Date createdTime;

        private long ideaId;

        private String commentText;

        private long numcomments;

        private String author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getPosterAvatar() {
        return posterAvatar;
    }

    public void setPosterAvatar(String posterAvatar) {
        this.posterAvatar = posterAvatar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getNumcomments() {
        return numcomments;
    }

    public void setNumcomments(long numcomments) {
        this.numcomments = numcomments;
    }


}

