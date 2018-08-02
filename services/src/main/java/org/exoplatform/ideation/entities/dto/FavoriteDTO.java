package org.exoplatform.ideation.entities.dto;

import java.io.Serializable;
import java.util.Date;

public class FavoriteDTO implements Serializable {
    private long Id  ;
    private long ideaId ;

    private String author;
    private long numfav;
    private boolean fav ;

    private  IdeaDTO idea;
    private Date createdTime;

    public FavoriteDTO(){


    }


    public FavoriteDTO(long id, long ideaId, String author, long numfav, boolean fav, IdeaDTO idea, Date createdTime) {
        Id = id;
        this.ideaId = ideaId;
        this.author = author;
        this.numfav = numfav;
        this.fav = fav;
        this.idea = idea;
        this.createdTime = createdTime;
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

    public boolean isFav() {
        return fav;
    }

    public IdeaDTO getIdea() {
        return idea;
    }

    public void setIdea(IdeaDTO idea) {
        this.idea = idea;
    }
}
