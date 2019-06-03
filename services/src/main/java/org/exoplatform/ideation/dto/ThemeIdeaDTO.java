package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.ThemeIdeaEntity;

public class ThemeIdeaDTO {
  private Long id;
  private Long id_themei;
  private Long id_ideat;

  public ThemeIdeaDTO(ThemeIdeaEntity themeIdea) {
    this.id = themeIdea.getId();
    this.id_themei = themeIdea.getThemeEntity().getId();
    this.id_ideat = themeIdea.getIdeaEntity().getId();
  }

  public ThemeIdeaDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId_themei() {
    return id_themei;
  }

  public void setId_themei(Long id_themei) {
    this.id_themei = id_themei;
  }

  public Long getId_ideat() {
    return id_ideat;
  }

  public void setId_ideat(Long id_ideat) {
    this.id_ideat = id_ideat;
  }
}
