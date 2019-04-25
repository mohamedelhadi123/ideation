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
    public List<IdeaEntity> getPublishedIdeas(IdeaEntity.Status PUBLISHED){
        TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.getIdeasPublished", IdeaEntity.class);
        query.setParameter("PUBLISHED",PUBLISHED);
        return query.getResultList();



    }

    public List<IdeaEntity> getDRAFETIdeas(IdeaEntity.Status DRAFET){
        TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.getIdeasDRAFET", IdeaEntity.class);
        query.setParameter("DRAFET",DRAFET);
        return query.getResultList();


    }

    public List<IdeaEntity> getARCHIVEDIdeas(IdeaEntity.Status ARCHIVED){
        TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.getIdeasARCHIVED", IdeaEntity.class);
        query.setParameter("ARCHIVED",ARCHIVED);
        return query.getResultList();



    }

    public List<IdeaEntity> getPublishedIdeaByUser(IdeaEntity.Status PUBLISHED,String user){
        TypedQuery<IdeaEntity> query=getEntityManager().createNamedQuery("Idea.getIdeaPublishedByuser",IdeaEntity.class);
        query.setParameter("PUBLISHED", PUBLISHED);
        query.setParameter("user", user);
        return query.getResultList();
    }
}
