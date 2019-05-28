package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.FavoriteEntity;


import java.io.Serializable;

public class FavoritDTO implements Serializable {
  private String user;
  private Long id_Ideaf;
  private Long id;

  public FavoritDTO(FavoriteEntity favoriteEntity) {
    this.id = favoriteEntity.getId();
    this.user = favoriteEntity.getUser();
    this.id_Ideaf = favoriteEntity.getIdea().getId();
  }

  public FavoritDTO() {
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Long getId_Ideaf() {
    return id_Ideaf;
  }

  public void setId_Ideaf(Long id_Ideaf) {
    this.id_Ideaf = id_Ideaf;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}