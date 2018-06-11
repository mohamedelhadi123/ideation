package org.exoplatform.ideation.entities.domain;


import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Coworker")
@ExoEntity
@Table(name = "IDEATION_COWORKERS")
@NamedQueries({
        @NamedQuery(
                name = "Coworker.getCoworker",
                query = "SELECT coworker FROM Coworker coworker where coworker.ideaId = :ideaId"
        ),

        @NamedQuery(
                name = "Coworker.count",
                query = "SELECT count(coworker) FROM Coworker coworker WHERE  coworker.ideaId = :ideaId"
        ),


})

public class CoworkerEntity {
    @Id
    @Column(name = "CWK_ID")
    @SequenceGenerator(name = "SEQ_IDEATION_CWK_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_CWK_ID")
    private long  id;
    @Column(name = "COWORKER")
    private String coworker;
    @Column(name = "IDEA_ID")
    private long ideaId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoworker() {
        return coworker;
    }

    public void setCoworker(String coworker) {
        this.coworker = coworker;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }
}
