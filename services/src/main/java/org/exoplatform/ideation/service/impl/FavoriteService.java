package org.exoplatform.ideation.service.impl;



import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.storage.dao.jpa.FavoriteDAO;
import org.exoplatform.ideation.entities.dto.FavoriteDTO;
import org.exoplatform.ideation.entities.domain.FavoriteEntity;
import org.exoplatform.ideation.storage.dao.jpa.IdeaDAO;
import org.exoplatform.services.security.ConversationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class FavoriteService {
    private  final Logger LOG = LoggerFactory.getLogger(FavoriteService.class);
    private String currentUser = ConversationState.getCurrent().getIdentity().getUserId();

    private FavoriteDAO favoriteDAO;


    public FavoriteService() {
        this.favoriteDAO = new FavoriteDAO();
    }

    public FavoriteDTO save(FavoriteDTO entity) {
        entity.setCreatedTime(new Date());
        FavoriteEntity favoriteEntity = null;
        /*if (entity == null) {*/
        favoriteEntity = favoriteDAO.create(convert(entity));

        return convert(favoriteEntity);
    }






    public void remove(FavoriteDTO entity) {
        if (entity == null) {
            throw new IllegalStateException("Parameter 'entity' = + "+entity+ " or 'entity.id' is null");
        }
        favoriteDAO.delete(convert(entity));
    }

    public List<FavoriteDTO> getFavoritesByUserId(String user) {
        List<FavoriteEntity> entities = favoriteDAO.getAllFavoriteofIdeaofuser(user);
        List<FavoriteDTO> dtos = new ArrayList<FavoriteDTO>();
        for (FavoriteEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }


    private FavoriteEntity convert(FavoriteDTO dto) {
        FavoriteEntity entity = new FavoriteEntity();
        entity.setId(dto.getId());
        entity.setAuthor(dto.getAuthor());
        entity.setIdeaId(dto.getIdeaId());
        entity.setCreatedTime(dto.getCreatedTime());
        return entity;
    }


    private FavoriteDTO convert(FavoriteEntity entity) {
        FavoriteDTO dto = new FavoriteDTO();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setIdeaId(entity.getIdeaId());
        dto.setCreatedTime(entity.getCreatedTime());
        return dto;
    }

}
