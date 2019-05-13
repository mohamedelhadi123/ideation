package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.RatingEntity;

import java.io.Serializable;
/*
 *
 *
 *
 * Class DTO For RatingEntity
 *
 *
 *
 *
 */
public class RatingDTO implements Serializable {
    public RatingDTO() {
    }
    private RatingEntity.Status status;
    private String user;
    private Long id_idear;
    private Long id;

    public RatingDTO(RatingEntity ratingEntity) {
        this.id=ratingEntity.getId();
        this.status=ratingEntity.getStatus();
        this.user=ratingEntity.getUser();
        this.id_idear=ratingEntity.getIdea().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
