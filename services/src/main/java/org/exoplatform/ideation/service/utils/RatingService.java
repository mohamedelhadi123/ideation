package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.IdeaImpDAO;
import org.exoplatform.ideation.dao.RatingImpDAO;
import org.exoplatform.ideation.dto.RatingDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.entities.RatingEntity;
import org.exoplatform.ideation.service.Mapper.RatingMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;


import java.util.List;

public class RatingService {
  private static final Log LOG = ExoLogger.getExoLogger(RatingEntity.class);

  private RatingImpDAO ratingdao;
  private RatingMapper ratingMapper;
  private IdeaImpDAO ideaImpDAO;
  protected IdentityManager identityManager = null;


  public RatingService(RatingImpDAO ratingdao, RatingMapper ratingMapper, IdeaImpDAO ideaImpDAO) {
    this.ratingdao = CommonsUtils.getService(RatingImpDAO.class);
    this.ratingMapper = CommonsUtils.getService(RatingMapper.class);
    this.ideaImpDAO = CommonsUtils.getService(IdeaImpDAO.class);
    identityManager = CommonsUtils.getService(IdentityManager.class);


  }

  public List<RatingDTO> getStatusByIdeaAndStatus(Long id, RatingEntity.Status status) {
    try {
      List<RatingEntity> ratings = ratingdao.getStatusByIdea(id, status);
      if (ratings != null) {
        return ratingMapper.RatingsToRatingsDTOS(ratings);
      }
    } catch (Exception e) {
      LOG.error("Error to find Status by id idea", e.getMessage());
    }
    return null;

  }

  public List<RatingDTO> getStatusByIdeaAndUser(Long id, String user) {
    try {
      List<RatingEntity> ratings = ratingdao.getStatusByIdeaAndUser(id, user);
      if (ratings != null) {
        return ratingMapper.RatingsToRatingsDTOS(ratings);
      }
    } catch (Exception e) {
      LOG.error("Error to find Status by id idea and user", e.getMessage());
    }
    return null;
  }

  @ExoTransactional
  public RatingDTO addRating(RatingDTO ratingDTO) {
    RatingEntity ratingEntity = null;
    try {
      ratingEntity = ratingdao.create(ratingMapper.RatingdtoToRating(ratingDTO));
    } catch (Exception e) {
      LOG.error("Error to create", ratingDTO.getUser(), e);
    }
    return ratingMapper.RatingToRatingDTO(ratingEntity);
  }

  @ExoTransactional
  public void deleteRating(Long id) {
    RatingEntity ratingEntity = null;
    ratingEntity = ratingdao.find(id);
    if (ratingEntity != null) {
      try {
        ratingdao.delete(ratingEntity);
      } catch (Exception e) {
        LOG.error("Error to delete rating with id {}", id, e);
      }
    }
  }

  @ExoTransactional
  public RatingDTO updateRating(RatingDTO ratingDTO) {
    try {
      String user = ConversationState.getCurrent().getIdentity().getUserId();
      RatingEntity ratingEntity = ratingdao.find(ratingDTO.getId());
      if (ratingEntity != null) {
        ratingEntity.setUser(user);
        ratingEntity.setStatus(ratingDTO.getStatus());
        ratingEntity.setIdea(ideaImpDAO.find(ratingDTO.getId_idear()));
        return ratingMapper.RatingToRatingDTO(ratingEntity);
      }


    } catch (Exception e) {
      LOG.error("Error to update with id {}", ratingDTO.getId(), e);
    }
    return null;
  }


}








