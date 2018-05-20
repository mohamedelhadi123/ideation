package org.exoplatform.ideation.entities.dto;

import java.io.Serializable;
import java.util.Date;

public class LikeDTO implements Serializable {
        private long            Id  ;
        private long ideaId ;
    private boolean like ;
        private long numlike;
        private String   author;

        private Date createdTime;

        public LikeDTO(){

        }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getNumlike() {
        return numlike;
    }

    public void setNumlike(long numlike) {
        this.numlike = numlike;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
