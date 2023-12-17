package sustech.cs309.sustechcampus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Building {

  @Id
  @GeneratedValue
  private UUID bid;
  private String buildingName;
  private String buildingIntroduction;
  @Transient
  private List<String> buildingImgPath;

  public Building() {
  }

  public Building(String buildingName) {
    this.buildingName = buildingName;
    this.buildingImgPath = new ArrayList<>();
  }

  public Building(String buildingName, String buildingIntroduction) {
    this.buildingName = buildingName;
    this.buildingIntroduction = buildingIntroduction;
    this.buildingImgPath = new ArrayList<>();
  }

  public UUID getBid() {
    return bid;
  }

  public String getBuildingName() {
    return buildingName;
  }

  public String getBuildingIntroduction() {
    return buildingIntroduction;
  }

  @Override
  public String toString() {
    return this.buildingName + '|' + this.buildingIntroduction;
  }
}
