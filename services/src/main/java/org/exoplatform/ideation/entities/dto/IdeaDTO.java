package org.exoplatform.ideation.entities.dto;
import org.exoplatform.ideation.entities.domain.FavoriteEntity;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


public class IdeaDTO implements Serializable {
    protected long id;
    private boolean like;
    private boolean fav ;



    @NotBlank

    @Size(min = 1, max = 50)
    protected String title;
    protected Date createdTime;

    @Size(min = 1, max = 256)
    protected String description;

    @Size(min = 1, max = 256)
    protected IdeaEntity.Status status;
    private long numfav;
    protected int rate;
    private long numlike;
    @Size(min = 1, max = 256)
    protected String createdBy;
    private String commentText;
    private long numcomments;
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public  IdeaDTO() {
    }
    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
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

    public boolean getFav() {return fav ;}

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getNumcomments() {
        return numcomments;
    }

    public void setNumcomments(long numcomments) {
        this.numcomments = numcomments;
    }

    public void setFav(boolean fav){this.fav = fav;}

    public long getNumfav() {
        return numfav;
    }

    public void setNumfav(long numfav) {
        this.numfav = numfav;
    }

    public long getNumlike() {
        return numlike;
    }

    public void setNumlike(long numlike) {
        this.numlike = numlike;
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

    public IdeaEntity.Status getStatus() {
        return status;
    }

    public void setStatus( IdeaEntity.Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}