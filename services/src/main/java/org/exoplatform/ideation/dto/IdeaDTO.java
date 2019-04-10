package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.IdeaEntity;

import java.io.Serializable;
import java.util.Date;

public class IdeaDTO implements Serializable {
    public IdeaDTO() {
    }

    public IdeaDTO(IdeaEntity ideaent) {
        this.id = ideaent.getId();
        this.title = ideaent.getTitle();
        this.createdTime = ideaent.getCreatedTime();
        this.description = ideaent.getDescription();
        this.status = ideaent.getStatus();
        this.USER = ideaent.getUSER();
    }

    private Long id;
    protected String title;
    private Date createdTime;
    private String description;
    private IdeaEntity.Status status;
    private String USER;

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IdeaEntity.Status getStatus() {
        return status;
    }

    public void setStatus(IdeaEntity.Status status) {
        this.status = status;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }
}
