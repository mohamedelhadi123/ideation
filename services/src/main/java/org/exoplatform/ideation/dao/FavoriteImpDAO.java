package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.FavoriteEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class FavoriteImpDAO extends GenericDAOJPAImpl<FavoriteEntity,Long> {


    public List<FavoriteEntity> getFav(String user){
        TypedQuery<FavoriteEntity> query = getEntityManager().createNamedQuery("Fav.getAllFavByUser", FavoriteEntity.class);
        query.setParameter("user",user);
        return query.getResultList();

    }
    public List<FavoriteEntity> getFavByUserAndId(String  user ,Long id){
        TypedQuery<FavoriteEntity> query = getEntityManager().createNamedQuery("Fav.getAllFavByUserAndId", FavoriteEntity.class);
       query.setParameter("user",user);
        query.setParameter("id",id);
        return query.getResultList();

    }
    public List<FavoriteEntity> getFavById(Long id){
        TypedQuery<FavoriteEntity> query = getEntityManager().createNamedQuery("Fav.getAllFavById", FavoriteEntity.class);
        query.setParameter("id",id);
        return query.getResultList();

    }
}
