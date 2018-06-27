package org.exoplatform.ideation.storage.dao.jpa;

import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class IdeaDAO extends GenericDAOJPAImpl<IdeaEntity, Long> {
    private static final Logger LOG = LoggerFactory.getLogger(IdeaDAO.class);

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

    public long getMaxId() throws PersistenceException {

        return getEntityManager().createNamedQuery("Idea.findMaxId", Long.class)
        .getSingleResult();


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

    public List<IdeaEntity> getIdea(long ideaId) throws PersistenceException {
        try {
            return getEntityManager().createNamedQuery("Idea.getIdea", IdeaEntity.class)
                    .setParameter("ideaId", ideaId)
                    .getResultList();
        }  catch (Exception e) {
            LOG.warn("Exception while attempting to get idea", e);
            throw e;
        }
    }

    public List<IdeaEntity> getPublishedIdeas(IdeaEntity.Status PUBLISHED , IdeaEntity.Status DRAFTED , String createdBy) throws PersistenceException {

       return getEntityManager().createNamedQuery("Idea.getPublishedIdeas", IdeaEntity.class).setParameter("PUBLISHED",PUBLISHED)
                .setParameter("DRAFTED",DRAFTED).setParameter("createdBy", createdBy).getResultList();



    }

    public Set<String> getCoworker(long ideaId) {
        TypedQuery<String> query = getEntityManager().createNamedQuery("Idea.getCoworker", String.class);
        query.setParameter("ideaId", ideaId);
        List<String> tags = query.getResultList();
        return new HashSet<String>(tags);
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