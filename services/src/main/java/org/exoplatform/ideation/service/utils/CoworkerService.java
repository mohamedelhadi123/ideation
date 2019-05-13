package org.exoplatform.ideation.service.utils;

import org.exoplatform.ideation.dao.CoworkerImpDAO;
import org.exoplatform.ideation.dao.IdeaImpDAO;
import org.exoplatform.ideation.dto.CowokerDTO;
import org.exoplatform.ideation.entities.CoworkerEntity;
import org.exoplatform.ideation.entities.IdeaEntity;

import java.util.ArrayList;
import java.util.List;

public class CoworkerService {

    private CoworkerImpDAO coworkerDao;
    private IdeaImpDAO ideadoa;

    public CoworkerService(CoworkerImpDAO coworkerDao, IdeaImpDAO ideadoa) {
        this.coworkerDao = coworkerDao;
        this.ideadoa=ideadoa;
    }

    public void setCoworkerDao(CoworkerImpDAO coworkerDao) {
        this.coworkerDao = coworkerDao;
    }

    public void setIdeadoa(IdeaImpDAO ideadoa) {
        this.ideadoa = ideadoa;
    }

    /*public List<CoworkerEntity> getAllCoworker(){
        return coworkerDao.findAll();
    }*/

    public CowokerDTO getCoworker(Long id){
        CoworkerEntity ce=coworkerDao.find(id);

        CowokerDTO cd=new CowokerDTO();
        cd.setCoworker(ce.getCoworker());
        cd.setId(ce.getId());
        cd.setId_idea(ce.getIdea().getId());
        return cd;
    }

    public CoworkerEntity addCoworker(CowokerDTO DTOCowoker){
        IdeaEntity ideaentity=ideadoa.find(DTOCowoker.getId_idea());

        CoworkerEntity cowkerentity=new CoworkerEntity( DTOCowoker.getCoworker(), ideaentity);
        coworkerDao.create(cowkerentity);
        return  cowkerentity;

    }

    public  List<CowokerDTO>  getallCowokerDto(){
        List<CowokerDTO> list1=new ArrayList<CowokerDTO>();
        List<CoworkerEntity> list2= coworkerDao.findAll();
        int i=0;
        while(i<list2.size()){
            CowokerDTO cdto=new CowokerDTO();
            cdto.setId(list2.get(i).getId());
            cdto.setId_idea(list2.get(i).getIdea().getId());
            cdto.setCoworker(list2.get(i).getCoworker());
            list1.add(cdto);
            i++;

        }



        return list1;
    }
}
