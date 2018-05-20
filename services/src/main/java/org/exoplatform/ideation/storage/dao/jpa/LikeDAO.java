package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.domain.LikeEntity;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class LikeDAO extends GenericDAOJPAImpl<LikeEntity, Long> {
    private  final Logger LOG = LoggerFactory.getLogger(LikeDAO.class);

    public LikeDAO() {

    }

    public List<LikeEntity> getAllLikes() throws PersistenceException {
        TypedQuery<LikeEntity> query = getEntityManager().createNamedQuery("Like.getAllLikes", LikeEntity.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    public LikeEntity getLikeByIdeaAndUserId(String author,long ideaId) throws PersistenceException {

        TypedQuery<LikeEntity> query =  getEntityManager().createNamedQuery("Like.getLikeByIdeaAndUserID", LikeEntity.class)
                .setParameter("author",author).setParameter("ideaId",ideaId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public  long getLikesByIdeaIdCount(long id) {
        try {
            return getEntityManager().createNamedQuery("Like.count", Long.class)
                    .setParameter("ideaId", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOG.warn("Exception while attempting to get Likes count.", e);
            throw e;
        }
    }

}
