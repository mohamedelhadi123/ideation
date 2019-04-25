package org.exoplatform.ideation.entities;
import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "idea")
@ExoEntity
@Table(name = "IDEA_IDEAS")
@NamedQueries({
        @NamedQuery(name = "Idea.getIdeasByUser", query = "select i from idea i where i.user= :user"),
        @NamedQuery(name = "Idea.getIdeasPublished", query = "select i from idea i where i.status= :PUBLISHED"),
        @NamedQuery(name = "Idea.getIdeasDRAFET", query = "select i from idea i where i.status= :DRAFET"),
        @NamedQuery(name = "Idea.getIdeasARCHIVED", query = "select i from idea i where i.status= :ARCHIVED"),
        @NamedQuery(name="Idea.getIdeaPublishedByuser",query = "select i from idea i where i.status= :PUBLISHED AND i.user= :user")



})

public class IdeaEntity implements Serializable {
    public enum Status {
        PUBLISHED,
        ARCHIVED,
        DRAFET;
        



    }
    @Id
    @GeneratedValue
    @Column(name = "IDEA_ID")
    private Long  id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "USER")
    private String user;
    @Column(name = "CREATED_TIME")
    private Date createdTime;


 public IdeaEntity(){

 }
    public IdeaEntity(long id, String title, String description, Status status, String user, Date createdTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
        this.createdTime = createdTime;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


}
