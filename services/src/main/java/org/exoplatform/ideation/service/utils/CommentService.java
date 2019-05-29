package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.CommentImpDAO;
import org.exoplatform.ideation.dto.CommentDTO;
import org.exoplatform.ideation.entities.CommentEntity;
import org.exoplatform.ideation.service.Mapper.CommentMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


import java.util.List;

public class CommentService {
  private static final Log LOG = ExoLogger.getExoLogger(CommentEntity.class);

  private CommentImpDAO commentImpDAO;
  private CommentMapper commentMapper;

  public CommentService(CommentImpDAO commentImpDAO, CommentMapper commentMapper) {
    this.commentImpDAO = CommonsUtils.getService(CommentImpDAO.class);
    this.commentMapper = CommonsUtils.getService(CommentMapper.class);
  }

  public CommentService() {

  }


  @ExoTransactional
  public CommentDTO addComment(CommentDTO commentDTO) {
    CommentEntity commentEntity = null;

    try {

      commentEntity = commentImpDAO.create(commentMapper.CommentdtoToComment(commentDTO));

    } catch (Exception e) {
      LOG.error("Error to create badge with title {}", commentDTO.getUser(), e);
    }

    return commentMapper.CommentTOcommentDTO(commentEntity);
  }

  public List<CommentDTO> getAllCommentByIdea(Long id) {
    try {
      List<CommentEntity> comments = commentImpDAO.getCommentByIdIdea(id);
      if (comments != null) {
        return commentMapper.CommentsToCommentDTOs(comments);
      }
    } catch (Exception e) {
      LOG.error("Error to find Comment by idea", e.getMessage());

    }
    return null;

  }


}
