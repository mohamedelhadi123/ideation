package org.exoplatform.ideation.dao;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.IdeaEntity;

import java.util.List;


public class IdeaImp extends GenericDAOJPAImpl<IdeaEntity ,Long> {

    public List<IdeaEntity> getPublishedIdeas(IdeaEntity.Status PUBLISHED ) {

        return getEntityManager().createNamedQuery("IdeaDTO.getPublishedIdeas", IdeaEntity.class).setParameter("PUBLISHED",PUBLISHED).getResultList();

    }

}
