package sustech.cs309.sustechcampus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Building;
import sustech.cs309.sustechcampus.repository.BuildingRepository;

@Service
public class BuildingService {

  private final BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  public Building getBuildingById(UUID bid) {
    return this.buildingRepository.findAll().stream().filter((b) -> b.getBid() == bid).findFirst()
      .orElseThrow(() -> new IllegalStateException("Building not exists"));
  }

  public void initBuilding() {
    List<Building> buildingList = new ArrayList<>();
    buildingList.add(new Building("一教", "一教的简介"));
    buildingList.add(new Building("二教", "二教的简介"));
    buildingList.add(new Building("三教", "三教的简介"));
    this.buildingRepository.saveAll(buildingList);
  }

  public void addBuilding(Building building) {
    this.buildingRepository.saveAll(List.of(building));
  }
}
