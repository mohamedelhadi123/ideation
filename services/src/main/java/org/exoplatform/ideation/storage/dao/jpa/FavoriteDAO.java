package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.ideation.entities.domain.FavoriteEntity;
import org.exoplatform.ideation.entities.domain.IdeaEntity;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class FavoriteDAO  extends GenericDAOJPAImpl<FavoriteEntity, Long> {

        public FavoriteDAO() {

        }

    List<FavoriteEntity> findFavorites(long ideaId) throws PersistenceException{
        return getEntityManager().createNamedQuery("Favorite.findFavoriteOfIdea", FavoriteEntity.class)
                .setParameter("ideaId", ideaId).getResultList();


    };



        public List<FavoriteEntity>  getAllFavoriteofIdeaofuser(String author) throws PersistenceException {

            return getEntityManager().createNamedQuery("Favorite.getFavoriteofUser", FavoriteEntity.class)
                    .setParameter("author",author).getResultList();


        }
}
