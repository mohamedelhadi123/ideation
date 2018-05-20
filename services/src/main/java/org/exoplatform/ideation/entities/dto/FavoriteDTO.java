package org.exoplatform.ideation.entities.dto;

import java.io.Serializable;
import java.util.Date;

public class FavoriteDTO implements Serializable {
    private long            Id  ;
    private long ideaId ;

    private String   author;
    private long numfav;
    private boolean fav ;

    private Date createdTime;

    public FavoriteDTO(){

    }


    public long getId() {
        return Id;
    }

    public boolean getFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public long getNumfav() {
        return numfav;
    }

    public void setNumfav(long numfav) {
        this.numfav = numfav;
    }

    public void setId(long id) {
        Id = id;
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
}
