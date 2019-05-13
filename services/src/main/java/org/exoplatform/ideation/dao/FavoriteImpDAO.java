package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.FavoriteEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class FavoriteImpDAO extends GenericDAOJPAImpl<FavoriteEntity,Long> {


    public List<FavoriteEntity> getFavbyuser(String user){
        TypedQuery<FavoriteEntity> query = getEntityManager().createNamedQuery("Fav.getAllFavByUser", FavoriteEntity.class);
        query.setParameter("user",user);
        return query.getResultList();

    }
    public List<FavoriteEntity> getFavByUserAndIdea(String  user ,Long id){
        TypedQuery<FavoriteEntity> query = getEntityManager().createNamedQuery("Fav.getAllFavByUserAndId", FavoriteEntity.class);
        query.setParameter("user",user);
        query.setParameter("id",id);
        return query.getResultList();

    }
    public List<FavoriteEntity> getFavByIdea(Long id){
        TypedQuery<FavoriteEntity> query = getEntityManager().createNamedQuery("Fav.getAllFavByIdea", FavoriteEntity.class);
        query.setParameter("id",id);
        return query.getResultList();

    }

    public List<FavoriteEntity> getAllOrderByFav() {
        TypedQuery<FavoriteEntity> query = getEntityManager().createNamedQuery("Fav.getAllOrderByFav", FavoriteEntity.class);
        return query.getResultList();

    }

    public Long getnumberOffav(Long id) {
        Long count = ((Long) getEntityManager().createNamedQuery("Fav.getnumberOffav").setParameter("id", id).getSingleResult()).longValue();
        return(count);
    }

}
