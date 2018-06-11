package org.exoplatform.ideation.service.impl;
import org.exoplatform.ideation.entities.domain.LikeEntity;
import org.exoplatform.ideation.entities.dto.LikeDTO;
import org.exoplatform.ideation.storage.dao.jpa.LikeDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LikeService {
private LikeDAO likeDAO = new LikeDAO();





    public Boolean save(LikeDTO entity,String author,long ideaId) {
        boolean isfav = false ;
        entity.setCreatedTime(new Date());
        LikeEntity likeEntity = null;
        LikeEntity entiti = likeDAO.getLikeByIdeaAndUserId(author,ideaId);
        if(entiti != null){
            likeDAO.delete(entiti);
            isfav = false ;
        }else {
            likeDAO.create(convert(entity));
            isfav = true ;
        }
        return isfav;
    }


    public long count(long ideaId){
        return  likeDAO.getLikesByIdeaIdCount(ideaId);



    }



    public void remove(LikeDTO entity) {
        if (entity == null) {
            throw new IllegalStateException("Parameter 'entity' = + "+entity+ " or 'entity.id' is null");
        }
        likeDAO.delete(convert(entity));
    }

    public List<LikeDTO> getLikes() {
        List<LikeEntity> entities = likeDAO.getAllLikes();
        List<LikeDTO> dtos = new ArrayList<LikeDTO>();
        for (LikeEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }



    private LikeEntity convert(LikeDTO dto) {
        LikeEntity entity = new LikeEntity();
        entity.setId(dto.getId());
        entity.setAuthor(dto.getAuthor());
        entity.setIdeaId(dto.getIdeaId());
        entity.setCreatedTime(dto.getCreatedTime());
        return entity;
    }


    private LikeDTO convert(LikeEntity entity) {
        LikeDTO dto = new LikeDTO();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setIdeaId(entity.getIdeaId());
        dto.setCreatedTime(entity.getCreatedTime());
        return dto;
    }
}
