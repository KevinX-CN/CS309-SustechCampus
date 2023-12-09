package sustech.cs309.backend.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.cs309.backend.service.BuildingService;

@RestController
public class BuildingController {

  private final BuildingService buildingService;

  public BuildingController(BuildingService buildingService) {
    this.buildingService = buildingService;
  }

  @CrossOrigin
  @GetMapping(value = "/api/building")
  public String getApiBuilding(@RequestParam(value = "bid") UUID bid) {
    return this.buildingService.getBuildingById(bid).toString();
  }

  @CrossOrigin
  @GetMapping(value = "/api/setBuildingName")
  public String getApiSetBuildingName(@RequestParam(value = "bid") UUID bid,
    @RequestParam(value = "name") String buildingName) {
    return this.buildingService.getBuildingById(bid).toString();
  }
}
