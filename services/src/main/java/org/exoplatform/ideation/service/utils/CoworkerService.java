package org.exoplatform.ideation.service.utils;

import org.exoplatform.ideation.dao.CoworkerImp;
import org.exoplatform.ideation.entities.CoworkerEntity;

import java.util.List;

public class CoworkerService {
  private CoworkerImp coworkerDao;

    public CoworkerService(CoworkerImp coworkerDao) {
        this.coworkerDao = coworkerDao;
    }



    public List<CoworkerEntity> getAllCoworker(){
        return coworkerDao.findAll();
    }

    public CoworkerEntity addCoworker(CoworkerEntity cr){
        coworkerDao.create(cr);
        return cr;
    }
}
