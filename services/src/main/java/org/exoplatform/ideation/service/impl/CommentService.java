package org.exoplatform.ideation.service.impl;

import java.lang.*;
import org.exoplatform.ideation.entities.domain.CommentEntity;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.dto.CommentDTO;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.storage.dao.jpa.CommentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;



public class CommentService {
    private  final Logger LOG = LoggerFactory.getLogger(CommentService.class);
    private CommentDAO commentDAO;


    public CommentService() {
        this.commentDAO = new CommentDAO();

    }

    public CommentDTO save(CommentDTO entity) {
        if (entity == null) {
            throw new IllegalStateException("Parameter 'entity' is null");
        }

        CommentEntity commentEntity = null;
        commentEntity = commentDAO.create(convert(entity));
        return convert(commentEntity);
    }

    public void remove(CommentDTO entity) {
        if (entity == null) {
            throw new IllegalStateException("Parameter 'entity' = + "+entity+ " or 'entity.id' is null");
        }
        commentDAO.delete(convert(entity));
    }

    public List<CommentDTO> getCommentsByIdeaId(long id, int offset, int limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Method getCommentsByRequestID - Parameter 'offset' must be positive");
        }
        List<CommentEntity> entities = commentDAO.getCommentsByIdeaId(id, offset, limit);
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        for (CommentEntity entity : entities) {
            dtos.add(convert(entity));
        }
        return dtos;
    }


    public Long getCommentsByIdeaIdCount(Long id) {
        return commentDAO.getCommentsByIdeaIdCount(id);
    }

    private CommentEntity convert(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setId(dto.getId());
        entity.setCreatedTime(dto.getCreatedTime());
        entity.setCommentText(dto.getCommentText());
        entity.setIdeaId(dto.getIdeaId());
        entity.setAuthor(dto.getAuthor());
        return entity;
    }

    private CommentDTO convert(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        IdeaEntity ideaEntity = new IdeaEntity();
        dto.setId(entity.getId());
        dto.setCreatedTime(entity.getCreatedTime());
        dto.setIdeaId(ideaEntity.getId());
        dto.setCommentText(entity.getCommentText());
        dto.setAuthor(entity.getAuthor());
        return dto;
    }

}
