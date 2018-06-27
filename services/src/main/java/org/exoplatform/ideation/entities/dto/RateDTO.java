package org.exoplatform.ideation.entities.dto;
import java.io.Serializable;
import java.util.Date;

public class RateDTO implements Serializable {


    public RateDTO(){}


    private long Id  ;
    private float generalRate;
    private long ideaId ;
    private String author;
    private float rate ;
    private Date createdTime;
    float result;
    private long countRate;
    private float numRate;


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

    public float getGeneralRate() {
        return generalRate;
    }

    public void setGeneralRate(float generalRate) {
        this.generalRate = generalRate;
    }

    public float getNumRate() {
        return numRate;
    }

    public void setNumRate(float numRate) {
        this.numRate = numRate;
    }

    public long getCountRate() {
        return countRate;
    }

    public void setCountRate(long countRate) {
        this.countRate = countRate;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }
}
