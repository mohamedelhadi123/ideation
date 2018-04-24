package org.exoplatform.ideation.entities.dto;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


public class IdeaDTO implements Serializable {
    protected long id;

    @NotBlank
    @Size(min = 1, max = 50)
    protected String title;

    @Size(min = 1, max = 256)
    protected String description;

    @Size(min = 1, max = 256)
    protected String status;

    protected int rate;

    @Size(min = 1, max = 256)
    protected String createdBy;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    protected Date createdTime;
    public  IdeaDTO() {
    }

    public  IdeaDTO(IdeaEntity ideaEntity) {

        this.id = ideaEntity.getId();

        this.title = ideaEntity.getTitle();

        this.status = ideaEntity.getStatus();

        this.description = ideaEntity.getDescription();

        this.createdBy = ideaEntity.getCreatedBy();
    }

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

}