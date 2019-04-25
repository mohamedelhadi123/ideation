package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.RatingEntity;

public class RatingDTO {

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

    public Long getId_idea() {
        return id_idear;
    }

    public void setId_idea(Long id_idear) {
        this.id_idear = id_idear;
    }
}
