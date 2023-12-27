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
  private String detailedInfo;
  private String buildingGroup;
  private UUID firstComment;
  private UUID lastComment;
  private UUID firstPicture;
  private UUID lastPicture;

  public Building() {
  }

  public Building(String buildingName, String buildingIntroduction, String detailedInfo,
    String buildingGroup) {
    this.buildingName = buildingName;
    this.buildingIntroduction = buildingIntroduction;
    this.detailedInfo = detailedInfo;
    this.buildingGroup = buildingGroup;
    this.firstComment = null;
    this.lastComment = null;
    this.firstPicture = null;
    this.lastPicture = null;
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

  public String getDetailedInfo() {
    return detailedInfo;
  }

  public void setDetailedInfo(String detailedInfo) {
    this.detailedInfo = detailedInfo;
  }

  public String getBuildingGroup() {
    return buildingGroup;
  }

  public void setBuildingGroup(String buildingGroup) {
    this.buildingGroup = buildingGroup;
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

  public UUID getFirstPicture() {
    return firstPicture;
  }

  public void setFirstPicture(UUID firstPicture) {
    this.firstPicture = firstPicture;
  }

  public UUID getLastPicture() {
    return lastPicture;
  }

  public void setLastPicture(UUID lastPicture) {
    this.lastPicture = lastPicture;
  }
}
