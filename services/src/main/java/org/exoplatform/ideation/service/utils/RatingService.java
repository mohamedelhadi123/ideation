package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.RatingImpDAO;
import org.exoplatform.ideation.dto.RatingDTO;
import org.exoplatform.ideation.entities.RatingEntity;
import org.exoplatform.ideation.service.Mapper.RatingMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

public class RatingService {
    private static final Log LOG = ExoLogger.getExoLogger(RatingEntity.class);

    private RatingImpDAO ratingdao;
    private RatingMapper ratingMapper;

    public RatingService(RatingImpDAO ratingdao, RatingMapper ratingMapper) {
        this.ratingdao =  CommonsUtils.getService(RatingImpDAO.class);
        this.ratingMapper =  CommonsUtils.getService(RatingMapper.class);
    }

    public List<RatingDTO> getStatusByIdeaAndStatus(Long id,RatingEntity.Status DISLIKE){
        try {
            List<RatingEntity> ratings=ratingdao.getStatusById(id,DISLIKE);
            if(ratings!=null){
                return ratingMapper.RatingsToRatingsDTOS(ratings);
            }
        }catch (Exception e) {
            LOG.error("Error to find Status by id idea", e.getMessage());
        }
        return null;

    }
    @ExoTransactional
    public RatingDTO addRating(RatingDTO ratingDTO){
        RatingEntity ratingEntity=null;
        try {
            ratingEntity=ratingdao.create(ratingMapper.RatingdtoToRating(ratingDTO));
        }catch (Exception e) {
            LOG.error("Error to create", ratingDTO.getUser() , e);
        }
        return  ratingMapper.RatingToRatingDTO(ratingEntity);
    }




    }








