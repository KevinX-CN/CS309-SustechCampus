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
  }

  public Optional<Building> getBuildingByName(String buildingName) {
    Optional<Building> result = Optional.empty();
    List<Building> buildingByName = this.buildingRepository.findAllByBuildingName(
      buildingName);
    if (buildingByName.size() == 1) {
      result.of(buildingByName.get(1));
      ;
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
  }

  public void initBuilding() {
    List<Building> buildingList = new ArrayList<>();
    buildingList.add(new Building("一教", "一教的简介", ""));
    buildingList.add(new Building("二教", "二教的简介", ""));
    buildingList.add(new Building("三教", "三教的简介", ""));
    this.buildingRepository.saveAll(buildingList);
  }
}
