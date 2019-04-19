package org.exoplatform.ideation.service.utils;

import org.exoplatform.ideation.dao.IdeaImpDAO;
import org.exoplatform.ideation.entities.IdeaEntity;

import java.util.List;

public class IdeaService {
    private IdeaImpDAO ideaDao;


    /**
     *
     * @param ideaDao;
     */

    public IdeaService(IdeaImpDAO ideaDao) {
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


    public List<IdeaEntity> getIdeaByUser(String user){
        return ideaDao.getIdeaByUser(user);
    }

 
}
