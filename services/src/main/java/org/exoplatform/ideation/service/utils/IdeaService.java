package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.IdeaImpDAO;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.service.Mapper.IdeaMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

public class IdeaService {

    private IdeaImpDAO ideaDao;
    private IdeaMapper ideaMapper;
    private static final Log LOG = ExoLogger.getExoLogger(IdeaEntity.class);

    public IdeaService(IdeaImpDAO ideaDao, IdeaMapper ideaMapper) {
        this.ideaDao = CommonsUtils.getService(IdeaImpDAO.class);
        this.ideaMapper = CommonsUtils.getService(IdeaMapper.class);
    }

    public List<IdeaDTO> getIdeaPublished(IdeaEntity.Status PUBLISHED){
        try {
            //--- load all Rules
            List<IdeaEntity> ideas = ideaDao.getPublishedIdeas(PUBLISHED);
            if (ideas != null) {
                return ideaMapper.IdeasToIdeaDTOs(ideas);
            }

        } catch (Exception e) {
            LOG.error("Error to find Idea published", e.getMessage());
        }
        return null;

    }

    public List<IdeaDTO> getIdeaPublishedByUser(IdeaEntity.Status PUBLISHED, String user){
        try{
            List<IdeaEntity> ideas=ideaDao.getPublishedIdeaByUser(PUBLISHED,user);
            if(ideas!=null){
                return ideaMapper.IdeasToIdeaDTOs(ideas);
            }
        }catch (Exception e) {
            LOG.error("Error to find Idea published by user", e.getMessage());
        }
        return null;
    }
   public IdeaDTO getIdea(Long id){
        try {
            IdeaEntity idea=ideaDao.find(id);
            if (idea!=null){
                return ideaMapper.ideaTOideaDTO(idea);
            }
        }catch (Exception e) {
            LOG.error("Error to find Idea published by user", e.getMessage());
        }
       return null;
   }

@ExoTransactional
public  IdeaDTO addIdea(IdeaDTO ideaDTO){
        IdeaEntity ideaEntity=null;
                try{
                    ideaEntity=ideaDao.create(ideaMapper.IdeadtoToIdea(ideaDTO));
                }catch(Exception e) {
                    LOG.error("Error to create badge with title {}", ideaDTO.getUser() , e);
                }
    return ideaMapper.ideaTOideaDTO(ideaEntity);


}



}
