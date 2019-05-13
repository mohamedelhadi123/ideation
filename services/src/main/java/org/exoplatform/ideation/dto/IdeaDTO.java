package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.IdeaEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;
/*
 *
 *
 * Class  DTO FOR IdeaEntity
 *
 *
 *
 */
public class IdeaDTO implements Serializable {
    String pattern = "yyyy-mm-dd hh:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
    private Long id;
    protected String title;
    private String createdTime;
    private String description;
    private IdeaEntity.Status status;
    private String user;
    private String explanation;
    private String resume;
    private Long id_themet;
    public IdeaDTO() {}

    public IdeaDTO(IdeaEntity ideaent) {
        this.id = ideaent.getId();
        this.title = ideaent.getTitle();
        if (ideaent.getCreatedTime() != null) {
            this.createdTime = simpleDateFormat.format(ideaent.getCreatedTime());
        }
        this.description = ideaent.getDescription();
        this.status = ideaent.getStatus();
        this.user = ideaent.getUser();
        this.resume = ideaent.getResume();
        this.explanation = ideaent.getExplanation();
        this.id_themet = ideaent.getTheme().getId();
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Long getId_themet() {
        return id_themet;
    }

    public void setId_themet(Long id_themet) {
        this.id_themet = id_themet;
    }
}