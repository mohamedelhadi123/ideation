package org.exoplatform.ideation.dto;

import org.exoplatform.ideation.entities.CoworkerEntity;

import java.io.Serializable;

public class CowokerDTO implements Serializable {
    public CowokerDTO() {
    }

    public CowokerDTO(CoworkerEntity coworkeren) {
        this.coworker = coworkeren.getCoworker();
        this.id_idea=coworkeren.getIdea().getId();

    }

    private Long  id;
    private String coworker;
    private Long id_idea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoworker() {
        return coworker;
    }

    public void setCoworker(String coworker) {
        this.coworker = coworker;
    }

    public Long getId_idea() {
        return id_idea;
    }

    public void setId_idea(Long id_idea) {
        this.id_idea = id_idea;
    }
}
