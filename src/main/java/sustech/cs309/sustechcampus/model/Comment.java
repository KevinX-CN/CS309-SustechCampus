package sustech.cs309.sustechcampus.model;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Comment {
  @Id
  @GeneratedValue
  private UUID cid;
  private UUID uid;
  private UUID bid;
  private String commentContent;
  private Date commentTime;
  private UUID nextComment;
  private String picturePath;

  public Comment() {

  }

  public Comment(UUID uid, UUID bid, String commentContent, Date commentTime,String picturePath) {
    this.uid = uid;
    this.bid = bid;
    this.commentContent = commentContent;
    this.commentTime = commentTime;
    this.picturePath = picturePath;
    this.nextComment = null;
  }

  public UUID getCid() {
    return cid;
  }

  public UUID getUid() {
    return uid;
  }

  public UUID getBid() {
    return bid;
  }

  public String getCommentContent() {
    return commentContent;
  }

  public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }

  public Date getCommentTime() {
    return commentTime;
  }

  public void setCommentTime(Date commentTime) {
    this.commentTime = commentTime;
  }

  public UUID getNextComment() {
    return nextComment;
  }

  public void setNextComment(UUID nextComment) {
    this.nextComment = nextComment;
  }

  public String getPicturePath() {
    return picturePath;
  }

  public void setPicturePath(String picturePath) {
    this.picturePath = picturePath;
  }
}
