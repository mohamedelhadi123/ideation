package org.exoplatform.ideation.service.utils;

import org.exoplatform.ideation.dao.IdeaImp;
import org.exoplatform.ideation.entities.IdeaEntity;

import java.util.List;

public class IdeaService {
    private IdeaImp ideaDao;


    /**
     *
     * @param ideaDao;
     */

    public IdeaService(IdeaImp ideaDao) {
        this.ideaDao = ideaDao;
    }
    public List<IdeaEntity> getAllIdea(){
        return this.ideaDao.findAll();
    }

    public IdeaEntity AddIdea(IdeaEntity idea){
       

         ideaDao.create(idea);
           
        return idea;
    }

    public String deleteIdea(Long id){
        String msg=null;
        IdeaEntity idea= ideaDao.find(id);
        if(idea !=null){
            ideaDao.delete(idea);
         msg="is deleted";
        }else{
            msg="is not deleted";
        }
        return msg;
    }

    public List<IdeaEntity> getIdeaPush(IdeaEntity.Status publshed){
            return ideaDao.getPublishedIdeas(publshed);

    }

 
}
