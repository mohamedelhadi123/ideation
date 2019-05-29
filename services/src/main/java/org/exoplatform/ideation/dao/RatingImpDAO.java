package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.RatingEntity;

import javax.persistence.TypedQuery;


import java.util.List;

public class RatingImpDAO extends GenericDAOJPAImpl<RatingEntity, Long> {

  public List<RatingEntity> getStatusByIdea(Long id, RatingEntity.Status status) {
    TypedQuery<RatingEntity> query = getEntityManager().createNamedQuery("Rating.getStatusByIdIdea", RatingEntity.class);
    query.setParameter("id", id);
    query.setParameter("status", status);
    return query.getResultList();
  }


  public List<RatingEntity> getStatusByIdeaAndUser(Long id, String user) {
    TypedQuery<RatingEntity> query = getEntityManager().createNamedQuery("Rating.getStatusByIdIdeaAndUser", RatingEntity.class);
    query.setParameter("id", id);
    query.setParameter("user", user);
    return query.getResultList();
  }
}