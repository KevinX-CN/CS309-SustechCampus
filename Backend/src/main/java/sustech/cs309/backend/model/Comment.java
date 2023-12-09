package sustech.cs309.backend.model;

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

  public Comment() {
  }

  public Comment(UUID uid, UUID bid, String commentContent, Date commentTime) {
    this.uid = uid;
    this.bid = bid;
    this.commentContent = commentContent;
    this.commentTime = commentTime;
  }
}
