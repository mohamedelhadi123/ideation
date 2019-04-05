package org.exoplatform.ideation.entities;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity(name = "Idea")
@ExoEntity
@Table(name = "IDEA_IDEAS")
@NamedQueries({

        @NamedQuery(
                name = "Idea.getPublishedIdeas",
                query = "SELECT idea FROM Idea idea where idea.status = :PUBLISHED"
        )
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
    private String USER;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

   
 public IdeaEntity(){
     
 }
    public IdeaEntity(long id, String title, String description, Status status, String USER, Date createdTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.USER = USER;
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

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

  
}
