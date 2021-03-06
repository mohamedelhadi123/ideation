package org.exoplatform.ideation.service;

import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.dto.FavoriteDTO;
import org.exoplatform.ideation.entities.dto.IdeaDTO;

import java.util.List;
import java.util.Set;


public interface IdeaService {


    public IdeaEntity createIdea (IdeaEntity ideaEntity) ;

    public long getMaxId() ;


    public List<IdeaDTO> getUserIdeas(String PUBLISHED);

    public List<IdeaDTO> getAllIdeas();
    public Set<String> getCoworker(long ideaId);
    public IdeaEntity updateIdea (IdeaEntity ideaEntity);
    public IdeaDTO getIdea(long ideaId);
    public IdeaDTO save(IdeaDTO entity, boolean newIde);
    public void delete(IdeaDTO entity);
    public List<IdeaDTO> getDraftIdeas(String createdBy);
    public IdeaEntity findIdeaByTitle(String IdeaTitle) ;







}
