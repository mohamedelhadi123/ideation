package org.exoplatform.ideation.entities.domain;


import org.exoplatform.commons.api.persistence.ExoEntity;
import java.util.*;

import javax.persistence.*;
import javax.xml.crypto.Data;

@Entity(name = "Favorite")
@ExoEntity
@Table(name = "IDEATION_FAV")
@NamedQueries({
        @NamedQuery(
                name = "Favorite.getAllFavoriteofIdea",
                query = "SELECT favorite FROM Favorite favorite where favorite.ideaId.id = :ideaId"
        ),
        @NamedQuery(
                name = "Favorite.getFavoriteofUser",
                query = "SELECT favorite FROM Favorite favorite where favorite.author = :author"
        ),

        @NamedQuery(
                name = "Favorite.favoriteOnce",
                query = "SELECT favorite FROM Favorite favorite where favorite.ideaId.id = :ideaId and favorite.author = :author"
        ),
        @NamedQuery(
                name = "Favorite.findFavoriteOfIdea",
                query = "SELECT favorite FROM Favorite favorite WHERE favorite.ideaId.id = :ideaId  ORDER BY favorite.createdTime DESC"
        ),

        @NamedQuery(
                name = "Favorite.countFavoriteOfIdea",
                query = "SELECT count(c) FROM Favorite c WHERE c.ideaId.id = :ideaId "
        ),
})

public class FavoriteEntity {
    @Id
    @Column(name = "FAV_ID")
    @SequenceGenerator(name = "SEQ_IDEATION_FAV_FAV_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_FAV_FAV_ID")
    private long  id;
    @Column(name = "AUTHOR")
    private String author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;
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

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }
}
