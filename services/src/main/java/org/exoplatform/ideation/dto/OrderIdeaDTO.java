package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.IdeaEntity;


import java.io.Serializable;
import java.text.SimpleDateFormat;

/*
 *
 *
 *
 * Class DTO For classification of ideas
 *
 *
 *
 *
 */
public class OrderIdeaDTO implements Serializable {

  private Long id;
  protected String title;
  private String user;
  private Long nb;

  public OrderIdeaDTO() {
  }

  public OrderIdeaDTO(IdeaEntity ideaentity, Long nb) {
    this.id = ideaentity.getId();
    this.title = ideaentity.getTitle();
    this.nb = nb;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getUser() {
    return user;
  }

  public Long getNb() {
    return nb;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public void setNb(Long nb) {
    this.nb = nb;
  }


}