package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.ThemeIdeaImpDAO;
import org.exoplatform.ideation.dto.ThemeIdeaDTO;
import org.exoplatform.ideation.entities.ThemeIdeaEntity;
import org.exoplatform.ideation.service.Mapper.ThemeIdeaMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


import java.util.List;

public class ThemeIdeaService {
  private static final Log LOG = ExoLogger.getExoLogger(ThemeIdeaEntity.class);
  private ThemeIdeaImpDAO themeIdeaImpDAO;
  private ThemeIdeaMapper themeIdeaMapper;

  public ThemeIdeaService() {
  }

  public ThemeIdeaService(ThemeIdeaImpDAO themeIdeaImpDAO, ThemeIdeaMapper themeIdeaMapper) {
    this.themeIdeaImpDAO = CommonsUtils.getService(ThemeIdeaImpDAO.class);
    this.themeIdeaMapper = CommonsUtils.getService(ThemeIdeaMapper.class);
  }

  public List<ThemeIdeaDTO> GetAllTheme() {
    try {
      List<ThemeIdeaEntity> themesi = themeIdeaImpDAO.findAll();
      if (themesi != null) {
        return themeIdeaMapper.ThemeIdeasToThemeIdeaDTO(themesi);
      }
    } catch (Exception e) {
      LOG.error("Error to find theme Idea", e.getMessage());
    }
    return null;
  }


  public List<ThemeIdeaDTO> GetAllThemeByIdIdea(Long id) {
    try {
      List<ThemeIdeaEntity> themesi = themeIdeaImpDAO.getThemebyIdIdea(id);
      if (themesi != null) {
        return themeIdeaMapper.ThemeIdeasToThemeIdeaDTO(themesi);
      }
    } catch (Exception e) {
      LOG.error("Error to find theme by Idea", e.getMessage());
    }
    return null;
  }


  public List<ThemeIdeaDTO> GetAllIdeaByTheme(Long id) {
    try {
      List<ThemeIdeaEntity> themesi = themeIdeaImpDAO.getIdeaByIdTheme(id);
      if (themesi != null) {
        return themeIdeaMapper.ThemeIdeasToThemeIdeaDTO(themesi);
      }
    } catch (Exception e) {
      LOG.error("Error to find   Idea by theme", e.getMessage());
    }
    return null;
  }


  @ExoTransactional
  public ThemeIdeaDTO AddThemeIdea(ThemeIdeaDTO themeIdeaDTO) {
    ThemeIdeaEntity themeIdeaEntity = null;
    try {
      themeIdeaEntity = themeIdeaImpDAO.create(themeIdeaMapper.ThemeIdeaDtoTOthemeIdea(themeIdeaDTO));
    } catch (Exception e) {
      LOG.error("Error to create theme  ", e);
    }
    return themeIdeaMapper.ThemeIdeaToThemeIdeaDTO(themeIdeaEntity);
  }

  public void deleteThemeIdea(Long id) {
    ThemeIdeaEntity themeIdeaEntity = null;
    themeIdeaEntity = themeIdeaImpDAO.find(id);
    if (themeIdeaEntity != null) {
      try {
        themeIdeaImpDAO.delete(themeIdeaEntity);
      } catch (Exception e) {
        LOG.error("error to delete ");
      }
    }

  }

}


