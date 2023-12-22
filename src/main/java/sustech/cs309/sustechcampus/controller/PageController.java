package sustech.cs309.sustechcampus.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
@CrossOrigin
public class PageController {

  @GetMapping("/index")
  public String getPageIndex() {
    return "index";
  }

  @GetMapping("/login")
  public String getPageLogin() {
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
  public String getPageBuilding(Model model, @Param(value = "name") String buildingName) {
    if (buildingName != null) {
      model.addAttribute("buildingName", buildingName);
    }
    return "building";
  }

  @GetMapping(value = "/comment")
  public String getPageComment(Model model, @Param(value = "name") String buildingName) {
    if (buildingName != null) {
      model.addAttribute("buildingName", buildingName);
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
}
