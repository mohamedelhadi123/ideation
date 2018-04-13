package org.exoplatform.ideation.entities.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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
@Table(name = "IDEA_IDEAS")
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
                        name = "Idea.findIdeaById",
                        query = "SELECT idea FROM Idea idea where idea.id = :ideaId"
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
    @Column(name = "RATE")
    private long rate;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private String status;
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



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdeaEntity badgeEntity = (IdeaEntity) o;
        return !(badgeEntity.getId() == 0 || getId() == 0) && Objects.equals(getId(), badgeEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Badge{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                "}";
    }
}
