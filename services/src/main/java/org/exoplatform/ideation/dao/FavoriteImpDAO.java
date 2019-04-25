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
}
