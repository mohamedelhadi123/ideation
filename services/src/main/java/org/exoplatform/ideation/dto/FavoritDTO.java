package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.FavoriteEntity;

public class FavoritDTO {
    private String user;
    private Long id_Ideaf;

    public FavoritDTO(FavoriteEntity favoriteEntity) {
        this.user = favoriteEntity.getUser();
        this.id_Ideaf = favoriteEntity.getIdea().getId();
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
}
