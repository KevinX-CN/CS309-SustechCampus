package sustech.cs309.sustechcampus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Building;
import sustech.cs309.sustechcampus.model.Comment;
import sustech.cs309.sustechcampus.repository.BuildingRepository;

@Service
public class BuildingService {

  private final BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
    initBuilding();
  }

  public Optional<Building> getBuildingByName(String buildingName) {
    Optional<Building> result = Optional.empty();
    List<Building> buildingByName = this.buildingRepository.findAllByBuildingName(
      buildingName);
    if (buildingByName.size() == 1) {
      result = Optional.of(buildingByName.get(0));
    }
    return result;
  }


  public Optional<UUID> addBuilding(Building building) {
    Optional<UUID> result = Optional.empty();
    if (this.getBuildingByName(building.getBuildingName()).isEmpty()) {
      this.buildingRepository.saveAll(List.of(building));
      result.of(building.getBid());
    }
    return result;
  }

  public void addCommentToBuilding(Building building, Comment comment) {
    if (building.getFirstComment() == null) {
      building.setFirstComment(comment.getCid());
    }
    building.setLastComment(comment.getCid());
    this.buildingRepository.save(building);
  }

  public void editBuildingByName(String buildingName, String buildingIntroduction) {
    Building building = getBuildingByName(buildingName).get();
    building.setBuildingIntroduction(buildingIntroduction);
    this.buildingRepository.save(building);
  }

  public void initBuilding() {
    List<Building> buildingList = new ArrayList<>();
    buildingList.add(new Building("一教", "一教的简介", "", "[<1,1>,<2,2>,<3,3>]"));
    buildingList.add(new Building("二教", "二教的简介", "", "[<2,1>,<3,2>,<1,3>]"));
    buildingList.add(new Building("三教", "三教的简介", "", "[<3,1>,<2,2>,<1,3>]"));
    this.buildingRepository.saveAll(buildingList);
  }
}
