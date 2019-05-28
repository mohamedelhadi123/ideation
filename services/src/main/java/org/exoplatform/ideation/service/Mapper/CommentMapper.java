package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.CommentDTO;
import org.exoplatform.ideation.entities.CommentEntity;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommentMapper {
  protected IdentityManager identityManager = null;


  public CommentMapper() {
    this.identityManager = CommonsUtils.getService(IdentityManager.class);

  }

  public CommentDTO CommentTOcommentDTO(CommentEntity commentEntity) {
    return new CommentDTO(commentEntity);
  }

  public List<CommentDTO> CommentsToCommentDTOs(List<CommentEntity> comments) {
    return comments.stream()
        .filter(Objects::nonNull)
        .map(this::CommentTOcommentDTO)
        .collect(Collectors.toList());
  }

  public CommentEntity CommentdtoToComment(CommentDTO commentDTO) {
    try {
      if (commentDTO == null) {
        return null;
      } else {
        String user = ConversationState.getCurrent().getIdentity().getUserId();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentText(commentDTO.getCommentText());
        commentEntity.setUSER(user);
        commentEntity.setCreatedTime(new Date());

        IdeaEntity idea = this.IdeaFormLongId(commentDTO.getId_ideac());
        commentEntity.setIdea(idea);
        return commentEntity;


      }
    } catch (Exception pe) {
      pe.printStackTrace();
    }
    return null;
  }

  public IdeaEntity IdeaFormLongId(Long id) {
    IdeaEntity ideaEntity = new IdeaEntity();
    ideaEntity.setId(id);
    return ideaEntity;
  }
}
