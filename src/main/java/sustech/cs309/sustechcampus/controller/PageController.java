package sustech.cs309.sustechcampus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.cs309.sustechcampus.model.Building;
import sustech.cs309.sustechcampus.service.AccountService;
import sustech.cs309.sustechcampus.service.BuildingService;
import sustech.cs309.sustechcampus.service.CommentService;
import sustech.cs309.sustechcampus.service.ReservationService;

@Controller
@RequestMapping("/page")
@CrossOrigin
public class PageController {

  private final AccountService accountService;
  private final BuildingService buildingService;
  private final CommentService commentService;
  private final ReservationService reservationService;

  public PageController(AccountService accountService, BuildingService buildingService,
    CommentService commentService, ReservationService reservationService) {
    this.accountService = accountService;
    this.buildingService = buildingService;
    this.commentService = commentService;
    this.reservationService = reservationService;
  }

  public String getHeader() {
    return "header";
  }

  @GetMapping("/index")
  public String getPageIndex(Model model) {
    model.addAttribute("header", getHeader());
    return "index";
  }

  @GetMapping("/login")
  public String getPageLogin(Model model) {
    return "login";
  }

  @GetMapping(value = "/register")
  public String getPageRegister() {
    return "register";
  }

  @GetMapping(value = "/map")
  public String getPageMap() {
    return "map";
  }

  @GetMapping(value = "/building")
  public String getPageBuilding(Model model, @Param(value = "name") String name) {
    model.addAttribute("header", getHeader());
    if (name != null) {
      Building building = this.buildingService.getBuildingByName(name).get();
      model.addAttribute("name", name);
      model.addAttribute("introduction",
        building.getDetailedInfo());
      model.addAttribute("group",
        building.getBuildingGroup());
    }
    return "building";
  }

  @GetMapping(value = "/buildingGroup")
  public String getPageBuildingGroup(Model model, @Param(value = "name") String name) {
    model.addAttribute("header", getHeader());
    if (name != null) {
      List<Building> buildingGroup = this.buildingService.getBuildingByGroup(name);
      if (buildingGroup.size() == 0) {
        return null;
      }
      model.addAttribute("buildingGroup", buildingGroup);
    } else {
      return null;
    }
    return "buildingGroup";
  }

  @GetMapping(value = "/comment")
  public String getPageComment(Model model, @Param(value = "name") String name) {
    if (name != null) {
      model.addAttribute("buildingName", name);
    }
    return "comment";
  }

  @GetMapping(value = "/addcomment")
  public String getPageAddcomment() {
    return "addcomment";
  }

  @GetMapping(value = "/reservation")
  public String getPageReservation() {
    return "reservation";
  }

  @GetMapping(value = "/admin")
  public String getAdmin(Model model, @Param(value = "name") String buildingName) {
    model.addAttribute("header", getHeader());
    if (buildingName != null) {
      model.addAttribute("buildingName", buildingName);
    }
    return "admin";
  }

  @GetMapping(value = "/admin/building")
  public String getAdminBuilding(Model model, @Param(value = "name") String name) {
    model.addAttribute("header", getHeader());
    if (name != null) {
      Building building = this.buildingService.getBuildingByName(name).get();
      model.addAttribute("name", name);
      model.addAttribute("introduction",
        building.getDetailedInfo());
      model.addAttribute("group",
        building.getBuildingGroup());
      return "adminBuildingEdit";
    } else {
      List<String> groupList = this.buildingService.getAllGroup();
      Map<String, List<Building>> buildingByGroup = new HashMap<>();
      for (String group : groupList) {
        buildingByGroup.put(group, this.buildingService.getBuildingByGroup(group));
      }
      model.addAttribute("buildingByGroup", buildingByGroup);
      return "adminBuildingList";
    }
  }
}
