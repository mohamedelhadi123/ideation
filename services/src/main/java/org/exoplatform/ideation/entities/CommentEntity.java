package org.exoplatform.ideation.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Entity(name = "comment")
@ExoEntity
@Table(name = "IDEATION_COMMENTS")
@NamedQueries({
    @NamedQuery(name = "Comment.getCommentByIdea", query = "select c from comment c where c.idea.id= :id"),
})
public class CommentEntity implements Serializable {
  @Id
  @GeneratedValue
  @Column(name = "COMMENT_ID")
  private Long id;

  @Column(name = "USER")
  private String user;
  @Column(name = "CREATED_TIME")
  private Date createdTime;
  @Column(name = "CMT")
  private String commentText;
  @ManyToOne
  @JoinColumn(name = "ID_IDEAC")
  private IdeaEntity idea;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUSER() {
    return user;
  }

  public void setUSER(String user) {
    this.user = user;
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

  public IdeaEntity getIdea() {
    return idea;
  }

  public void setIdea(IdeaEntity idea) {
    this.idea = idea;
  }

  public CommentEntity(String user, Date createdTime, String commentText, IdeaEntity idea) {
    this.user = user;
    this.createdTime = createdTime;
    this.commentText = commentText;
    this.idea = idea;
  }

  public CommentEntity() {
  }
}
