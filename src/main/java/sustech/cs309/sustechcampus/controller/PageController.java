package sustech.cs309.sustechcampus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
@CrossOrigin
public class PageController {

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
  public String getPageBuilding(Model model,@RequestParam(value = "name") String buildingName) {
    model.addAttribute("buildingName",buildingName);
    return "building";
  }
  @GetMapping(value = "/comment")
  public String getPageComment(){
    return "comment";
  }
  @GetMapping(value = "/viewcomment")
  public String getPageViewComment(Model model,@RequestParam(value = "name") String buildingName) {
    model.addAttribute("buildingName",buildingName);
    return "viewcomment";
  }
}
