package org.exoplatform.ideation.service.impl;

import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.storage.dao.jpa.IdeaDAO;


import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class IdeaServiceImpl implements IdeaService {
    private static final Log LOG = ExoLogger.getExoLogger(IdeaServiceImpl.class);



    private IdeaDAO ideaDao = new IdeaDAO();

    public IdeaEntity createIdea(IdeaEntity ideaEntity) {

        IdeaEntity IdeaE = ideaDao.create(ideaEntity);

        return IdeaE;

    }

    public IdeaEntity updateIdea(IdeaEntity ideaEntity) {

        IdeaEntity IdeaE = ideaDao.update(ideaEntity);

        return IdeaE;

    }

    public void deleteIdea(IdeaEntity ideaEntity) {

        ideaDao.delete(ideaEntity);

    }

    public IdeaEntity findIdeaByTitle(String IdeaTitle) {

        return ideaDao.findIdeaByTitle(IdeaTitle);
    }

    public IdeaDTO save(IdeaDTO entity, boolean newIde) {
        if (entity == null) {
            throw new IllegalStateException("Parameter 'entity' is null");
        }

        IdeaEntity ideaEntity = null;
        if (newIde) {
            ideaEntity = ideaDao.create(convert(entity));
        } else {
            ideaEntity = ideaDao.update(convert(entity));
        }
        return convert(ideaEntity);
    }

    public List<IdeaDTO> getAllIdeas() {
        List<IdeaEntity> entities = ideaDao.getAllIdeas();
        List<IdeaDTO> dtos = new ArrayList<IdeaDTO>();
        for (IdeaEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }


    public IdeaEntity findIdeaById(Long IdeaId) {

        return ideaDao.findIdeaById(IdeaId);
    }


    private IdeaEntity convert(IdeaDTO dto) {
        IdeaEntity entity = new IdeaEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    private IdeaDTO convert(IdeaEntity entity) {
        IdeaDTO dto = new IdeaDTO(entity);
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        return dto;
    }

}