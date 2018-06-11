package org.exoplatform.ideation.entities.dto;

import org.exoplatform.ideation.entities.domain.CoworkerEntity;

import java.io.Serializable;

public class CoworkerDTO implements Serializable {

    public CoworkerDTO(){}

    public CoworkerDTO(CoworkerEntity coworkerEntity){
        this.id = coworkerEntity.getId();
        this.ideaId = coworkerEntity.getIdeaId();
        this.coworker = coworkerEntity.getCoworker();
    }

    private long id;

    private long ideaId;

    private String coworker;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public String getCoworker() {
        return coworker;
    }

    public void setCoworker(String coworker) {
        this.coworker = coworker;
    }
}
