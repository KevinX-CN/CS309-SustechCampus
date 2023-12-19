package sustech.cs309.sustechcampus.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Building {

  @Id
  @GeneratedValue
  private UUID bid;
  private String buildingName;
  private String buildingIntroduction;
  private String buildingImgPath;
  private UUID firstComment;
  private UUID lastComment;

  public Building() {
  }

  public Building(String buildingName, String buildingIntroduction, String buildingImgPath) {
    this.buildingName = buildingName;
    this.buildingIntroduction = buildingIntroduction;
    this.buildingImgPath = buildingImgPath;
    this.firstComment = null;
    this.lastComment = null;
  }

  public UUID getBid() {
    return bid;
  }

  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  public String getBuildingIntroduction() {
    return buildingIntroduction;
  }

  public void setBuildingIntroduction(String buildingIntroduction) {
    this.buildingIntroduction = buildingIntroduction;
  }

  public String getBuildingImgPath() {
    return buildingImgPath;
  }

  public void setBuildingImgPath(String buildingImgPath) {
    this.buildingImgPath = buildingImgPath;
  }

  public UUID getFirstComment() {
    return firstComment;
  }

  public void setFirstComment(UUID firstComment) {
    this.firstComment = firstComment;
  }

  public UUID getLastComment() {
    return lastComment;
  }

  public void setLastComment(UUID lastComment) {
    this.lastComment = lastComment;
  }
}
