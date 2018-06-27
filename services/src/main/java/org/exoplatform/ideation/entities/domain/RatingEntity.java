package org.exoplatform.ideation.entities.domain;


import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Rate")
@ExoEntity
@Table(name = "IDEATION_RATING")
@NamedQueries({
        @NamedQuery(
                name = "Rate.getglobaleRateofIdea",
                query = "SELECT rate FROM Rate rate where rate.ideaId = :ideaId"
        ),
        @NamedQuery(
                name = "Rate.getRateByIdea",
                query = "SELECT rate FROM Rate rate"
        ),
        @NamedQuery(
                name = "Rate.CountRate",
                query = "SELECT rate FROM Rate rate where rate.ideaId = :ideaId"
        ),

        @NamedQuery(
                name = "Rate.Count",
                query = "SELECT count(rate) FROM Rate rate where rate.ideaId = :ideaId"
        ),


        @NamedQuery(
                name = "Rate.getRateByIdeaAndUser",
                query = "SELECT rate FROM Rate rate where rate.ideaId = :ideaId and rate.author = :author"
        ),






})


public class RatingEntity {

    public RatingEntity(){

    }

    @Id
    @Column(name = "RATE_ID")
    @SequenceGenerator(name = "SEQ_IDEATION_RATE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_RATE_ID")
    private long  id;
    @Column(name = "AUTHOR")
    private String author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;
    @Column(name = "Rate")
    private float rate ;
    @Column(name = "IDEA_ID")
    private long ideaId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }


}
