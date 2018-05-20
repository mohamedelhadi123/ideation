package org.exoplatform.ideation.entities.domain;
import lombok.Data;
import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "Comment")
@ExoEntity
@Table(name = "IDEATION_COMMENTS")
@NamedQueries({
        @NamedQuery(
                name = "Comment.findByIdeaId",
                query = "SELECT comment FROM Comment comment where comment.ideaId = :ideaId"
        ),
        @NamedQuery(
                name = "Comment.getAllComments",
                query = "SELECT comment FROM Comment comment"
        ),
        @NamedQuery(
                name = "Comment.count",
                query = "SELECT count(comment) FROM Comment comment where comment.ideaId = :ideaId"
        )

})
public class CommentEntity {

    public CommentEntity(){

    }


    @Id
    @Column(name = "COMMENT_ID")
    @SequenceGenerator(name = "SEQ_IDEATION_CMT_CMT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_IDEATION_CMT_CMT_ID")
    private long id;
    @Column(name = "AUTHOR", nullable = false)
    private String author;
    @Column(name = "CREATED_TIME")
    private Date   createdTime;
    @Column(name = "CMT")
    private String  commentText;
    @Column(name = "IDEA_ID")
    private long ideaId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }
}