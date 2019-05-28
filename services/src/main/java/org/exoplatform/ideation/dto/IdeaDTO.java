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
  String pattern = "yyyy-mm-dd hh:mm";
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
  private Long id;
  protected String title;
  private long createdTime;
  private long updateTime;
  private String description;
  private IdeaEntity.Status status;
  private String user;
  private String explanation;
  private String resume;
  private Boolean isProject;
  private String spaceID;

  public IdeaDTO() {
  }

  public IdeaDTO(IdeaEntity ideaEntity) {
    this.id = ideaEntity.getId();
    this.title = ideaEntity.getTitle();
    this.createdTime = ideaEntity.getCreatedTime();
    this.description = ideaEntity.getDescription();
    this.status = ideaEntity.getStatus();
    this.user = ideaEntity.getUser();
    this.resume = ideaEntity.getResume();
    this.explanation = ideaEntity.getExplanation();
    this.isProject = ideaEntity.getIsProject();
  }

  public Boolean getIsProject() {
    return isProject;
  }

  public void setIsProject(Boolean isProject) {
    this.isProject = isProject;
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

  public Long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Long createdTime) {
    this.createdTime = createdTime;
  }

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
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

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }

  public Boolean getProject() {
    return isProject;
  }

  public void setProject(Boolean project) {
    isProject = project;
  }

  public String getSpaceID() {
    return spaceID;
  }

  public void setSpaceID(String spaceID) {
    this.spaceID = spaceID;
  }
}