package org.exoplatform.ideation.service.impl;



import org.exoplatform.ideation.entities.domain.CoworkerEntity;
import org.exoplatform.ideation.entities.dto.CoworkerDTO;
import org.exoplatform.ideation.storage.dao.jpa.CoworkerDAO;

import java.util.ArrayList;
import java.util.List;

public class CoworkerService {

    private CoworkerDAO coworkerDAO ;

    public  List<CoworkerDTO> getcoworker(CoworkerDTO coworkerDTO, long ideaId) {
        CoworkerEntity coworkerEntity = null;
        List<CoworkerEntity>Coworkers = coworkerDAO.getCoworker(ideaId);
        List<CoworkerDTO> dtos = new ArrayList<CoworkerDTO>();
        for (CoworkerEntity entity : Coworkers) {
            dtos.add(convert(entity));
        }
        return dtos;
    }

    private CoworkerEntity convert(CoworkerDTO dto) {
        CoworkerEntity entity = new CoworkerEntity();
        entity.setId(dto.getId());
        entity.setCoworker(dto.getCoworker());
        entity.setId(dto.getId());
        entity.setIdeaId(dto.getIdeaId());
        return entity;
    }

    private CoworkerDTO convert(CoworkerEntity entity) {
        CoworkerDTO dto = new CoworkerDTO();
        dto.setId(entity.getId());
        dto.setIdeaId(entity.getIdeaId());
        dto.setCoworker(entity.getCoworker());
        return dto;
    }


}
