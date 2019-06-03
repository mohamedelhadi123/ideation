package org.exoplatform.ideation.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;


import java.io.Serializable;

@Entity(name = "themeidea")
@ExoEntity
@NamedQueries({
    @NamedQuery(name = "ThemeIdea.getIdThemeByIdIdea", query = "select t from themeidea t where t.ideaEntity.id=:id"),
    @NamedQuery(name = "ThemeIdea.getIdIdeaByIdTheme", query = "select t from themeidea t where t.themeEntity.id= :id"),

})
@Table(name = "IDEATION_THEME_IDEA")
public class ThemeIdeaEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_THEME_IDEA")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "ID_THEMEI")
  private ThemeEntity themeEntity;
  @ManyToOne
  @JoinColumn(name = "ID_IDEAT")
  private IdeaEntity ideaEntity;

  public ThemeIdeaEntity(ThemeEntity themeEntity, IdeaEntity ideaEntity) {
    this.themeEntity = themeEntity;
    this.ideaEntity = ideaEntity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ThemeEntity getThemeEntity() {
    return themeEntity;
  }

  public void setThemeEntity(ThemeEntity themeEntity) {
    this.themeEntity = themeEntity;
  }

  public IdeaEntity getIdeaEntity() {
    return ideaEntity;
  }

  public void setIdeaEntity(IdeaEntity ideaEntity) {
    this.ideaEntity = ideaEntity;
  }

  public ThemeIdeaEntity() {
  }
}
