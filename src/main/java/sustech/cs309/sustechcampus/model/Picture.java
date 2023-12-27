package sustech.cs309.sustechcampus.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Picture {

  @Id
  @GeneratedValue
  UUID pid;
  String path;
  UUID nextPid;

  public Picture() {
  }

  public Picture(String path) {
    this.path = path;
  }

}
