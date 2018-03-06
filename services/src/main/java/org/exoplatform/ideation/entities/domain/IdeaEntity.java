package org.exoplatform.ideation.entities.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.exoplatform.commons.api.persistence.ExoEntity;

@Entity(name = "Idea")
@ExoEntity
@Table(name = "IDEA")
@NamedQueries({
                @NamedQuery(
                        name = "Idea.getAllIdeas",
                        query = "SELECT idea FROM Idea idea"
                ),
                @NamedQuery(
                        name = "Idea.findIdeaByTitle",
                        query = "SELECT idea FROM Idea idea where idea.title = :ideaTitle"
                ),
                @NamedQuery(
                        name = "Idea.deleteIdeaByTitle",
                        query = "DELETE FROM Idea Idea WHERE Idea.title = :ideaTitle "
                ),
                @NamedQuery(
                        name = "Idea.deleteIdeaById",
                        query = "DELETE FROM Idea idea WHERE idea.id = :ideaId "
                )

})
public class IdeaEntity implements Serializable {

    @Id
    @Column(name = "IDEA_ID")
    private long  id;
    private long rate;
    private String title;
    private String description;
    private String status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "Idea_Idea_COWORKERS",
            joinColumns = @JoinColumn(name = "Idea_ID"))
    private Set<String> coworker = new HashSet<String>();

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Set<String> getCoworker() {
        return coworker;
    }

    public void setCoworker(Set<String> coworker) {
        this.coworker = coworker;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdeaEntity IdeaEntity = (org.exoplatform.ideation.entities.domain.IdeaEntity) o;

        if (id != IdeaEntity.id) return false;
        if (coworker != null ? !coworker.equals(IdeaEntity.coworker) : IdeaEntity.coworker != null) return false;
        if (createdBy != null ? !createdBy.equals(IdeaEntity.createdBy) : IdeaEntity.createdBy != null) return false;
        if (createdTime != null ? !createdTime.equals(IdeaEntity.createdTime) : IdeaEntity.createdTime != null) return false;
        if (description != null ? !description.equals(IdeaEntity.description) : IdeaEntity.description != null) return false;
        if (status != null ? !status.equals(IdeaEntity.status) : IdeaEntity.status != null) return false;
        if (title != null ? !title.equals(IdeaEntity.title) : IdeaEntity.title != null) return false;

        return true;
    }
}
