package sustech.cs309.sustechcampus.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.cs309.sustechcampus.service.BuildingService;

@RestController
@CrossOrigin
public class BuildingController {

  private final BuildingService buildingService;

  public BuildingController(BuildingService buildingService) {
    this.buildingService = buildingService;
  }


  @GetMapping(value = "/api/building")
  public String getApiBuilding(@RequestParam(value = "name") String buildingName) {
    return this.buildingService.getBuildingByName(buildingName).toString();
  }
}
