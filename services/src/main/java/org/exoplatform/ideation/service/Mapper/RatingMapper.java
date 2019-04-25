package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.ideation.dto.RatingDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.entities.RatingEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RatingMapper {
    public RatingMapper() {
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
            if(ratingDTO==null){
                return null;
            }
            else {
                RatingEntity ratingEntity=new RatingEntity();
                ratingEntity.setStatus(ratingDTO.getStatus());
                ratingEntity.setUser(ratingDTO.getUser());
                IdeaEntity idea=this.IdeaFromId(ratingDTO.getId_idea());
                ratingEntity.setIdea(idea);
                return ratingEntity;
            }
        } catch (Exception pe) {
            pe.printStackTrace();
        }
    return null;
}




public IdeaEntity IdeaFromId(Long id){
        IdeaEntity ideaEntity=new IdeaEntity();
        ideaEntity.setId(id);
        return ideaEntity;
}


}
