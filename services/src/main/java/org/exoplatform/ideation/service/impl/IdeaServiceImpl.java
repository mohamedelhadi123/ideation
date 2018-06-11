package org.exoplatform.ideation.service.impl;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.storage.dao.jpa.IdeaDAO;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;

import java.util.*;


public class IdeaServiceImpl implements IdeaService {

    private  String currentUser = ConversationState.getCurrent().getIdentity().getUserId();

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


    public IdeaEntity findIdeaByTitle(String IdeaTitle) {

        return ideaDao.findIdeaByTitle(IdeaTitle);
    }


    public void delete(IdeaDTO entity) {
        if (entity == null) {
            throw new IllegalStateException("Parameter 'entity' = + "+entity+ " or 'entity.id' is null");
        }
        ideaDao.delete(convert(entity));
    }

    public Set<String> getCoworker(long ideaId) {
        return ideaDao.getCoworker(ideaId);
    }


    public IdeaDTO save(IdeaDTO entity, boolean newIde) {
        entity.setCreatedTime(new Date());

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

    public List<IdeaDTO> getUserIdeas(String createdBy) {
        List<IdeaEntity> entities = ideaDao.getPublishedIdeas(IdeaEntity.Status.PUBLISHED , IdeaEntity.Status.DRAFTED ,createdBy);
        List<IdeaDTO> dtos = new ArrayList<IdeaDTO>();
        for (IdeaEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }


    public List<IdeaDTO> getPublishedIdeas(String createdBy) {
        List<IdeaEntity> entities = ideaDao.getPublishedIdeas(IdeaEntity.Status.PUBLISHED , IdeaEntity.Status.DRAFTED ,createdBy);
        List<IdeaDTO> dtos = new ArrayList<IdeaDTO>();
        for (IdeaEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }


    public IdeaDTO getIdea(long ideaId){
        return convert(ideaDao.getIdea(ideaId));

    }

    public List<IdeaDTO> getAllIdeas() {
        List<IdeaEntity> entities = ideaDao.getAllIdeas();
        List<IdeaDTO> dtos = new ArrayList<IdeaDTO>();
        for (IdeaEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }


    public List<IdeaDTO> getDraftIdeas(String createdBy) {
            List<IdeaEntity> entities = ideaDao.getDraftIdeas(IdeaEntity.Status.DRAFTED,createdBy);
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
        entity.setCreatedTime(dto.getCreatedTime());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private IdeaDTO convert(IdeaEntity entity) {
        IdeaDTO dto = new IdeaDTO(entity);
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedTime(entity.getCreatedTime());
        dto.setStatus(entity.getStatus());
        return dto;
    }

}