package org.exoplatform.ideation.entities.dto;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;


public class IdeaDTO {
    protected Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    protected String title;

    @Size(min = 1, max = 256)
    protected String description;

    @Size(min = 1, max = 256)
    protected String status;

    protected int rate;


    public  IdeaDTO(IdeaEntity ideaEntity) {

        this.id = ideaEntity.getId();

        this.title = ideaEntity.getTitle();

        this.status = ideaEntity.getStatus();

        this.description = ideaEntity.getDescription();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }



}