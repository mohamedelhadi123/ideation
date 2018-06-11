package org.exoplatform.ideation.entities.dto;
import java.io.Serializable;
import java.util.Date;

public class RateDTO implements Serializable {


    public RateDTO(){}


    private long Id  ;
    private float numRate;
    private long ideaId ;
    private String author;
    private float rate ;
    private Date createdTime;



    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }



    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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

    public float getNumRate() {
        return numRate;
    }

    public void setNumRate(float numRate) {
        this.numRate = numRate;
    }
}
