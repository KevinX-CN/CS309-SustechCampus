package sustech.cs309.sustechcampus.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Account {

  enum authorityType {
    Admin,
    User,
    Banned
  }

  ;

  @Id
  @GeneratedValue
  private UUID uid;
  private String username;
  private String password;
  authorityType authority;

  public Account() {
  }

  public Account(String username, String password) {
    this.username = username;
    this.password = password;
    this.authority = authorityType.User;
  }

  public UUID getUid() {
    return this.uid;
  }

  public String getUserName() {
    return this.username;
  }

  public void setUserName(String userName) {
    this.username = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean verifyPassword(String password) {
    return this.password.equals(password);
  }
}
