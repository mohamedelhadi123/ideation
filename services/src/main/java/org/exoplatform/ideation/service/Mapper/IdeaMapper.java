package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IdeaMapper {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected IdentityManager identityManager = null;

    public IdeaMapper() {
        identityManager = CommonsUtils.getService(IdentityManager.class);

    }
    public IdeaDTO ideaTOideaDTO(IdeaEntity ideaEntity){
        return new IdeaDTO(ideaEntity);
    }
    public List<IdeaDTO> IdeasToIdeaDTOs(List<IdeaEntity> ideas){
        return ideas.stream()
                .filter(Objects::nonNull)
                .map(this::ideaTOideaDTO)
                .collect(Collectors.toList());
    }

    public IdeaEntity IdeadtoToIdea(IdeaDTO ideaDTO){
        try {
            if(ideaDTO==null){
                return null;
            }else {
                String user = ConversationState.getCurrent().getIdentity().getUserId();

                IdeaEntity ideaEntity=new IdeaEntity();
                ideaEntity.setUser(user);
                ideaEntity.setStatus(ideaDTO.getStatus());
                ideaEntity.setDescription(ideaDTO.getDescription());
                ideaEntity.setTitle(ideaDTO.getTitle());
                if (ideaDTO.getCreatedTime() != null) {
                    ideaEntity.setCreatedTime(formatter.parse(ideaDTO.getCreatedTime()));
                    return ideaEntity;
                }
            }


        }catch (Exception pe){
        pe.printStackTrace();
    }
        return null;


    }



}
