package org.exoplatform.ideation.service;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.storage.dao.IdeaDAO;


public class IdeaService {

    protected final IdeaDAO ideaDao;

    public IdeaService(IdeaDAO ideaDao) {

        this.ideaDao = ideaDao;

    }

    public IdeaEntity createIdea (IdeaEntity ideaEntity) {

        IdeaEntity IdeaE = ideaDao.create(ideaEntity);

        return IdeaE;

    }

    public IdeaEntity updateIdea (IdeaEntity ideaEntity) {

        IdeaEntity IdeaE = ideaDao.update(ideaEntity);

        return IdeaE;

    }

    public void deleteIdea (IdeaEntity ideaEntity) {

        ideaDao.delete(ideaEntity);

    }

    public IdeaEntity findIdeaByTitle(String IdeaTitle) {

        return ideaDao.findIdeaByTitle(IdeaTitle);
    }

    public IdeaEntity findIdeaById(Long IdeaId) {

        return ideaDao.findIdeaById(IdeaId);
    }
}

