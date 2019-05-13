package org.exoplatform.ideation.service.utils;

import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dao.IdeaImpDAO;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.service.Mapper.IdeaMapper;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class IdeaService {
    private IdeaImpDAO ideaDao;
    private IdeaMapper ideaMapper;
    private static final Log LOG = ExoLogger.getExoLogger(IdeaEntity.class);
    private static     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    protected IdentityManager identityManager = null;

    public IdeaService(IdeaImpDAO ideaDao, IdeaMapper ideaMapper) {
        this.ideaMapper = CommonsUtils.getService(IdeaMapper.class);
        identityManager = CommonsUtils.getService(IdentityManager.class);
        this.ideaDao=CommonsUtils.getService(IdeaImpDAO.class);

    }

    public List<IdeaDTO> getIdeaByStatus(IdeaEntity.Status status){
        try {
            //--- load all Rules
            List<IdeaEntity> ideas = ideaDao.getIdeasByStatus(status);
            if (ideas != null) {

                return ideaMapper.IdeasToIdeaDTOs(ideas);
            }

        } catch (Exception e) {
            LOG.error("Error to find Idea ", e.getMessage());
        }
        return null;

    }

    public List<IdeaDTO> getIdeaByUserAndStatus(IdeaEntity.Status status, String user){
        try{
            List<IdeaEntity> ideas=ideaDao.getIdeaByUserAndStatus(status,user);
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
    public IdeaDTO addIdea(IdeaDTO ideaDTO) {
        IdeaEntity ideaEntity = null;
        try {
            ideaEntity = ideaDao.create(ideaMapper.IdeadtoToIdea(ideaDTO));
        } catch (Exception e) {
            LOG.error("Error to create badge with title ", ideaDTO.getUser(), e);
        }
        return ideaMapper.ideaTOideaDTO(ideaEntity);

    }

    @ExoTransactional

    public void deleteIdea(Long id) {
        IdeaEntity idea=null;
        idea=ideaDao.find(id);
        if(idea!=null) {

            try {

                ideaDao.delete(idea);

            } catch (Exception e) {
                LOG.error("Error to delete idea with id {}", id, e);
            }
        }
    }

    @ExoTransactional
    public  IdeaDTO  updateIdea ( IdeaDTO  ideaDTO) {

        try {
            String user = ConversationState.getCurrent().getIdentity().getUserId();

            IdeaEntity ideaEntity = ideaDao.find(ideaDTO.getId());
            if(ideaEntity!=null){
                ideaEntity.setDescription(ideaDTO.getDescription());
                ideaEntity.setStatus(ideaDTO.getStatus());
                ideaEntity.setTitle(ideaDTO.getTitle());
                ideaEntity.setUser(user);
                ideaEntity.setResume(ideaDTO.getResume());
                ideaEntity.setExplanation(ideaDTO.getExplanation());
                ideaEntity.setCreatedTime(new Date());

                return ideaMapper.ideaTOideaDTO(ideaEntity);

            }
        } catch (Exception e) {
            LOG.error("Error to update with id {}", ideaDTO.getId() , e);
        }

        return null;
    }





}
