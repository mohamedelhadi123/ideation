package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;


public class IdeaDAO extends GenericDAOJPAImpl<IdeaEntity, Long> {

    public IdeaDAO() {

    }

    public IdeaEntity findIdeaByTitle(String IdeaTitle) throws PersistenceException {

        TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.findIdeaByTitle", IdeaEntity.class)
                .setParameter("ideaTitle", IdeaTitle);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public IdeaEntity findIdeaById(Long ideaId) throws PersistenceException {

        TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.findIdeaById", IdeaEntity.class)
                .setParameter("ideaId", ideaId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }



    public List<IdeaEntity> getPublishedIdeas(IdeaEntity.Status PUBLISHED , IdeaEntity.Status DRAFTED , String createdBy) throws PersistenceException {

       return getEntityManager().createNamedQuery("Idea.getPublishedIdeas", IdeaEntity.class).setParameter("PUBLISHED",PUBLISHED)
                .setParameter("DRAFTED",DRAFTED).setParameter("createdBy", createdBy).getResultList();



    }

    public List<IdeaEntity> getAllIdeas() throws PersistenceException {

        TypedQuery<IdeaEntity> query = getEntityManager().createNamedQuery("Idea.getAllIdeas", IdeaEntity.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }




    public List<IdeaEntity> getDraftIdeas(IdeaEntity.Status DRAFTED , String createdBy) throws PersistenceException {
        return getEntityManager().createNamedQuery("Idea.getDraftIdeas", IdeaEntity.class).setParameter("DRAFTED",DRAFTED)
                .setParameter("createdBy", createdBy).getResultList();

    }


    public int deleteIdeaById(int ideaId) throws PersistenceException {
        return getEntityManager().createNamedQuery("Idea.deleteIdeaById",Long.class)
                .setParameter("ideaId", ideaId)
                .executeUpdate();

    }

    public int deleteIdeaByTitle(int ideaTitle) throws PersistenceException {
        return getEntityManager().createNamedQuery("Idea.deleteIdeaByTitle")
                .setParameter("ideaTitle", ideaTitle)
                .executeUpdate();

    }

}