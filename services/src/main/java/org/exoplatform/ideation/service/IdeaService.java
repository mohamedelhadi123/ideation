package org.exoplatform.ideation.service;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.dao.IdeaDAO;


public class IdeaService {

    protected final IdeaDAO IdeaDao;

    public IdeaService(IdeaDAO IdeaDao) {

        this.IdeaDao = IdeaDao;

    }

    public IdeaEntity createIdea (IdeaEntity IdeaEntity) {

        IdeaEntity IdeaE = IdeaDao.create(IdeaEntity);

        return IdeaE;

    }

    public IdeaEntity updateIdea (IdeaEntity IdeaEntity) {

        IdeaEntity IdeaE = IdeaDao.update(IdeaEntity);

        return IdeaE;

    }

    public void deleteIdea (IdeaEntity IdeaEntity) {

        IdeaDao.delete(IdeaEntity);

    }

    public IdeaEntity findIdeaByTitle(String IdeaTitle) {

        return IdeaDao.findIdeaByTitle(IdeaTitle);
    }
}
