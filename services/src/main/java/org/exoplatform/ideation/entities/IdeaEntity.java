package org.exoplatform.ideation.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;


import java.io.Serializable;

@Entity(name = "idea")
@ExoEntity
@Table(name = "IDEA_IDEAS")
@NamedQueries({
    @NamedQuery(name = "Idea.getIdeasByStatus", query = "select i from idea i where i.status= :status"),
    @NamedQuery(name = "Idea.getIdeaByuserAndStatus", query = "select i from idea i where i.status= :status AND i.user= :user")

})

public class IdeaEntity implements Serializable {
  public enum Status {
    PUBLISHED,
    ARCHIVED,
    DRAFT;
  }

  @Id
  @GeneratedValue
  @Column(name = "IDEA_ID")
  private Long id;
  @Column(name = "TITLE")
  private String title;
  @Column(name = "DESCRIPTION")
  private String description;
  @Enumerated(EnumType.STRING)
  private Status status;
  @Column(name = "USER")
  private String user;
  @Column(name = "CREATED_TIME")
  private Long createdTime;
  @Column(name = "UPDATED_TIME")
  private Long updatedTime;
  @Column(name = "ISPROJECT")
  private Boolean isProject;
  @Column(name="SPACE_ID")
  private String spaceID;

  @Column(name = "RESUME")
  private String resume;

  @Column(name = "EXPLANATION")
  private String explanation;

  public IdeaEntity() {

  }

  public IdeaEntity(String title, String description, Status status, String user, Long createdTime, String resume, String explanation, Boolean etat) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.user = user;
    this.createdTime = createdTime;
    this.resume = resume;
    this.explanation = explanation;
    this.isProject = etat;
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

  public Long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Long createdTime) {
    this.createdTime = createdTime;
  }

  public Long getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(Long updatedTime) {
    this.updatedTime = updatedTime;
  }

  public String getResume() {
    return resume;
  }

  public void setResume(String resume) {
    this.resume = resume;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
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
