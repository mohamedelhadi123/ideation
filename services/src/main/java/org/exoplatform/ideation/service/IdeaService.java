package org.exoplatform.ideation.service;

import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.dto.IdeaDTO;

import java.util.List;


public interface IdeaService {


    public IdeaEntity createIdea (IdeaEntity ideaEntity) ;

    public List<IdeaDTO> getAllIdeas();

    public IdeaEntity updateIdea (IdeaEntity ideaEntity);


    public IdeaDTO save(IdeaDTO entity, boolean newIde);

    public void deleteIdea (IdeaEntity ideaEntity);



    public IdeaEntity findIdeaByTitle(String IdeaTitle) ;



    public IdeaEntity findIdeaById(Long IdeaId) ;




}
