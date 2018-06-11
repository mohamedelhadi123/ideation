package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.domain.RatingEntity;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class RateDAO  extends GenericDAOJPAImpl<RatingEntity, Long> {
    private final Logger LOG = LoggerFactory.getLogger(RateDAO.class);

    public RateDAO() {

    }




    public  long getrateByIdeaIdCount(long id) {
        try {
            return getEntityManager().createNamedQuery("Rate.Count", Long.class)
                    .setParameter("ideaId", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOG.warn("Exception while attempting to get Rate count.", e);
            throw e;
        }
    }

    public RatingEntity getRateByIdeaAndUser(long ideaId , String author) throws PersistenceException {

        TypedQuery<RatingEntity> query =  getEntityManager().createNamedQuery("Rate.getRateByIdeaAndUser", RatingEntity.class)
                .setParameter("ideaId" , ideaId).setParameter("author",author);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<RatingEntity> getRateByIdea() throws PersistenceException {

        TypedQuery<RatingEntity> query =  getEntityManager().createNamedQuery("Rate.getRateByIdea", RatingEntity.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
