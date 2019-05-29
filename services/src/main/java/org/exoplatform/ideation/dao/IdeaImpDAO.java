package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.IdeaEntity;

import javax.persistence.TypedQuery;


import java.util.List;


public class IdeaImpDAO extends GenericDAOJPAImpl<IdeaEntity, Long> {
  public List<IdeaEntity> getIdeasByStatus(IdeaEntity.Status status) {
    TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.getIdeasByStatus", IdeaEntity.class);
    query.setParameter("status", status);
    return query.getResultList();


  }


  public List<IdeaEntity> getIdeaByUserAndStatus(IdeaEntity.Status status, String user) {
    TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.getIdeaByuserAndStatus", IdeaEntity.class);
    query.setParameter("status", status);
    query.setParameter("user", user);
    return query.getResultList();
  }

}