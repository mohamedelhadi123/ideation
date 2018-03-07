package org.exoplatform.ideation.service;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.dao.IdeaDAO;


public class IdeaService {

    protected final IdeaDAO ideaDao;

    public IdeaService(IdeaDAO IdeaDao) {

        this.ideaDao = IdeaDao;

    }

    public IdeaEntity createIdea (IdeaEntity IdeaEntity) {

        IdeaEntity IdeaE = ideaDao.create(IdeaEntity);

        return IdeaE;

    }

    public IdeaEntity updateIdea (IdeaEntity IdeaEntity) {

        IdeaEntity IdeaE = ideaDao.update(IdeaEntity);

        return IdeaE;

    }

    public void deleteIdea (IdeaEntity IdeaEntity) {

        ideaDao.delete(IdeaEntity);

    }

    public IdeaEntity findIdeaByTitle(String IdeaTitle) {

        return ideaDao.findIdeaByTitle(IdeaTitle);
    }
}
