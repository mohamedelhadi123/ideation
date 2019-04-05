package org.exoplatform.ideation.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@ExoEntity
@Table(name = "IDEATION_RATING")
public class RatingEntity implements Serializable {
    public enum Status {
        LIKE,
        DISLIKE,
        NONE
    }

    @Id
    @Column(name = "RATE_ID")
    private Long  id;
    @Column(name = "USER")
    private String user;
    @Enumerated(EnumType.STRING)
    private IdeaEntity.Status status;
    @ManyToOne
    @JoinColumn (name="ID_IDEAR")
    private IdeaEntity idea;

    public RatingEntity(Long id, String user, IdeaEntity.Status status, IdeaEntity idea) {

        this.id = id;
        this.user = user;
        this.status = status;
        this.idea = idea;
    }

    public RatingEntity() {
    }

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

    public IdeaEntity.Status getStatus() {
        return status;
    }

    public void setStatus(IdeaEntity.Status status) {
        this.status = status;
    }

    public IdeaEntity getIdea() {
        return idea;
    }

    public void setIdea(IdeaEntity idea) {
        this.idea = idea;
    }
}
