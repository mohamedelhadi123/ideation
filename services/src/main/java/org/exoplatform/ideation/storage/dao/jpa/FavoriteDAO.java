package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.ideation.entities.domain.FavoriteEntity;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class FavoriteDAO  extends GenericDAOJPAImpl<FavoriteEntity, Long> {
    private  final Logger LOG = LoggerFactory.getLogger(FavoriteDAO.class);

        public FavoriteDAO() {

        }

    List<FavoriteEntity> findFavorites(long ideaId) throws PersistenceException{
        return getEntityManager().createNamedQuery("Favorite.findFavoriteOfIdea", FavoriteEntity.class)
                .setParameter("ideaId", ideaId).getResultList();


    };



        public List<FavoriteEntity>  getAllFavorites(String author) throws PersistenceException {

            return getEntityManager().createNamedQuery("Favorite.getFavoriteofUser", FavoriteEntity.class).setParameter("author",author)
                    .getResultList();


        }

    public  long getfavsByIdeaIdCount(long id) {
        try {
            return getEntityManager().createNamedQuery("Favorite.count", Long.class)
                    .setParameter("ideaId", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOG.warn("Exception while attempting to get Favorite count.", e);
            throw e;
        }
    }



    public FavoriteEntity getFavoriteByIdeaAndUserId(String author, long ideaId ) throws PersistenceException {

            TypedQuery<FavoriteEntity> query =  getEntityManager().createNamedQuery("Favorite.getFavoriteByIdeaAndUserID", FavoriteEntity.class)
                    .setParameter("author",author).setParameter("ideaId",ideaId);


            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }


}
