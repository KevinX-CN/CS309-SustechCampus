package sustech.cs309.sustechcampus.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

  @Id
  @GeneratedValue
  private UUID uid;
  private String userName;
  private String password;

  public User() {
  }

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public UUID getUid() {
    return this.uid;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean verifyPassword(String password) {
    return this.password.equals(password);
  }
}
