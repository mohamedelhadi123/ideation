package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.ThemeIdeaEntity;

import javax.persistence.TypedQuery;


import java.util.List;

public class ThemeIdeaImpDAO extends GenericDAOJPAImpl<ThemeIdeaEntity, Long> {
  public List<ThemeIdeaEntity> getThemebyIdIdea(Long id) {
    TypedQuery<ThemeIdeaEntity> query = getEntityManager().createNamedQuery("ThemeIdea.getIdThemeByIdIdea", ThemeIdeaEntity.class);
    query.setParameter("id", id);
    return query.getResultList();

  }

  public List<ThemeIdeaEntity> getIdeaByIdTheme(Long id) {
    TypedQuery<ThemeIdeaEntity> query = getEntityManager().createNamedQuery("ThemeIdea.getIdIdeaByIdTheme", ThemeIdeaEntity.class);
    query.setParameter("id", id);
    return query.getResultList();

  }
}
