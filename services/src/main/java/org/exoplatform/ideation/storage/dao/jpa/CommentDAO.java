package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.domain.CommentEntity;
import org.exoplatform.ideation.entities.domain.FavoriteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;
import java.util.List;


public class CommentDAO extends GenericDAOJPAImpl<CommentEntity, String> {
    private static final Logger LOG = LoggerFactory.getLogger(CommentDAO.class);

    public List<CommentEntity>  getAllComments() throws PersistenceException {

        return getEntityManager().createNamedQuery("Comment.getAllComments", CommentEntity.class).getResultList();


    }

    public List<CommentEntity>  getIdeaComments(long ideaId) throws PersistenceException {

        return getEntityManager().createNamedQuery("Comment.findByIdeaId", CommentEntity.class).setParameter("ideaId",ideaId).getResultList();


    }

   public  long getCommentsByIdeaIdCount(long id) {
        try {
            return getEntityManager().createNamedQuery("Comment.count", Long.class)
                    .setParameter("ideaId", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOG.warn("Exception while attempting to get comments count.", e);
            throw e;
        }
    }



}