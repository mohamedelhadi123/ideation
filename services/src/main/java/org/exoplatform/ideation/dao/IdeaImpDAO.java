package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.IdeaEntity;

import javax.persistence.TypedQuery;
import java.util.List;


public class IdeaImpDAO extends GenericDAOJPAImpl<IdeaEntity ,Long> {



    public List<IdeaEntity> getIdeaByUser(String user){
        TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.getIdeasByUser", IdeaEntity.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

}
