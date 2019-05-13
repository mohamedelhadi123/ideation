package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.RatingDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.entities.RatingEntity;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RatingMapper {
    protected IdentityManager identityManager = null;

    public RatingMapper() {
        identityManager = CommonsUtils.getService(IdentityManager.class);

    }

    public RatingDTO RatingToRatingDTO(RatingEntity ratingEntity){
        return new RatingDTO(ratingEntity);
    }




    public List<RatingDTO> RatingsToRatingsDTOS(List<RatingEntity> ratings){
        return ratings.stream()
                .filter(Objects::nonNull)
                .map(this::RatingToRatingDTO)
                .collect(Collectors.toList());
    }





    public RatingEntity RatingdtoToRating(RatingDTO ratingDTO){
        try {
            if(ratingDTO == null){
                return null;
            }
            else {
                String user = ConversationState.getCurrent().getIdentity().getUserId();

                RatingEntity ratingEntity=new RatingEntity();
                ratingEntity.setStatus(ratingDTO.getStatus());
                ratingEntity.setUser(user);
                IdeaEntity idear=this.IdeaFormLongId(ratingDTO.getId_idear());
                ratingEntity.setIdea(idear);
                return ratingEntity;
            }
        } catch (Exception pe) {
            pe.printStackTrace();
        }
        return null;
    }



    public IdeaEntity IdeaFormLongId(Long id){
        IdeaEntity ideaEntity=new IdeaEntity();
        ideaEntity.setId(id);
        return ideaEntity;
    }



}
