package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.IdeaImpDAO;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.service.Mapper.IdeaMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;


import java.text.SimpleDateFormat;
import java.util.List;

public class IdeaService {
  private IdeaImpDAO ideaDao;
  private IdeaMapper ideaMapper;
  private SpaceService spaceService;
  private static final Log LOG = ExoLogger.getExoLogger(IdeaEntity.class);
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  protected IdentityManager identityManager = null;

  public IdeaService(IdeaImpDAO ideaDao, IdeaMapper ideaMapper) {
    this.ideaMapper = CommonsUtils.getService(IdeaMapper.class);
    identityManager = CommonsUtils.getService(IdentityManager.class);
    this.ideaDao = CommonsUtils.getService(IdeaImpDAO.class);
    spaceService = CommonsUtils.getService(SpaceService.class);

  }

  public List<IdeaDTO> getIdeaByStatus(IdeaEntity.Status status) {
    try {
      // --- load all Rules
      List<IdeaEntity> ideas = ideaDao.getIdeasByStatus(status);
      if (ideas != null) {

        return ideaMapper.IdeasToIdeaDTOs(ideas);
      }

    } catch (Exception e) {
      LOG.error("Error to find Idea ", e.getMessage());
    }
    return null;

  }

  public List<IdeaDTO> getIdeaByUserAndStatus(IdeaEntity.Status status, String user) {
    try {
      List<IdeaEntity> ideas = ideaDao.getIdeaByUserAndStatus(status, user);
      if (ideas != null) {
        return ideaMapper.IdeasToIdeaDTOs(ideas);
      }
    } catch (Exception e) {
      LOG.error("Error to find Idea published by user", e.getMessage());
    }
    return null;
  }

  public IdeaDTO getIdea(Long id) {
    try {
      IdeaEntity idea = ideaDao.find(id);
      if (idea != null) {
        return ideaMapper.ideaTOideaDTO(idea);
      }
    } catch (Exception e) {
      LOG.error("Error to find Idea published by user", e.getMessage());
    }
    return null;
  }


  @ExoTransactional
  public IdeaDTO addIdea(IdeaDTO ideaDTO) {
    IdeaEntity ideaEntity = null;
    try {
      ideaEntity = ideaDao.create(ideaMapper.dtoToIdea(ideaDTO));
    } catch (Exception e) {
      LOG.error("Error to create badge with title ", ideaDTO.getUser(), e);
    }
    return ideaMapper.ideaTOideaDTO(ideaEntity);

  }

  @ExoTransactional

  public void deleteIdea(Long id) {
    IdeaEntity idea = null;
    idea = ideaDao.find(id);
    if (idea != null) {

      try {

        ideaDao.delete(idea);

      } catch (Exception e) {
        LOG.error("Error to delete idea with id {}", id, e);
      }
    }
  }

  @ExoTransactional
  public IdeaDTO updateIdea(IdeaDTO ideaDTO) {

    try {
      String user = ConversationState.getCurrent().getIdentity().getUserId();

      IdeaEntity ideaEntity = ideaDao.find(ideaDTO.getId());
      if (ideaEntity != null) {
        ideaEntity.setDescription(ideaDTO.getDescription());
        ideaEntity.setStatus(ideaDTO.getStatus());
        ideaEntity.setTitle(ideaDTO.getTitle());
        ideaEntity.setUser(user);
        ideaEntity.setResume(ideaDTO.getResume());
        ideaEntity.setExplanation(ideaDTO.getExplanation());
        ideaEntity.setUpdatedTime(System.currentTimeMillis());
        ideaEntity.setIsProject(ideaDTO.getIsProject());
        ideaDao.update(ideaEntity);
        return ideaMapper.ideaTOideaDTO(ideaEntity);
      }
    } catch (Exception e) {
      LOG.error("Error to update with id {}", ideaDTO.getId(), e);
    }

    return null;
  }


  public void createSpace(Long ideaID, String creator) throws Exception {
    IdeaDTO idea = ideaMapper.ideaTOideaDTO(ideaDao.find(ideaID));
    String[] managers = {creator};
    String[] members = {creator};
    Space space = new Space();
    space.setDisplayName("Projet " + idea.getTitle());
    space.setPrettyName("IDEATION-" + idea.getId());
    space.setDescription("Espace créé à partit de l'idée " + ideaID + "pour le projet de " + idea.getTitle());
    space.setManagers(managers);
    space.setMembers(members);
    space.setRegistration(Space.VALIDATION);
    //TODO choose which template to be used for projects
    //space.setTemplate("classic");
    space.setVisibility(Space.PRIVATE);
    space = spaceService.createSpace(space, creator);
    idea.setSpaceID(space.getId());
    ideaDao.update(ideaMapper.dtoToIdea(idea));
  }
  
  public String GetUrlSpace(String spaceID) {
    Space s = spaceService.getSpaceById(spaceID);
    return "/portal/g/:spaces" + s.getGroupId() + "/" + s.getPrettyName();
  }
}
