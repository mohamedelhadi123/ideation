package org.exoplatform.ideation.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;


import java.io.Serializable;

@Entity(name = "theme")
@ExoEntity
@Table(name = "IDEATION_THEMES")
public class ThemeEntity implements Serializable {
  @Id
  @GeneratedValue
  @Column(name = "THEME_ID")
  private Long id;


  @Column(name = "SUBJECT")
  private String subject;

  @Column(name = "USER")
  private String user;

  public ThemeEntity() {
  }

  public ThemeEntity(String subject, String user) {
    this.subject = subject;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }
}
