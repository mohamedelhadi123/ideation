package org.exoplatform.ideation.entities.domain;


import org.exoplatform.commons.api.persistence.ExoEntity;
import java.util.*;
import javax.persistence.*;

@Entity(name = "Favorite")
@ExoEntity
@Table(name = "IDEATION_FAV")
@NamedQueries({
        @NamedQuery(
                name = "Favorite.getAllFavoriteofIdea",
                query = "SELECT favorite FROM Favorite favorite where favorite.ideaId = :ideaId"
        ),
        @NamedQuery(
                name = "Favorite.getFavoriteofUser",
                query = "SELECT favorite FROM Favorite favorite WHERE  favorite.author = :author"
        ),

        @NamedQuery(
                name = "Favorite.count",
                query = "SELECT count(favorite) FROM Favorite favorite WHERE  favorite.ideaId = :ideaId"
        ),

        @NamedQuery(
                name = "Favorite.getFavoriteByIdeaAndUserID",
                query = "SELECT favorite FROM Favorite favorite WHERE favorite.ideaId = :ideaId and favorite.author = :author"
        ),



        @NamedQuery(
                name = "Favorite.countFavoriteOfIdea",
                query = "SELECT count(c) FROM Favorite c WHERE c.ideaId = :ideaId "
        ),
})

public class FavoriteEntity {
    @Id
    @Column(name = "FAV_ID")
    @SequenceGenerator(name = "SEQ_IDEATION_FAV_FAV_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_FAV_FAV_ID")
    private long  id;
    @Column(name = "ISFAV")
    private boolean isfav;
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

    public boolean getFav() {
        return isfav;
    }

    public void setFav(boolean isfav) {
        this.isfav = isfav;
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
