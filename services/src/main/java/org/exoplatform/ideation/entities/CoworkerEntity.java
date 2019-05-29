package org.exoplatform.ideation.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;


import java.io.Serializable;

@Entity
@ExoEntity
@Table(name = "IDEATION_COWORKERS")
public class CoworkerEntity implements Serializable {

  @Id
  @Column(name = "CWK_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "COWORKER")
  private String coworker;

  @ManyToOne
  @JoinColumn(name = "ID_IDEACE")
  private IdeaEntity idea;

  public CoworkerEntity(String coworker, IdeaEntity idea) {
    this.coworker = coworker;
    this.idea = idea;
  }


  public CoworkerEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCoworker() {
    return coworker;
  }

  public void setCoworker(String coworker) {
    this.coworker = coworker;
  }

  public IdeaEntity getIdea() {
    return idea;
  }

  public void setIdea(IdeaEntity idea) {
    this.idea = idea;
  }
}
