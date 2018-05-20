package org.exoplatform.ideation.entities.domain;


import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Like")
@ExoEntity
@Table(name = "IDEATION_LIKE")
@NamedQueries({
        @NamedQuery(
                name = "Like.getAllLikes",
                query = "SELECT l FROM Like l "
        ),
        @NamedQuery(
                name = "Like.count",
                query = "SELECT count(l) FROM Like l where l.ideaId = :ideaId"
        ),
        @NamedQuery(
                name = "Like.getLikeByIdeaAndUserID",
                query = "SELECT l FROM Like l WHERE l.ideaId = :ideaId and l.author = :author"
        ),


})



public class LikeEntity {

    @Id
    @Column(name = "LIKE_ID")
    @SequenceGenerator(name = "SEQ_IDEATION_Like_Like_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_Like_Like_ID")
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
