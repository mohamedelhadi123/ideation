package org.exoplatform.ideation.entities.domain;
import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;


@Entity(name = "IdeaCommentEntity")
@ExoEntity
@Table(name = "IDEATION_COMMENTS")
@NamedQueries({
        @NamedQuery(name = "commentEntity.findByIdeaId", query = "SELECT a FROM IdeaCommentEntity a where a.id = :ideaId"),
        @NamedQuery(name = "commentEntity.count", query = "SELECT count(a.id) FROM IdeaCommentEntity a  where a.id = :ideaId ") })
public class CommentEntity {

    public CommentEntity(){

    }


    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_IDEATION_COMMENTS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_COMMENTS_ID")
    private long Id;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "IDEA_ID")
    private long ideaId;

    @Column(name = "CREATED_TIME")
    private long   createdTime;

    @Column(name = "CMT")
    private String   commentText;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAuthor(){return author;}

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}