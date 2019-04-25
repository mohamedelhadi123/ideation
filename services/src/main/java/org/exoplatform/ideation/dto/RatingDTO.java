package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.RatingEntity;

import java.io.Serializable;

public class RatingDTO implements Serializable {
    public RatingDTO() {
    }
    private RatingEntity.Status status;
    private String user;
    private Long id_idear;

    public RatingDTO(RatingEntity ratingEntity) {
       this.status=ratingEntity.getStatus();
       this.user=ratingEntity.getUser();
       this.id_idear=ratingEntity.getIdea().getId();
    }



    public RatingEntity.Status getStatus() {
        return status;
    }

    public void setStatus(RatingEntity.Status status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getId_idear() {
        return id_idear;
    }

    public void setId_idear(Long id_idear) {
        this.id_idear = id_idear;
    }
}
