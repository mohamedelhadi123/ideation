package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.ThemeImpDAO;
import org.exoplatform.ideation.dto.ThemeDTO;
import org.exoplatform.ideation.entities.ThemeEntity;
import org.exoplatform.ideation.service.Mapper.ThemeMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


import java.util.List;

public class ThemeService {
  private static final Log LOG = ExoLogger.getExoLogger(ThemeEntity.class);

  private ThemeImpDAO themeImpDAO;
  private ThemeMapper themeMapper;

  public ThemeService(ThemeImpDAO themeImpDAO, ThemeMapper themeMapper) {
    this.themeImpDAO = CommonsUtils.getService(ThemeImpDAO.class);
    this.themeMapper = CommonsUtils.getService(ThemeMapper.class);
  }

  public List<ThemeDTO> getallTheme() {
    try {
      List<ThemeEntity> themes = themeImpDAO.findAll();
      if (themes != null) {
        return themeMapper.ThemesToThemesDTO(themes);
      }
    } catch (Exception e) {
      LOG.error("Error to find theme", e.getMessage());
    }
    return null;
  }

  @ExoTransactional

  public ThemeDTO addTheme(ThemeDTO themeDTO) {
    ThemeEntity theme = null;
    try {
      theme = themeImpDAO.create(themeMapper.ThemedtoToTheme(themeDTO));
    } catch (Exception e) {
      LOG.error("Error to create theme  ", e);
    }
    return themeMapper.ThemeToThemeDTO(theme);
  }

  public ThemeDTO getThemeById(Long id) {
    try {
      ThemeEntity theme = themeImpDAO.find(id);
      if (theme != null) {
        return themeMapper.ThemeToThemeDTO(theme);
      }
    } catch (Exception e) {
      LOG.error("Error to find Theme by Id", e.getMessage());
    }
    return null;
  }


}
