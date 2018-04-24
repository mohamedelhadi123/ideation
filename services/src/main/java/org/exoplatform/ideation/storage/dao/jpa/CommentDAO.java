package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.domain.CommentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CommentDAO extends GenericDAOJPAImpl<CommentEntity, String> {
    private static final Logger LOG = LoggerFactory.getLogger(CommentDAO.class);

    public List<CommentEntity> getCommentsByIdeaId(Long id,  int offset, int limit) {
        try {
            if (offset >= 0 && limit > 0) {
                return getEntityManager().createNamedQuery("commentEntity.findByIdeaId", CommentEntity.class)
                        .setParameter("ideaId", id)
                        .setFirstResult(offset)
                        .setMaxResults(limit)
                        .getResultList();
            } else {
                return getEntityManager().createNamedQuery("commentEntity.findByIdeaId", CommentEntity.class)
                        .setParameter("ideaId", id)
                        .getResultList();
            }
        } catch (Exception e) {
            LOG.warn("Exception while attempting to get comments with offset = '" + offset + "' and limit = '" + limit + "'.", e);
            throw e;
        }
    }

    public  Long getCommentsByIdeaIdCount(Long id) {
        try {
            return getEntityManager().createNamedQuery("commentEntity.count", Long.class)
                    .setParameter("ideaId", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOG.warn("Exception while attempting to get comments count.", e);
            throw e;
        }
    }



}