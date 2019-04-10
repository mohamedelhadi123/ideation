package org.exoplatform.ideation.service.utils;

import org.exoplatform.ideation.dao.CoworkerImp;
import org.exoplatform.ideation.dao.IdeaImp;
import org.exoplatform.ideation.dto.CowokerDTO;
import org.exoplatform.ideation.entities.CoworkerEntity;
import org.exoplatform.ideation.entities.IdeaEntity;

import java.util.List;
import java.util.logging.Logger;

public class CoworkerService {

  private CoworkerImp coworkerDao;
  private IdeaImp ideadoa;

    public CoworkerService(CoworkerImp coworkerDao,IdeaImp ideadoa) {
        this.coworkerDao = coworkerDao;
        this.ideadoa=ideadoa;
    }

    public void setCoworkerDao(CoworkerImp coworkerDao) {
        this.coworkerDao = coworkerDao;
    }

    public void setIdeadoa(IdeaImp ideadoa) {
        this.ideadoa = ideadoa;
    }

    public List<CoworkerEntity> getAllCoworker(){
        return coworkerDao.findAll();
    }

   public CowokerDTO getCoworker(Long id){
        CoworkerEntity ce=coworkerDao.find(id);

        CowokerDTO cd=new CowokerDTO();
        cd.setCoworker(ce.getCoworker());
        cd.setId(ce.getId());
        cd.setId_idea(ce.getIdea().getId());
        return cd;
   }

    public CoworkerEntity addCoworker(CowokerDTO DTOCowoker){
        System.out.println("l'id de idea"+DTOCowoker.getId_idea());
        IdeaEntity ideaentity=ideadoa.find(DTOCowoker.getId_idea());
        System.out.println(ideaentity.getId());
        System.out.println("l'id de cowoker"+ideaentity.getId());
        System.out.println("l'status"+ideaentity.getStatus());
        System.out.println(ideaentity.getUSER());
        CoworkerEntity cowkerentity=new CoworkerEntity( DTOCowoker.getCoworker(), ideaentity);
        coworkerDao.create(cowkerentity);
        return  cowkerentity;

    }
}
