package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.ideation.dto.ThemeIdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.entities.ThemeEntity;
import org.exoplatform.ideation.entities.ThemeIdeaEntity;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ThemeIdeaMapper {
  public ThemeIdeaMapper() {
  }

  public ThemeIdeaDTO ThemeIdeaToThemeIdeaDTO(ThemeIdeaEntity themeIdeaEntity) {
    return new ThemeIdeaDTO((themeIdeaEntity));
  }

  public List<ThemeIdeaDTO> ThemeIdeasToThemeIdeaDTO(List<ThemeIdeaEntity> themeIdeaEntities) {
    return themeIdeaEntities.stream()
        .filter(Objects::nonNull)
        .map(this::ThemeIdeaToThemeIdeaDTO)
        .collect(Collectors.toList());
  }


  public ThemeIdeaEntity ThemeIdeaDtoTOthemeIdea(ThemeIdeaDTO themeIdeaDTO) {
    try {
      if (themeIdeaDTO == null) {
        return null;
      } else {
        ThemeIdeaEntity themeIdeaEntity = new ThemeIdeaEntity();

        ThemeEntity theme = this.ThemeFromId(themeIdeaDTO.getId_themei());
        IdeaEntity idear = this.IdeaFormLongId(themeIdeaDTO.getId_ideat());
        themeIdeaEntity.setIdeaEntity(idear);
        themeIdeaEntity.setThemeEntity(theme);
        return themeIdeaEntity;
      }
    } catch (Exception pe) {
      pe.printStackTrace();
    }
    return null;
  }


  public ThemeEntity ThemeFromId(Long id) {
    ThemeEntity themeEntity = new ThemeEntity();
    themeEntity.setId(id);
    return themeEntity;
  }

  public IdeaEntity IdeaFormLongId(Long id) {
    IdeaEntity ideaEntity = new IdeaEntity();
    ideaEntity.setId(id);
    return ideaEntity;
  }

}
