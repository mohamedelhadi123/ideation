package org.exoplatform.ideation.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.io.Serializable;

  
@Entity(name="fav")
@ExoEntity
@Table(name = " eries({
        @NamedQuery(name = "Fav.getAllFavByUser", query = "select f from fav f where f.user=:user"),
        @NamedQuery(name = "Fav.getAllFavByUserAndId", query = "select f from fav f where f.user=:user AND f.idea.id=:id"),
        @NamedQuery(name = "Fav.getAllFavByIdeaID", query = "select f from fav f where f.idea.id=:id"),
        @NamedQuery(name = "Fav.getAllOrderByFav", query = "SELECT f FROM fav f  GROUP BY f.idea.id order 


})

    
    @Id
    @Column(name = "FAV_ID")
    @GeneratedVa e
    private Long  id;
    @Column(name = "USER")
    private String user;

    @ManyToOne  
    @JoinColumn (name="ID_IDEAF")
    private IdeaEntity idea;
 
    public Favo r iteEntity(Long id ,String user, IdeaEntity idea) {
        this.id=id;
        this.user = user;
        this.idea = idea;
    }

    public FavoriteEntity() {
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

    public IdeaEntity getIdea() {
        return idea;
    }

    public void setIdea(IdeaEntity idea) {
        this.idea = idea;
 }