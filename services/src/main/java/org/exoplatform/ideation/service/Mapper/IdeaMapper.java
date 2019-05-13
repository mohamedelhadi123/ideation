package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.entities.ThemeEntity;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IdeaMapper {
    protected IdentityManager identityManager = null;

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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
                ideaEntity.setExplanation(ideaDTO.getExplanation());
                ideaEntity.setResume(ideaDTO.getResume());

                ideaEntity.setCreatedTime(new Date());




                ThemeEntity theme=this.ThemeFromId(ideaDTO.getId_themet());
                ideaEntity.setTheme(theme);
                return ideaEntity;
            }


        }catch (Exception pe){
            pe.printStackTrace();
        }
        return null;


    }


    public ThemeEntity ThemeFromId(Long id){
        ThemeEntity themeEntity=new ThemeEntity();
        themeEntity.setId(id);
        return themeEntity;
    }


}
