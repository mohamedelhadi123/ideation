package org.exoplatform.ideation.service.impl;

import org.exoplatform.ideation.entities.domain.RatingEntity;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.entities.dto.RateDTO;
import org.exoplatform.ideation.storage.dao.jpa.RateDAO;
import org.exoplatform.services.security.ConversationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateService {

    private  final Logger LOG = LoggerFactory.getLogger(FavoriteService.class);
    private String currentUser = ConversationState.getCurrent().getIdentity().getUserId();

    private RateDAO rateDAO;

    public RateService() {
        this.rateDAO = new RateDAO();
    }


    public RateDTO save(RateDTO entity,boolean newrate,String author,long ideaId) {
        entity.setCreatedTime(new Date());
        float rate = 0 ;
        RatingEntity ratingEntity = null;
        RatingEntity entiti = rateDAO.getRateByIdeaAndUser(ideaId,author);
        if (newrate && entiti == null ) {
            ratingEntity = rateDAO.create(convert(entity));
            entity.setRate(rate);
        } else {
            ratingEntity = rateDAO.update(convert(entity));
            entity.setRate(rate);
        }
        return convert(ratingEntity);
    }


    public List<RateDTO> count(){
        IdeaDTO rate = new IdeaDTO();
        long ideaId = rate.getId();
        List<RatingEntity> entities = rateDAO.getrateByIdeaIdCount(ideaId);
        List<RateDTO> dtos = new ArrayList<RateDTO>();
        for (RatingEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }

    public List<RateDTO> getRates() {
        List<RatingEntity> entities = rateDAO.getRateByIdea();
        List<RateDTO> dtos = new ArrayList<RateDTO>();
        for (RatingEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }

    private RatingEntity convert(RateDTO dto) {
        RatingEntity entity = new RatingEntity();
        IdeaDTO rate = new IdeaDTO();
        entity.setId(dto.getId());
        entity.setRate(dto.getRate());
        entity.setAuthor(dto.getAuthor());
        entity.setIdeaId(dto.getIdeaId());
        entity.setCreatedTime(dto.getCreatedTime());
        return entity;
    }


    private RateDTO convert(RatingEntity entity) {
        RateDTO dto = new RateDTO();
        dto.setId(entity.getId());
        dto.setRate(entity.getRate());
        dto.setAuthor(entity.getAuthor());
        dto.setIdeaId(entity.getIdeaId());
        dto.setCreatedTime(entity.getCreatedTime());
        return dto;
    }
}
