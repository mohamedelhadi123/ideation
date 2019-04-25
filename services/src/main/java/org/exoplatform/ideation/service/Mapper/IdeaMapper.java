package org.exoplatform.ideation.service.Mapper;

import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IdeaMapper {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public IdeaMapper() {
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
                IdeaEntity ideaEntity=new IdeaEntity();
                ideaEntity.setUser(ideaDTO.getUser());
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
