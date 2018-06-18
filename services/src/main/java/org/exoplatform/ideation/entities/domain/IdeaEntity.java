package org.exoplatform.ideation.entities.domain;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.ecs.html.S;
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
                        name = "Idea.getPublishedIdeas",
                        query = "SELECT idea FROM Idea idea where idea.status = :PUBLISHED or (idea.status = :DRAFTED and idea.createdBy = :createdBy)"
                ),
                @NamedQuery(
                        name = "Idea.getDraftIdeas",
                        query = "SELECT idea FROM Idea idea where idea.status = :DRAFTED and idea.createdBy = :createdBy"
                ),
                @NamedQuery(name = "Idea.getCoworker",
                query = "SELECT c FROM Idea idea inner join idea.coworker c WHERE idea.id = :ideaId"),

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
                ),
                    @NamedQuery(
                            name = "Idea.getIdea",
                            query = "SELECT idea FROM Idea idea WHERE idea.id = :ideaId "
                    ),


})
public class IdeaEntity {
    public enum Status {
        PUBLISHED("PUBLISHED"),
        ARCHIVED("ARCHIVED"),
        DRAFTED("DRAFTED");

        private static Map<String, Status> FORMAT_MAP = Stream
                .of(Status.values())
                .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

        private final String formatted;

        Status(String formatted) {
            this.formatted = formatted;
        }





        @JsonCreator // This is the factory method and must be static
        public static Status fromString(String string) {
            return Optional
                    .ofNullable(FORMAT_MAP.get(string))
                    .orElseThrow(() -> new IllegalArgumentException(string));
        }

        @JsonCreator // This is the factory method and must be static
        public  String toString() {
            return this.name();
        }

    }


    @Id
    @Column(name = "IDEA_ID")
    @SequenceGenerator(name = "SEQ_IDEATION_IDEAS_IDEA_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_IDEAS_IDEA_ID")
    private long  id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "CREATED_BY")
    private String createdBy;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "IDEATION_COWORKERS",
            joinColumns = @JoinColumn(name = "IDEA_ID"))
    private Set<String> coworker = new HashSet<String>();

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


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<String> getCoworker() {
        return coworker;
    }

    public void setCoworker(Set<String> coworker) {
        this.coworker = coworker;
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
