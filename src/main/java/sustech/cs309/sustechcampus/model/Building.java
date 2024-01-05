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
  private String briefInfo;
  private String detailedInfo;
  private String buildingGroup;
  private UUID firstComment;
  private UUID lastComment;
  private String picturePath;
  private double centerX;
  private double centerY;

  public Building() {
  }

  public Building(String buildingName, String briefInfo, String detailedInfo,
    String buildingGroup, String picturePath, double centerX, double centerY) {
    this.buildingName = buildingName;
    this.briefInfo = briefInfo;
    this.detailedInfo = detailedInfo;
    this.buildingGroup = buildingGroup;
    this.picturePath = picturePath;
    this.centerX=centerX;
    this.centerY=centerY;
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

  public String getBriefInfo() {
    return briefInfo;
  }

  public void setBriefInfo(String briefInfo) {
    this.briefInfo = briefInfo;
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

  public String getPicturePath() {
    return picturePath;
  }

  public void setPicturePath(String picturePath) {
    this.picturePath = picturePath;
  }

  public double getCenterX() {
    return centerX;
  }

  public void setCenterX(double centerX) {
    this.centerX = centerX;
  }

  public double getCenterY() {
    return centerY;
  }

  public void setCenterY(double centerY) {
    this.centerY = centerY;
  }
}
